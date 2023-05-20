package leetcode;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2022/5/9 9:57
 * @Description
 */
public class day03无重复最长子串 {
    public static int lengthOfLongestSubstring(String s){
        HashMap<Character,Integer> map=new HashMap<Character, Integer>();
        int max=0,start=0;
        for(int end=0;end<s.length();end++){
            char ch=s.charAt(end);
            if(map.containsKey(ch)){
                start=map.get(ch)+1;
            }
            max=Math.max(max,end-start+1);
            map.put(ch,end);
        }
        return max;
    }


    public static int lengthOfLongestSubstring2(String s){
        HashSet<Character> set=new HashSet<Character>();
        int max=0,start=0;
        for(int end=0;end<s.length();end++){
            char ch=s.charAt(end);
            while(set.contains(ch)){
               set.remove(s.charAt(start));
               start++;
            }
            set.add(ch);
            max=Math.max(max,end-start+1);
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcab"));
        System.out.println(lengthOfLongestSubstring("abcab"));
    }
}
