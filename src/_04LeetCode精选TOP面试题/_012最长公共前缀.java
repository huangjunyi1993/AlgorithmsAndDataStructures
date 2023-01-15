package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/longest-common-prefix/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _012最长公共前缀 {
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            int l = 0;
            int r = strs[0].length();
            int i = 1;
            while (i < strs.length && r > l) {
                int p = 0;
                while (p < r && p < strs[i].length() && strs[0].charAt(p) == strs[i].charAt(p)) {
                    p++;
                }
                r = p;
                i++;
            }
            return strs[0].substring(l, r);
        }
    }
}
