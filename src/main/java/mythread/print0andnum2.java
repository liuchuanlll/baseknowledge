package mythread;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/11/18 9:45
 * @Description
 */
class ZeroEvenOdd2 {
    public int num;
    public int count;

    public ZeroEvenOdd2(int num) {
        this.count = count;
    }

    public synchronized void even() throws InterruptedException {

        while (num % 3 != 0) {
            this.wait();
        }
        PrintNumber(num);
        num=1;
        this.notifyAll();
    }

    public synchronized void odd() throws InterruptedException {
        while (num % 3 != 1) {
            this.wait();
        }
        PrintNumber(num);
        num=2;
        this.notifyAll();
    }
    public synchronized void odd2() throws InterruptedException {
        while (num % 3 != 2) {
            this.wait();
        }
        PrintNumber(num);
        num=0;
        this.notifyAll();
    }
    public static void PrintNumber(int num) {
        System.out.println(num);
    }
}

public class print0andnum2 {

    public static void main(String[] args) {
        ZeroEvenOdd2 zeroEvenOdd2 = new ZeroEvenOdd2(8);
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd2.even();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread b = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    zeroEvenOdd2.odd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd2.odd2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();
        b.start();
        c.start();
        //        for()
    }
}
