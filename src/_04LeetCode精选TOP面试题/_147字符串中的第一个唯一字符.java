package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/first-unique-character-in-a-string/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _147字符串中的第一个唯一字符 {
    class Solution {
        public int firstUniqChar(String s) {
            int[] count = new int[26];
            char[] chs = s.toCharArray();
            int N = s.length();
            // 遍历统计一遍
            for (int i = 0; i < N; i++) {
                count[chs[i] - 'a']++;
            }
            // 第二次遍历遇到出现1次的就返回
            for (int i = 0; i < N; i++) {
                if (count[chs[i] - 'a'] == 1) return i;
            }
            return -1;
        }
    }
}
