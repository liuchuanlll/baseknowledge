package test;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/11/25 10:45
 * @Description
 */
public class binsch_xuanzhuan {
    public static int seqSch(int[] a,int target) {
        int leftfoot=0;
        int rightfoot=a.length-1;
        while(leftfoot<=rightfoot){
            int midfoot=(leftfoot+rightfoot)/2;//奇数中间，偶数为小的那个;
            if(target<a[leftfoot]&&target>a[rightfoot])  return -1;
            else if(target==a[midfoot]) return midfoot;
            else if(a[midfoot]<a[leftfoot]) {////left~mid有旋转
                if(target<a[rightfoot]&&target>a[midfoot]){//target在mid~rightfoot之间
                    leftfoot=midfoot+1;
                }else{
                    rightfoot=midfoot-1;
                }
            } else {//left~mid无旋转
                if(target<a[rightfoot]&&target>a[midfoot]){//target在mid~rightfoot之间
                    leftfoot=midfoot+1;
                }else{
                    rightfoot=midfoot-1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        4567123
    }
}
