package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/12/21 14:13
 * @Description
 */
public class day40组合总数II {
    ArrayList<Integer> arrayList=new ArrayList<Integer>();

    void backTracking(int[] ints,int t,int startIndex){
        if(t<=toSum(arrayList)){
            if(t==toSum(arrayList)){
                System.out.println(arrayList.toString());
            }
            return;
        }
        for(int i=startIndex;i<ints.length;i++){
            if(arrayList.size()==0&&ints[i-1]==ints[i]){
                arrayList.add(ints[i]);
                backTracking(ints,t,i+1);
                arrayList.remove(arrayList.size()-1);
            }

        }
    }



    public static void main(String[] args) {
//        new day39组合总数().backTracking(new int[]{2,3,6,7},7,0);
        int[] ints = new int[]{1, 1, 2, 5,5, 6, 7, 10};
        Arrays.sort(ints);
        new day40组合总数II().backTracking(ints,8,0);
    }
    static int toSum(ArrayList<Integer> arrayList){
        int sum=0;
        for(Integer i:arrayList){
            sum+=i;
        }
        return sum;
    }
}
