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
public class consumer {
	public consumer() {
		
	}

	private static Logger logger = LoggerFactory.getLogger(consumer.class);

	public static void main(String[] args) throws InterruptedException {
		//1��Init the Properties
		Properties props2 = new Properties();
		props2.put("bootstrap.servers", "122.42.201.152:9092, 122.42.201.219:9092");
		props2.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props2.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props2.put("auto.offset.reset","latest");
//		props2.put("auto.offset.reset","earliest");
		//�����������һ�����ѻ�ǰƫ����������Сƫ�����������ã����糬�����죩������ò����������ã����ϴμ�¼��ƫ������ʼ����
		//q1:����kafuka��¼���ȷ��ʱ�䣬
		//spring֪ʶfactorycontain�����������ѣ����ȷ��ƫ������
		props2.put("group.id","2sdf011");

		props2.put("security.protocol","SASL_PLAINTEXT");
		props2.put("sasl.mechanism","SCRAM-SHA-512");
		props2.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"F-GPM\" password=\"A270C9A1BFE360FAE980D5E25DB85261CF6F16D64980FD5A\";");


//kafkapro
		//2��Create the Consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props2);

		try {
			//3��subscribe Topic
			consumer.subscribe(Collections.singletonList("DMQS_TEST"));

			//4��The poll loop
			while (true) {
				//��ȡ��Ϣ����������û����Ϣ���߳�����100ms������100ms������Ϣ�����̷�����Ϣ��100�ɸ���ҵ������̬�������ͻ��˽������Ӻ��״ε���poll()������ʱ2~3�룬��ͻ��˸���poll()�Ƿ�����Ϣ���ؽ���ҵ���߼�����������ò�����
				ConsumerRecords<String, String> records = consumer.poll(100);//poll-timeout
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("topic = {}, partition = {}, offset = {}, key={}, value={}"+" "+
							record.topic()+" "+record.partition()+" "+ record.offset()+" "+ record.key()+" "+record.value());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			consumer.close();
		}
	}

}
