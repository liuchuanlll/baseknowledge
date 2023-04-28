package kafka.kafkaproducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

public class Producer {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "122.44.168.118:9092,122.44.168.122:9092,122.44.168.159:9092,122.44.168.165:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //新增参数如下
        props.put("security.protocol","SASL_PLAINTEXT");
        props.put("sasl.mechanism","SCRAM-SHA-512");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"F-GPM\" password=\"A270C9A1BFE360FAE980D5E25DB85261CF6F16D64980FD5A\";");
        props.put("client.id", "a");
        //2、Create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //3、Create the ProducerRecord
        try {
            final String value="{\"PRIVATE\":{\"value\":{\"appNo\":\"GPM\",\"checkPeriod\":\"20201120\",\"checkSerno\":\"20201120124625009\",\"checkTemplateGroupNo\":\"G-GPM-CHECK\",\"fileMsgs\":[{\"fileName\":\"2.txt\",\"signType\":\"sha256\",\"signValue\":\"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855\",\"totalSize\":0},{\"fileName\":\"1.txt\",\"signType\":\"sha256\",\"signValue\":\"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855\",\"totalSize\":0}],\"fileNum\":2,\"msgId\":\"20201120124625009\",\"msgTimestamp\":\"2020-11-20T12:46:25.008+0800\"}},\"PUBLIC\":{\"AppId\":\"GPM\",\"ServiceAlias\":\"Coss_Check_Message_Handle_V1.0\",\"Timestamp\":\"2020-11-20T12:46:25.008+0800\"}}";
            for (int i = 0; i < 10; i++) {
                // 逻辑处理
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("DMQS_TEST", value);
                producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (e != null) {
                            logger.error("Error Info: ",e);
                        } else {
                            logger.warn("topic = {}, partition = {}, offset = {}, time = {}",
                                    metadata.topic(), metadata.toString(), metadata.offset(),
                                    metadata.timestamp());
                            System.out.println(value);}
                    }
                });
                Thread.sleep(10000);

            }


        } catch (Exception e){
            logger.error("Error Msg:",e);
        }finally {
            producer.close();
        }

    }


}
