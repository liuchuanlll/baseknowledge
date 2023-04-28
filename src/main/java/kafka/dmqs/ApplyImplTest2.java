package kafka.dmqs;

import com.icbc.dmqs.sdk.standard.consumer.base.DMQSConsumerProperties;
import com.icbc.dmqs.sdk.standard.consumer.handler.DMQSConsumeRecordHandlerAbstract;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.PropertyConfigurator;

import java.util.List;
import java.util.Properties;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2023/4/25 15:11
 * @Description
 */
// 模拟用户消费业务实现类 —— 第一种消费模型
public class ApplyImplTest2 extends DMQSConsumeRecordHandlerAbstract<String, String> {

    @Override
    public DMQSConsumerProperties defineConsumerProperties() {
        Properties cbpsProps = new Properties();
        cbpsProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "122.244.155.247:9092");
        cbpsProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        cbpsProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        cbpsProps.put(ConsumerConfig.GROUP_ID_CONFIG,"GID_CLIENTNAME");
        cbpsProps.put(ConsumerConfig.CLIENT_ID_CONFIG,"CID_Consumer_TopicName");

        cbpsProps.put("app.id","F-DMQS");
        cbpsProps.put("identifier","");

        cbpsProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        cbpsProps.put("max.poll.records","500");
        cbpsProps.put("max.poll.interval.ms","60000");
        cbpsProps.put("enable.auto.commit","false");

        return new DMQSConsumerProperties.Builder(cbpsProps, "topicName", 1)
                // 此处链式编程，可自定义其他设置
                .processType(DMQSConsumerProperties.ConsumeProcessType.FETCH)
                .build();
    }

    @Override
    public void execute(List<ConsumerRecord<String, String>> recordList) throws Exception {
        try{
            System.out.println("to poll fnish!!");
            for(ConsumerRecord<String, String> record:recordList){
                // 编写应用业务逻辑
                System.out.println(record);
            }
        } catch (Exception e){
            /*请务必catch发生的异常，并应用侧完善处理。如果是无法处理异常，请关闭消费者*/
//            logger.info("have exception catch!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertyConfigurator.configure("conf/log4j.properties");
        ApplyImplTest2 applyImplTest1= new ApplyImplTest2();
        applyImplTest1.launch();
    }
}
