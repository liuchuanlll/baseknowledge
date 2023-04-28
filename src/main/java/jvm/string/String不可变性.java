package jvm.string;

public class String不可变性 {
    public void test1(){
//        "abc"在字符串常量中，例如String s1="abc";System.out.println(s1=="abc");//true
//       chars= new char[]{} ;  string t=new string(chars)  不在字符串常量中，因为new char[]{}显示创建字节数组，然后string指向该堆中的数组
//        new string(x)不会创建字符串常量因为x为变量，但是不保证之前x定义时不会
//        tostring()不会放常量池，因为使用了字符串拼接或new string(new char[]{})
        String s1="123";//字面量定义的方式,存储在堆空间的常量池中，静态变量和字符串常量都在堆中
        String s2="123";
        s1="456";//存储在字符串常量池中的数据只要有修改就要重新创建，且string使用的数组定义为了final，也无法修改
        System.out.println(s1 == s2);
    }
    public void test2(){
        String s1="abc";//字面量定义的方式
        String s2="abcdef";
        s1+="def";
        System.out.println(s1==s2);//false
    }
    public void test3(){
        String s1="abc";//字面量定义的方式
        String s3="dbc";//字面量定义的方式
        String s2=s1.replace('a','d');
        System.out.println(s1);//abc
        System.out.println(s2);//adc
        System.out.println(s2==s3);//false
    }


    String str="good";
    char[] ch={'b','e','s','t'};
    public void change(String str,char[] ch){
        str="ok";
        ch[0]='b';
    }
    public static void main(String[] args) {
        String不可变性 ex = new String不可变性();
        ex.change(ex.str,ex.ch);
        System.out.println(ex.str);//good  没变
        System.out.println(ex.ch);//best  变了
    }
}
