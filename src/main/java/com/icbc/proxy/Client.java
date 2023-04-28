package com.icbc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/12/1 19:02
 * @Description
 */
public class Client {

    public static void main(String args[]){
        SubjectInterface real = new RealSubject();
        InvocationHandler proxySubjectHandler = new ProxyHandler(real);
        //ClassLoader loader:类加载器，负责向内存中加载对象的，使用反射获取对象的classloader，例如a..getClass().getClassLoader(),
        //Class<?>[] interfaces:目标对象实现的接口，也是反射获取的
        //InvocationHandler h：自己写的代理类要完成的功能
        //使用Proxy类的方法，等同于静态代理中，代替代理对象new的使用，返回值就是代理对象
//        proxySubjectHandler.invoke(null,)
        SubjectInterface subjectInterface = (SubjectInterface) Proxy.newProxyInstance(real.getClass().getClassLoader(), real.getClass().getInterfaces(), proxySubjectHandler);
        //代理类是java创建，没有类名，只能获取其interface，而接口也没有属性等，无法获取被代理类的属性
        subjectInterface.doSomething("111");
        subjectInterface.doSomething2("2222");

    }

}
