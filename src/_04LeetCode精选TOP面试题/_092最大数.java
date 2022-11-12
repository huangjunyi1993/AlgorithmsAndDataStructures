package _04LeetCode精选TOP面试题;

import java.util.Arrays;

/**
 * Created by huangjunyi on 2022/11/5.
 */
public class _092最大数 {
    class Solution {
        public String largestNumber(int[] nums) {
            String[] arr = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = String.valueOf(nums[i]);
            }
            /*
            排序
            两个数比较的方法是，谁放前面拼出的字典序大，谁就放前面
             */
            Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
            StringBuilder res = new StringBuilder();
            for (String s : arr) {
                res.append(s);
            }
            String s = res.toString();
            char[] chs = s.toCharArray();
            int index = -1;
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != '0') {
                    index = i;
                    break;
                }
            }
            return index == -1 ? "0" : s.substring(index);
        }
    }
}
