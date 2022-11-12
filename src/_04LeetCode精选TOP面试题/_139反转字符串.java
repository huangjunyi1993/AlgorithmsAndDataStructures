package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/reverse-string/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/9.
 */
public class _139反转字符串 {
    class Solution {
        public void reverseString(char[] s) {
            int l = 0;
            int r = s.length - 1;
            while (l < r) {
                char tmp = s[l];
                s[l++] = s[r];
                s[r--] = tmp;
            }
        }
    }
}
