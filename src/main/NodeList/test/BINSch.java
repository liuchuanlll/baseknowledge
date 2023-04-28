package test;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/4/24 14:41
 * @Description
 */
public class BINSch {
    /**
     *
     * @param a
     * @param target
     * @return 末尾递归调用很容易改写成不适用栈的非递归算法
     */
public static int seqSch(int[] a,int target) {
    int leftfoot=0;
    int rightfoot=a.length-1;
    while(leftfoot<=rightfoot){
        int midfoot=(leftfoot+rightfoot)/2;//奇数中间，偶数为小的那个;
        if(target==a[midfoot]) return midfoot;
        else if(target<a[midfoot])  rightfoot=midfoot-1;
        else leftfoot=rightfoot+1;
    }
    return -1;
}
    /**
     * 二分搜索 自己写的，思路错误，不通过
     * @return
     */
//    public static int seqSch(int[] a,int target) {
//        int min=a[0];
//        int max=a[a.length-1];
//        int midFoot=(min+max)/2;//奇数中间，偶数为小的那个
//        if(target>max||target<min){return -1;}
//
//        while(a[midFoot]!=target){
//            if(a[midFoot]>target){
//                return seqSch(Arrays.copyOfRange(a,0,midFoot-1),target);
//            } else{
//                return seqSch(Arrays.copyOfRange(a,midFoot+1,a.length-1),target);
//            }
//        }
//        return midFoot;
//    }

    public static void main(String[] args) {
        int i = seqSch(new int[]{1, 2, 3, 4, 5, 6, 7, 8,9,10,11}, 10);
        System.out.println(i);
        System.out.println(seqSch(new int[]{1, 2, 3, 4}, 3));
        System.out.println(seqSch(new int[]{1, 2, 3, 4}, 4));
        System.out.println(seqSch(new int[]{1, 2, 3, 4}, 5));
    }
}
