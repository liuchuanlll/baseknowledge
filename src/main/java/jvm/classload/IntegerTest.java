package jvm.classload;

public class IntegerTest {
    int a=1;
    public IntegerTest() {
        a=8;
    }
    public static void main(String[] args) {
        Integer x=5;
        int y=5;
        System.out.println(x == y);//true 引用类型和基本类型比较是会自动拆箱，无论y多大都相等

        Integer x2=5;
        System.out.println(x==x2);//true 有缓存

        Integer i1=128;
        Integer i2=128;
        System.out.println(i1==i2);//false

        Integer integer=new Integer(5);
        Integer integer2=new Integer(5);
        System.out.println(integer==integer2);//显示定义，肯定为false
    }
}
