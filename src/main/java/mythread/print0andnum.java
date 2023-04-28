package mythread;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/11/18 9:45
 * @Description
 */
class ZeroEvenOdd {
    public int num;
    public int count = 0;
    public boolean ifZero = false;

    public ZeroEvenOdd(int num) {
        this.num = num;
    }

    public void zero() {
        PrintNumber(0);
    }

    public void even() {
        if (num % 2 == 0) {
            PrintNumber(num);
        }
    }

    public void odd() {
        if (num % 2 == 1) {
            PrintNumber(num);
        }
    }

    public static void PrintNumber(int num) {
        System.out.println(num);
    }
}

public class print0andnum {

    public static void main(String[] args) {
        final ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(8);
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (zeroEvenOdd) {
                    while (zeroEvenOdd.ifZero) {
                        try {
                            zeroEvenOdd.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    zeroEvenOdd.zero();
                    zeroEvenOdd.ifZero = true;
                    zeroEvenOdd.notifyAll();
                }

            }
        });
        Thread b = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (zeroEvenOdd) {
                    while (zeroEvenOdd.ifZero && zeroEvenOdd.count % 2 == 1) {
                        try {
                            zeroEvenOdd.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    zeroEvenOdd.even();
                    zeroEvenOdd.count++;
                    zeroEvenOdd.ifZero = false;
                    zeroEvenOdd.notifyAll();
                }

            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (zeroEvenOdd) {
                    while (zeroEvenOdd.ifZero && zeroEvenOdd.count % 2 == 0) {
                        try {
                            zeroEvenOdd.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    zeroEvenOdd.odd();
                    zeroEvenOdd.count++;
                    zeroEvenOdd.ifZero = false;
                    zeroEvenOdd.notifyAll();
                }

            }
        });
        a.start();
        b.start();
        c.start();
        //        for()
    }
}
