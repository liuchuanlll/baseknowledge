package leetcode;

import org.apache.kafka.common.protocol.types.Field;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2023/3/14 17:07
 * @Description
 */
public class day2251花期数目 {
//    思路一：某person能看到的花数=在t时刻花开数-t时刻花谢数
    public int[] fullBloomFlowers(int[][] flowers,int[] persons){
        HashMap<String, String> stringStringHashMap = new HashMap<>();

        Arrays.sort(flowers);
        return new int[]{1};
    }

    public static void main(String[] args) {
        int[][] x = {{2, 4}, {1, 3}};
        Arrays.sort(x[0]);
        System.out.println(x);

    }
}
