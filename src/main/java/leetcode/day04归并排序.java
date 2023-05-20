package leetcode;

import java.util.Arrays;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/5/9 10:39
 * @Description 二路归并 左晨云
 */
public class day04归并排序 {
    public static int[] findmiddle(int[] a,int[] b){
        int lengthA=a.length;
        int lengthb=b.length;
        int i = 0,j = 0,k=0;
        int[] c=new int[lengthA+lengthb];
        while(i<lengthA||j<lengthb){
            if(i<lengthA&&a[i]<=b[j]){
                c[i+j]=a[i];
                i++;
            }else if(j<lengthb){
                c[i+j]=b[j];
                j++;
            }
        }
//        while(i<lengthA){
//            c[i+j]=b[i]; i++;
//        }
//        while(j<lengthb){
//            c[i+j]=b[j]; j++;
//        }
        return c;
    }
    /**
     *
     * @param a 原始数组
     * @param s  第一段有数数组开始位置
     * @param m  第一段有数数组结束位置，第二段有数数组开始位置为m+1
     * @param t 第二段有数数组结束位置
     * @return
     */
    public static void TwoMerge(int[] a,int[] R,int s,int m,int t){
       int s2=m+1;
       int k=s;

        while(s<=m&&s2<=t){
            if(a[s]<=a[s2]){
                R[k]=a[s];
                s++;
                k++;
            }else{
                R[k]=a[s2];
                s2++;
                k++;
            }
        }
        while(s<=m){
            R[k]=a[s];
            s++;
            k++;
        }
        while(s2<=t){
            R[k]=a[s2];
            s2++;
            k++;
        }

    }
    /**
     *
     * @param a  数组
     * @param len  每个长度为len的有序数组
     * @return
     */
    public static void MergePass(int[] a,int R[],int len){
        int p=0;
        while(len*2+p*2*len<=a.length){
            TwoMerge(a,R,p*2*len,p*2*len+len-1,p*2*len+2*len-1);
            p++;
        }
//        if(){
//         归并最后两个长度不等的有序表
//        } else{
        //把剩下的最后一个有序表复制到R中
        //        }

    }
    public static int[] MergeSort(int[] a){
        int len=1;
        while (len<a.length){
            int R[]=new int[a.length];
            MergePass(a,R,len);
            len*=2;
            a=R;
        }
        return a;
    }
    public static void main(String[] args) {
        int[] a=new int[]{1,3,5,2, 2, 3,1, 5, 6,2,4,8};
        int[] ints = MergeSort(a);
//      System.out.println(Arrays.toString(TwoMerge(a,0,2,5)));
        System.out.println(Arrays.toString(ints));

    }
}
