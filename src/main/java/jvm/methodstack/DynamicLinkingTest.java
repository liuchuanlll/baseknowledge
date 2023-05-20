package jvm.methodstack;

public class DynamicLinkingTest {
    public static Object str = new stackAnalyse();
    public static int number =144545;
    int num = 6545;

    public void methodA(){
        System.out.println("methodA()....");
    }

    public String methodB(){

        methodA();

        num++;
        System.out.println(num);
        return "hgf hfg";
    }

}
