package spring;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/12/5 14:54
 * @Description
 */
public class test {
    int x = 13;
    public int simpleMethod() {
        test test2=new test();
        test2.x=test2.x+1;

        return test2.x;
    }

    public static void main(String[] args) {
        int i = new test().simpleMethod();
        System.out.println(i);
    }
}
