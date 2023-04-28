package kafka.kafkaconsumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @description Consumer auto.offset.resetʾ��
 * @author kfzx-lailg
 *
 */
public class ConsumerAsyncCommit {
	private Logger logger = LoggerFactory.getLogger(ConsumerAsyncCommit.class);
	public ConsumerAsyncCommit() {
		
	}
	
	public void toDo() {
		//1��Init the Properties
		Properties props = new Properties();
		props.put("bootstrap.servers", "122.23.13.72:9092,122.23.13.73:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id","grouprrB");
		props.put("auto.offset.reset","earliest");
		props.put("enable.auto.commit", "false");
		
		//2��Create the Consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		
		try {			
			//3��subscribe Topic
			consumer.subscribe(Collections.singletonList("COSS_2_GPM_4_CHECK"));
			
			//4��The poll loop
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				logger.info("Records Count: "+records.count());
				for (ConsumerRecord<String, String> record : records) {
					logger.info("topic = {}, partition = {}, offset = {}, key={}, value={}",
							record.topic(), record.partition(), record.offset(), record.key(), record.value());
					//每条信息处理完成后异步提交
					consumer.commitAsync();
				}
				//At least once
				consumer.commitAsync(new OffsetCommitCallback() {
					public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception e) {
						// TODO Auto-generated method stub
						if(e != null)
							logger.error("Error Msg: ",e);
						else
							logger.info(offsets.toString());
					}
					
				});
			}
		}catch (Exception e) {
			logger.error("Error Msg:",e);
		}finally {
			consumer.close();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ConsumerAsyncCommit _sp = new ConsumerAsyncCommit();
		_sp.toDo();
	}

}
