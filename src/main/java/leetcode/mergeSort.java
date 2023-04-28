package leetcode;

import java.util.Arrays;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2023/1/3 13:55
 * @Description
 */
public class mergeSort {

    //二路归并result[]为返回值,start~mid   mid+1~end,为了后续数组归并，result和data必须一样的位置和长度
    //且result为参数，否则当在方法体里新建时会覆盖其他非排序位置的值。
    static int[] twoMerge(int[] data,int[] result,int start,int mid,int end){
        int i=start;
        int j=mid+1;
        int k=start;//为了后续数组归并，result和data必须一样的位置和长度
        while(i<=mid&&j<=end){
            if(data[i]<=data[j]){
                result[k]=data[i];
                k++;
                i++;
            } else{
                result[k]=data[j];
                k++;
                j++;
            }
        }
        while(i<=mid){
            result[k]=data[i];
            k++;
            i++;
        }
        while(j<=end){
            result[k]=data[j];
            k++;
            j++;
        }
        return result;
    }

    int[] twoMerge2(int[] a,int[] b){
        int lengthA = a.length;
        int lengthb = b.length;
        int i = 0;
        int j = 0;
        int c[] = new int[lengthA + lengthb];
        while (i < lengthA || j < lengthb) {
            if (a[i] <= b[j]) {
                c[i + j] = a[i];
                i++;
            } else {
                c[i + j] = b[j];
                j++;
            }
        }
        return c;
    }
    /**
     *
     * @param a  数组
     * @param len  每个长度为len的有序数组
     * @return 两两合并长度为len的数组，最后一个长度不固定
     */
    static void MergePass(int[] a,int R[],int len) {
        int p = 0;//已排序长度，p-1为最大排序下标
        while (len * 2 + p <= a.length) {
            twoMerge(a, R, p, p + len - 1, p + 2 * len - 1);
            p+=2* len;
        }
        if(p+len<a.length&&p+len*2>a.length){//归并最后两个长度不等的有序表
            twoMerge(a, R, p, p + len - 1, a.length-1);
        } else{ //把剩下的最后一个有序表复制到R中
            for(int i=p;i<a.length;i++){
                R[i]=a[i];
            }
        }

    }

    static int[] MergeSort(int a[]){
        int len = 1;//最小长度为1时一定有序
        while (len < a.length) {//当len = a.length时说明只有一个有序数组了
            int[] R = new int[a.length];
            MergePass(a, R, len);
            len *= 2;
            a = R;
            System.out.println(Arrays.toString(a));
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 2, 2, 3, 1, 5, 6, 2, 4, 8};
        int[] ints = MergeSort(a);
        //      System.out.println(Arrays.toString(TwoMerge(a,0,2,5)));
        System.out.println(Arrays.toString(ints));
    }
}
