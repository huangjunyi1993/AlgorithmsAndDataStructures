package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _148至少有K个重复字符的最长子串 {
    class Solution {
        public int longestSubstring(String s, int k) {
            int res = 0; // 结果，长度
            char[] chs = s.toCharArray();
            int N = chs.length;
            /*
            每次循环，限制窗口内的字符串种数必须是required种，才结算，
            少于required则r右移，
            直到即将要超了，结算，然后l右移
             */
            for (int required = 1; required <= 26; required++) {
                int[] count = new int[26]; // 统计每个字母出现的次数
                int a = 0; // 窗口中的字母种数
                int b = 0; // 窗口中出现次数大于等于k次的字母的种数
                int l = 0; // 窗口左边界
                int r = 0; // 窗口右边界
                while (l < N) {
                    while (r < N && !(a == required && count[chs[r] - 'a'] == 0)) {
                        if (count[chs[r] - 'a'] == 0) {
                            a++;
                        }
                        if (count[chs[r] - 'a'] == k - 1) {
                            b++;
                        }
                        count[chs[r] - 'a']++;
                        r++;
                    }
                    if (required == b) res = Math.max(res, r - l);
                    if (count[chs[l] - 'a'] == 1) {
                        a--;
                    }
                    if (count[chs[l] - 'a'] == k) {
                        b--;
                    }
                    count[chs[l] - 'a']--;
                    l++;
                }
            }
            return res;
        }
    }
}
