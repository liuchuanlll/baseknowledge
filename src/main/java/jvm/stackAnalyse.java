package jvm;


public class stackAnalyse {
    public static void main(String[] args) {
        String s1="abc";//字面量定义的方式
        System.out.println(s1=="abc");//false
    }
    public void testAddOperation(){
        byte i = 15;
        int j = 8;
        int k = i + j;
        System.out.println(k);
    }
    public int getSum(){
        byte i = 15;
        int j = 8;
        int k = i + j;
         return k;//11: ireturn     idea你看下aload_0或者ireturn，当前有返回值的栈帧直接把操作数栈第一个值压入前一个栈帧的操作数栈中
    }
    public void testGetSum(){
        //如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中，并更新PC寄存器中下一条需要执行的字节码指令。
        int i=getSum();
//        0: aload_0  将局部变量槽0 即this 指针入栈
//        1: invokevirtual #50                 // Method getSum:()I    调用虚方法invokvirtual，讲返回值保存到operand stack里面。
//        4: istore_1  3. 将栈顶元素存入1号索引局部变量中
        int j=10;
        System.out.println(i+j);
    }
}
