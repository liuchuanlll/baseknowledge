package mythread;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/11/14 10:03
 * @Description
 */
public class xianchengsisuo2 {
    private static Lock  lock2=new ReentrantLock();
    private static Lock  lock=new ReentrantLock();
//    private Lock readLock = lock.readLock();
//    // 写锁
//    private Lock writeLock = lock.writeLock();
//    private static ReentrantReadWriteLock lock =   new ReentrantReadWriteLock();
    public static void main(String[] args) {
        Thread T1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1线程开始");
                lock.lock();
                System.out.println("T1.lock.lock()");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lock();
                System.out.println("T1.lock2.lock()");
                if(true){
                    throw new RuntimeException("T1运行时出现异常,线程停止");
                }
                lock.unlock();
                System.out.println("T1.lock.unlock()");
                lock2.unlock();
                System.out.println("T1.lock2.unlock()");
            }
        });
        Thread T2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2线程开始");
                lock2.lock();
                System.out.println("T2.lock2.lock()");
                lock.lock();
                System.out.println("T2.lock1.lock()");
                if(true){
                    throw new RuntimeException("T2运行时出现异常,线程停止");
                }
                lock.unlock();
                System.out.println("T2.lock.unlock()");
                lock2.unlock();
                System.out.println("T2.lock2.unlock()");
            }
        });
        T1.start();
        T2.start();
    }
}
