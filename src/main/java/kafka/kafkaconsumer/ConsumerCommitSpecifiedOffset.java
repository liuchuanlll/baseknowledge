package kafka.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @description Consumer auto.offset.resetʾ��
 * @author kfzx-lailg
 *
 */
public class ConsumerCommitSpecifiedOffset {
	private Logger logger = LoggerFactory.getLogger(ConsumerCommitSpecifiedOffset.class);
	public ConsumerCommitSpecifiedOffset() {
		
	}
	
	public void toDo() {
		//1��Init the Properties
		Properties props = new Properties();
		props.put("bootstrap.servers", "122.16.157.135:9092,122.16.173.85:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id","groupD");
		props.put("auto.offset.reset","earliest");
		props.put("enable.auto.commit", "false");
		
		//2��Create the Consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		
		try {			
			//3��subscribe Topic
			consumer.subscribe(Collections.singletonList("test"));consumer.commitAsync();consumer.commitSync();
			
			Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();
			int count = 0;
			
			//4��The poll loop
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				logger.info("Records Count: "+records.count());
				for (ConsumerRecord<String, String> record : records) {
					logger.info("topic = {}, partition = {}, offset = {}, key={}, value={}",
							record.topic(), record.partition(), record.offset(), record.key(), record.value());
					
					currentOffsets.put(new TopicPartition(record.topic(),record.partition()), 
							new OffsetAndMetadata(record.offset()+1, "no metadata"));
					if(count%100 == 0) {
						consumer.commitSync(currentOffsets);
						//consumer.commitAsync(currentOffsets, null);
					}					
					count++;
				}
			}
		}catch (Exception e) {
			logger.error("Error Msg:",e);
		}finally {
			consumer.close();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("conf/log4j.properties");
		
		ConsumerCommitSpecifiedOffset _sp = new ConsumerCommitSpecifiedOffset();
		_sp.toDo();
	}

}
