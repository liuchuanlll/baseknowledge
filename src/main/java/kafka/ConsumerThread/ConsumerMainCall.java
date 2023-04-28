package kafka.ConsumerThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 一个县城一个consumer，本质上还是一台机器订阅一个分区的模式，
 * @author kfzx-lailg
 *
 */
public class ConsumerMainCall {
	public ConsumerMainCall() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("conf/log4j.properties");
		final Logger logger = LoggerFactory.getLogger(ConsumerMainCall.class);
		
		int ThreadCounts = 2;
		final List<ConsumerThread> consumers = new ArrayList<ConsumerThread>();
		final ExecutorService executor = Executors.newFixedThreadPool(ThreadCounts);
		
		for(int i=0; i<2; i++) {
			ConsumerThread _ct = new ConsumerThread(i);
			consumers.add(_ct);	
			
			executor.submit(_ct);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				logger.error("Exec ShutdownHook!");
				// TODO Auto-generated method stub
				for(ConsumerThread consumer:consumers) {
					consumer.shutdown();
				}
				executor.shutdown();
				
				try {
					executor.awaitTermination(10000, TimeUnit.MILLISECONDS);
				}catch(InterruptedException e) {
					logger.error("Error Msg: ",e);
				}
				logger.error("ShutdownHook Finish!");
			}
			
		});
	}

}
