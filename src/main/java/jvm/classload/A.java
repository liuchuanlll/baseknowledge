package jvm.classload;

import java.util.concurrent.atomic.AtomicInteger;

// 父类A
public abstract  class A {
    int i;
    public  A() {
        System.out.println("I'm a ");
    }

    public abstract void hello();
}
// 子类B
 class B extends A{

    public B() {
        super();
        System.out.println("I'm b");
    }

    @Override
    public void hello() {
        System.out.println("Hello, i'm b");
    }


    public static void main(String[] args) {
        B b = new B();
        b.i = 10;
        System.out.println(b.i);
        b.hello();
    }

}

