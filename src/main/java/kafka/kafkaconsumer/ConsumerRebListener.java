package kafka.kafkaconsumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @description 
 * @author kfzx-lailg
 *
 */
public class ConsumerRebListener {
	private Logger logger = LoggerFactory.getLogger(ConsumerRebListener.class);
	
	private Map<TopicPartition, OffsetAndMetadata> currentOffsets;
	private KafkaConsumer<String, String> consumer;
	private class DemoRebalacneListener implements ConsumerRebalanceListener{

		public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
			// TODO Auto-generated method stub
			if(currentOffsets.size() !=0 ) {
				logger.info("Lost membership in rebalance. Committing current offsets:" + currentOffsets.toString());
				consumer.commitSync(currentOffsets);
				currentOffsets.clear();
			}
		}
		public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
			// TODO Auto-generated method stub
			logger.info("new assignment: ======"+consumer.assignment().toString());
		}

	}
	
	public ConsumerRebListener() {
		currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();

		//1��Init the Properties
		Properties props = new Properties();
		props.put("bootstrap.servers", "122.16.157.135:9092,122.16.173.85:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id","gg_abc");
		props.put("auto.offset.reset","earliest");
		props.put("enable.auto.commit", "false");
		props.put("max.poll.records", "20");
		
		//2��Create the Consumer
		consumer = new KafkaConsumer<String, String>(props);
		
		//3��subscribe Topic
		consumer.subscribe(Collections.singletonList("test"), new DemoRebalacneListener());
	}
	
	public void toDo() {
		
		try {
			
			//4��The poll loop
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				logger.info("Records Count: "+records.count());
				for (ConsumerRecord<String, String> record : records) {
					logger.info("topic = {}, partition = {}, offset = {}, key={}, value={}",
							record.topic(), record.partition(), record.offset(), record.key(), record.value());
					
					currentOffsets.put(new TopicPartition(record.topic(),record.partition()), 
							new OffsetAndMetadata(record.offset()+1, "no metadata"));
					
				}
				
				logger.info("Committing current offsets:" + currentOffsets.toString());
				consumer.commitSync(currentOffsets);
			}
		}catch(WakeupException e) {
			//ignore, do nothing
		}catch (Exception e) {
			logger.error("Error Msg:",e);
		}finally {
			try {
				consumer.commitSync(currentOffsets);
			} finally {
				consumer.close();
				logger.info("Closed Consumer!");
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("conf/log4j.properties");
		
		ConsumerRebListener _sp = new ConsumerRebListener();
		_sp.toDo();
	}

}
