package leetcode;

import java.util.ArrayList;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/12/21 13:48
 * @Description
 */
public class day39组合总数 {
    ArrayList<Integer> arrayList=new ArrayList<Integer>();

    void backTracking(int[] ints,int t,int startIndex){
        if(t<=toSum(arrayList)){
            if(t==toSum(arrayList)){
                System.out.println(arrayList.toString());
            }
            return;
        }
       for(int i=startIndex;i<ints.length;i++){
           arrayList.add(ints[i]);
           backTracking(ints,t,i);
           arrayList.remove(arrayList.size()-1);
       }
    }



    public static void main(String[] args) {
        int i=10;
        i %= 2;
        System.out.println(i);
    }
    static int toSum( ArrayList<Integer> arrayList){
        int sum=0;
        for(Integer i:arrayList){
            sum+=i;
        }
        return sum;
    }
}
