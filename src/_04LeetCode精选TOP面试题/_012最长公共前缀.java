package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/longest-common-prefix/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _012最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        int l = 0;
        int r = strs[0].length(); // 公共前缀右边界，一开始等于第一个字符的长度，表示先假设第一个字符串时公共前缀
        int i = 1;
        // 遍历每一个字符串，不到缩减公共串边界
        while (i != strs.length && r > l) {
            int p = 0;
            while (p < strs[0].length() && p < strs[i].length() && strs[0].charAt(p) == strs[i].charAt(p)) {
                p++;
                i++;
            }
            r = p;
        }
        return strs[0].substring(l, r);
    }
}
