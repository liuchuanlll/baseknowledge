package com.icbc.proxy;


import java.util.ArrayList;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/12/1 19:00
 * @Description
 */
public class RealSubject  implements SubjectInterface{

    ArrayList arrayList=new ArrayList<String>();

    @Override
    public void doSomething(String s) {
        arrayList.add(s);
        System.out.println("doSomething1:;"+arrayList.toString());
    }
    @Override
    public void doSomething2(String s) {
        arrayList.add(s);
        System.out.println("doSomething2:"+arrayList.toString());
    }
    public void doSomething3(String s) {
        System.out.println("call doSomething()3;");
    }

    public ArrayList getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }
}
