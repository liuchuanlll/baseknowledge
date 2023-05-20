package jvm.classload;

public class class文件字节码解析 {
    public int num=1;

    public synchronized int add(){//定义于方法，作用于对象
        num+=2;
        return num;
    }
    public int add2(int j){
        j+=15;
        return j;
    }
    public void load(int num, Object obj, long count, boolean flag, short[] arr) {
        System.out.println(num);
        System.out.println(obj);
        System.out.println(count);
        System.out.println(flag);
        System.out.println(arr);
    }

    public void foo(long l, float f) {
        {
            int i = 1111;
            System.out.println(i);
        }
        {
            String s = "Hello, World";
            System.out.println(s);

        }
    }

    public static String func() {//基本类型，引用类型，string都是返回的第一次定义的值00000
//        String str="00000";

        String str=new String("00000");
        try {
            return str;
        }finally {
            str=new String("111111");
        }
    }


    public static void main(String[] args) {
        String func = func();
        System.out.println(func);
    }
}