package _03经典面试题目.滑动窗口;

/**
 * https://leetcode.cn/problems/permutation-in-string/
 *
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * Created by huangjunyi on 2022/12/31.
 */
public class _02PermutationInString {
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s2.length() < s1.length()) return false;
            char[] chs1 = s1.toCharArray();
            char[] chs2 = s2.toCharArray();
            // 字符 => 欠账计数
            int[] chCount = new int[256];
            for (char c : chs1) {
                chCount[c]++;
            }

            int all = s1.length();

            // 初始化窗口
            for (int i =0; i < s1.length(); i++) {
                if (chCount[chs2[i]]-- > 0) all--;
            }

            // 窗口 + hashmap
            // 每个进窗口的字符，在map中--，出窗口的字符，在map++
            // 只有欠账字符，--后才会>=0，其他的都是负数，那--后>=0的，all就--
            // all减到0，表示欠账全部还完，返回true
            for (int i = s1.length(); i < s2.length(); i++) {
                if (all == 0) return true;
                if (chCount[chs2[i]]-- > 0) all--;
                if (chCount[chs2[i - s1.length()]]++ >= 0) all++;
            }

            return all == 0;
        }
    }
}
