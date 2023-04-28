package kafka.dmqs;

import com.icbc.dmqs.sdk.standard.consumer.base.DMQSConsumerProperties;
import com.icbc.dmqs.sdk.standard.consumer.handler.DMQSConsumeRecordHandlerAbstract;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.PropertyConfigurator;

import java.util.List;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2023/4/25 15:11
 * @Description
 */
// 模拟用户消费业务实现类 —— 第一种消费模型
public class ApplyImplTest1 extends DMQSConsumeRecordHandlerAbstract<String, String> {

    ApplyImplTest1() {
        //如果使用构造函数调用的形式，defineConsumerProperties只能读取配置文件，不能在new Properties()去实例化后调整参数
        super.launch();
    }

    @Override
    public DMQSConsumerProperties defineConsumerProperties() {
        return new DMQSConsumerProperties.Builder("conf/DMQS_kafka.properties", "topicName", 1)
                // 此处链式编程，可自定义其他设置
                .processType(DMQSConsumerProperties.ConsumeProcessType.FETCH)
                .build();
    }

    @Override
    public void execute(List<ConsumerRecord<String, String>> recordList) throws Exception {
        System.out.println("to poll fnish!!");
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
        new ApplyImplTest1();
    }
}
