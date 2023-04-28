package kafka.kafkaproducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer2 {
    private static Logger logger = LoggerFactory.getLogger(Producer2.class);

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "122.44.168.118:9092,122.44.168.122:9092,122.44.168.159:9092,122.44.168.165:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //2、Create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        //3、Create the ProducerRecord
        try {
            String value="{\n" +
                    "\"msgId\": \"GPM\", \n" +
                    "\"checkSerno\": \"/cossfss/data/check/send/GPM/20201111141102751/C-GPM-UPDATEAMT/\", \n" +
                    "\"appNo\": \"GPM\", \n" +
                    "\"checkTemplateNo\": \"C-GPM-PLEDGEONE\", \n" +
                    "\"status\": \"1\",\n" +
                    "\"fileName\": \"GPM_GPMCHANLOG_20201111_001@GPM_KFAPPLOG_20201111_001_RST.ZIP\", \n" +
                    "\"filePath\": \"/cossfss/data/check/send/GPM/20201111141102751/C-GPM-UPDATEAMT/\", \n" +
                    "\"signType\": \"sha256\", \n" +
                    "\"signValue\": \"588f962a20d2e552381f419591b1be40737c89b17b9fcf85f3bb4517664b3706\", \n" +
                    "\"totalSize\": \"646\"\n" +
                    "}";
            for (int i = 0; i < 2; i++) {
                // 逻辑处理
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("paas_6", value);
                producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        if (e != null) {
                            logger.error("Error Info: ",e);
                        } else {
                            logger.warn("topic = {}, partition = {}, offset = {}, time = {}",
                                    metadata.topic(), metadata.partition(), metadata.offset(),
                                    metadata.timestamp());                        }
                    }
                });
            }
        } catch (Exception e){
            logger.error("Error Msg:",e);
        }finally {
            producer.close();
        }

    }


}
