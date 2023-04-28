package com.icbc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/12/1 19:03
 * @Description
 */
public class ProxyHandler implements InvocationHandler {
    private Object proxied;

    public ProxyHandler(Object proxied){
        this.proxied = proxied;
    }
//    ClassLoader loader,  Class<?>[] interfaces，InvocationHandler h
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before call");
        Object result =  method.invoke(proxied, args);//执行动态方法
        System.out.println("end call");
        return result;
    }


}
