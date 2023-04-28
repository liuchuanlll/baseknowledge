package mythread;

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
public class xianchengsisuo {
    private ReentrantReadWriteLock lock =   new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    // 写锁
    private Lock writeLock = lock.writeLock();
    ReentrantLock lock2=new ReentrantLock();
    public static void main(String[] args) {
        final Semaphore semaphore_a=new Semaphore(0);
        final Semaphore semaphore_b=new Semaphore(0);
        final Object locka=new Object();
        final Object lockb=new Object();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (locka){
                    System.out.println("a");
                    semaphore_a.release();
                    try {
                        semaphore_b.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockb){
                        System.out.println("a.b");
                    }

                }

            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockb){
                    System.out.println("b");
                    semaphore_b.release();
                    try {
                        semaphore_a.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (locka){
                        System.out.println("b.a");
                    }

                }

            }
        });
        a.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.start();
    }
}
