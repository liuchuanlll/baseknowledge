package kafka.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

/**
 * @description ???????Producer???
 * @author kfzx-lailg
 *
 */
public class consumer2 {
	public consumer2() {
		
	}
	private static Logger logger = LoggerFactory.getLogger(consumer2.class);

	public static void main(String[] args) throws InterruptedException {
		//1、Init the Properties
		Properties props2 = new Properties();
		props2.put("bootstrap.servers", "122.23.13.72:9092,122.23.13.73:9092");
		props2.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props2.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props2.put("auto.offset.reset","latest");
//		props2.put("auto.offset.reset","earliest");
		//当该消费组第一次消费或当前偏移量低于最小偏移量才起作用（比如超过七天），否则该参数不起作用，从上次记录的偏移量开始消费
		//q1:如无kafuka记录如何确定时间，
		//spring知识factorycontain容器批量消费，如何确定偏移量等
		props2.put("group.id","232的424");



//kafkapro
		//2、Create the Consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props2);

		try {
			//3、subscribe Topic
			consumer.subscribe(Collections.singletonList("COSS_2_GPM_4_CHECK"));
			//consumer.subscribe(Pattern.compile("test[A-Z]+"));

			//4、The poll loop
			while (true) {
				//拉取消息，如果服务端没有消息则线程阻塞100ms。若在100ms内有消息则立刻返回消息，100可根据业务需求动态调整。客户端建立连接后首次调用poll()方法耗时2~3秒，如客户端根据poll()是否有消息返回进行业务逻辑处理，请调整该参数。
				ConsumerRecords<String, String> records = consumer.poll(100);//poll-timeout
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("topic = {}, partition = {}, offset = {}, key={}, value={}"+" "+
							record.topic()+" "+record.partition()+" "+ record.offset()+" "+ record.key()+" "+record.value());
//					logger.debug("topic = {}, partition = {}, offset = {}, key={}, value={}",
//							record.topic(), record.partition(), record.offset(), record.key(), record.value());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			consumer.close();
		}
	}

}
