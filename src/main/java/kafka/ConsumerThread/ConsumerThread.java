package kafka.ConsumerThread;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(ConsumerThread.class);
	private KafkaConsumer<String, String> consumer;
	private int id;
	public ConsumerThread(int id) {
		//1��Init the Properties
		this.id = id;
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "122.16.157.135:9092,122.16.173.85:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id","group_1");
		//2��Create the Consumer
		consumer = new KafkaConsumer<String, String>(props);
	}
	public void run() {
		// TODO Auto-generated method stub
		try {
			//3��subscribe Topic
			consumer.subscribe(Collections.singletonList("test"));
			
			//4��The poll loop
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					logger.info("topic = {}, partition = {}, offset = {}, key={}, value={}",
							record.topic(), record.partition(), record.offset(), record.key(), record.value());
				}
			}
		}catch (WakeupException e) {
			// ignore for shutdown
			logger.error("ConsumerThread-{}: Catch the WakeupException!",id,e);
		} catch (Exception e) {
			logger.error("Error Msg: ",e);
		}finally {
			logger.error("ConsumerThread Exit!");
			consumer.close();
		}
	}

	public void shutdown() {
		consumer.wakeup();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
