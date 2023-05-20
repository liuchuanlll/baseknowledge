package mythread;

import java.util.concurrent.*;

public class ThreadPoolAndfuzhulei {

    public static void main(String[] args) throws InterruptedException {
        //一池五线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5); //5个窗口
        //一池可扩容线程
        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
//        for (int i = 1; i <=10; i++) {
//            threadPool1.execute(()->{
//                System.out.println(Thread.currentThread().getName()+" 办理业务");
//            });
//        }
//        threadPool1.shutdown(); //关闭线程池，不关闭程序不会关闭
//        System.out.println("关闭线程");
//
//
//        CountDownLatch latch = new CountDownLatch(10);
//        for (int i = 1; i <=10; i++) {
//            threadPool2.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(Thread.currentThread().getName()+" 办理业务");
//                    }finally {
//                        latch.countDown();//latch--
//                    }
//                }
//            });
//        }
//        latch.await();//等待latch值为0
//        threadPool2.shutdown(); //关闭
//        System.out.println("关闭线程");


        CyclicBarrier cyclicBarrier = new CyclicBarrier(10+1);
        for (int i = 1; i <=10; i++) {
            threadPool3.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+" 办理业务");
                        cyclicBarrier.await();//await++
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            //主线程也加入等待
            cyclicBarrier.await();
            threadPool3.shutdown(); //关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("关闭线程");
    }
}
