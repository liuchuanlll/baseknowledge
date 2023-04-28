package kafka.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * @description 
 * @author kfzx-lailg
 *
 */
public class ConsumerEosModule {
	private Logger logger = LoggerFactory.getLogger(ConsumerEosModule.class);
	
	private KafkaConsumer<String, String> consumer;
	private class DemoRebalacneListener implements ConsumerRebalanceListener{

		public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
			// TODO Auto-generated method stub
			commitDBTransaction();
		}
		public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
			// TODO Auto-generated method stub
			for(TopicPartition partition: partitions) {
				consumer.seek(partition, getOffsetFromDB(partition));
				logger.info("seek TopicPartition: "+partition.toString()+" Offset to: "+getOffsetFromDB(partition));
			}
		}

	}
	
	public ConsumerEosModule() {

		//1��Init the Properties
		Properties props = new Properties();
		props.put("bootstrap.servers", "122.16.157.135:9092,122.16.173.85:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id","gg_new");
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
					
					saveRecordToDB(record);
					saveOffsetToDB(record.topic(), record.partition(),record.offset());
				}
				commitDBTransaction();
			}
		}catch(WakeupException e) {
			//ignore, do nothing
		}catch (Exception e) {
			logger.error("Error Msg:",e);
		}finally {
			try {
				commitDBTransaction();
			} finally {
				consumer.close();
				logger.info("Closed Consumer!");
			}
		}
		
	}
	
	public void commitDBTransaction() {
		//Do DB Transaction commit
		logger.info("Do DB Transaction commit");
	}
	
	public long getOffsetFromDB(TopicPartition tp) {
		long offset = 0;;
		//get the offset from DB by Topic Partition
		//offset = xxx
		logger.info("select tp={} offset from db! offset={}",tp.toString(),offset);
		return offset;
	}
	public void saveRecordToDB(ConsumerRecord<String, String> record) {
		//save record to DB
		logger.info("Save record: {} to db",record.toString());
	}
	public void saveOffsetToDB(String topic, int partition, long offset) {
		//save offset info to DB
		logger.info("Save offset info to db: topic = {}, partition = {}, offset = {}",topic,partition,offset);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("conf/log4j.properties");
		
		ConsumerEosModule _sp = new ConsumerEosModule();
		_sp.toDo();
	}

}
