package _03经典面试题目.动态规划;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/scramble-string/description/
 *
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回
 *
 * Created by huangjunyi on 2023/1/1.
 */
public class _048ScrambleString {
    class Solution {
        public boolean isScramble(String s1, String s2) {
            // 洗掉特殊情况
            if (s1 == null && s2 == null) return true;
            if (s1 == null && s2 != null) return false;
            if (s1 != null && s2 == null) return false;
            if (s1.length() != s2.length()) return false;
            char[] chs1 = s1.toCharArray();
            char[] chs2 = s2.toCharArray();
            Set<Character> charSet1 = new HashSet<>();
            Set<Character> charSet2 = new HashSet<>();
            int charCnt1 = 0;
            int charCnt2 = 0;
            for (char c : chs1) {
                if (charSet1.contains(c)) continue;
                charSet1.add(c);
                charCnt1++;
            }
            for (char c : chs2) {
                if (charSet2.contains(c)) continue;
                charSet2.add(c);
                charCnt2++;
            }
            if (charCnt1 != charCnt2) return false;
            int[][][] dp = new int[chs1.length][chs2.length][chs1.length + 1];
            return process(chs1, chs2, 0, 0, chs1.length, dp);
        }

        /**
         * s1从L1开始的N长度，s2从L2开始的N长度，是否互为扰乱字符串
         * @param chs1 s1
         * @param chs2 s2
         * @param L1 s1开始的位置
         * @param L2 s2开始的位置
         * @param N 比较部分的长度
         * @param dp 缓存表
         * @return
         */
        private boolean process(char[] chs1, char[] chs2, int L1, int L2, int N, int[][][] dp) {
            if (dp[L1][L2][N] != 0) return dp[L1][L2][N] == 1;
            if (N == 1) {
                // base case：长度剩1，字符相等则为true
                dp[L1][L2][N] = chs1[L1] == chs2[L2] ? 1 : -1;
                return chs1[L1] == chs2[L2];
            }
            // 枚举切分的位置，左边一部分，右边一部分
            for (int lRange = 1; lRange < N; lRange++) {
                // s1的左部分和s2的左部分，s1的右部分与s2的右部分，递归比较
                boolean p1 = process(chs1, chs2, L1, L2, lRange, dp);
                boolean p2 = process(chs1, chs2, L1 + lRange, L2 + lRange, N - lRange, dp);
                if (p1 && p2) {
                    dp[L1][L2][N] = 1;
                    return true;
                }
                // s1的左部分和s2的右部分，s1的右部分与s2的左部分，递归比较
                p1 = process(chs1, chs2, L1, L2 + N - lRange, lRange, dp);
                p2 = process(chs1, chs2, L1 + lRange, L2, N - lRange, dp);
                if (p1 && p2) {
                    dp[L1][L2][N] = 1;
                    return true;
                }
            }
            dp[L1][L2][N] = -1;
            return false;
        }
    }
}
