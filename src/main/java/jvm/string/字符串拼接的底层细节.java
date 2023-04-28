package jvm.string;

public class 字符串拼接的底层细节 {
    public void test3(){
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        /*
        如下的s1 + s2 的执行细节：(变量s是我临时定义的）
        ① StringBuilder s = new StringBuilder();
        ② s.append("a")
        ③ s.append("b")
        ④ s.toString()  --> 约等于 new String("ab")，但不等价，没有生成字符串常量池

        补充：在jdk5.0之后使用的是StringBuilder,在jdk5.0之前使用的是StringBuffer,    2. 针对于final会进行编译器优化，还是放进常量池。

         */
        String s4 = s1 + s2;//
        System.out.println(s3 == s4);//false
    }


    /**
     * 题目：
     * new String("ab")会创建几个对象？看字节码，就知道是两个。
     *     一个对象是：new关键字在堆空间创建的
     *     另一个对象是：字符串常量池中的对象"ab"。 字节码指令：ldc
     */
    /**思考：new String("a") + new String("b")呢？
     *  对象1：new StringBuilder()
     *  对象2： new String("a")
     *  对象3： 常量池中的"a"
     *  对象4： new String("b")
     *  对象5： 常量池中的"b"
     *
     *  深入剖析： StringBuilder的toString():
     *      对象6 ：new String("ab")
     *       强调一下，toString()的调用，在字符串常量池中，没有生成"ab"
     *
     */


    //有点难的面试
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();//调用此方法之前，字符串常量池中已经存在了"1"
        String s2 = "1";
        System.out.println(s == s2);//jdk6：false   jdk7/8：false

        /*
         1、s3变量记录的地址为：new String("11")
         2、经过上面的分析，我们已经知道执行完pos_1的代码，在堆中有了一个new String("11")
         这样的String对象。但是在字符串常量池中没有"11"
         3、接着执行s3.intern()，在字符串常量池中生成"11"
           3-1、在JDK6的版本中，字符串常量池还在永久代，所以直接在永久代生成"11",也就有了新的地址
           3-2、而在JDK7的后续版本中，字符串常量池被移动到了堆中，此时堆里已经有new String（"11"）了
           出于节省空间的目的，直接将堆中的那个字符串的引用地址储存在字符串常量池中。没错，字符串常量池
           中存的是new String（"11"）在堆中的地址
         4、所以在JDK7后续版本中，s3和s4指向的完全是同一个地址。
         */
        String s3 = new String("1") + new String("1");//pos_1
        s3.intern();
        String s4 = "11";//s4变量记录的地址：使用的是上一行代码代码执行时，在常量池中生成的"11"的地址
        System.out.println(s3 == s4);//jdk6：false  jdk7/8：true
    }


}
