package designmode;

public class single {
    private single(){}
    private static volatile single instance;
    public static single getInstance(){
        //第一次判断,如果instance不为null,不需要抢占锁，直接返回对象
        if (instance == null){
            synchronized (single.class){
                //第二次判断
                if (instance == null){
                    instance = new single();
                }
            }
        }
        return instance;
    }
}
class LazyMan3Test{
    public static void main(String[] args) {
        single instance = single.getInstance();
        single instance1 = single.getInstance();
        System.out.println(instance == instance1);
    }
}
