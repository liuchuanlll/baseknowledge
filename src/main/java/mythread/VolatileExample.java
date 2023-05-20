package mythread;


import java.util.concurrent.locks.Lock;

public class VolatileExample {


    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // 开启线程
        myThread.start();
        // 主线程执行
        for (; ; ) {
            if (myThread.isFlag()=="true") {
                System.out.println("有点东西");
            }
        }
    }

}

/**
 * 子线程类
 */
class MyThread extends Thread {

    private String flag = "false";

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 修改变量值
        flag = "true";
        System.out.println("flag = " + flag);
    }

    public String isFlag() {
        return flag;
    }

    public  void setFlag(String flag) {

        this.flag = flag;
    }
}