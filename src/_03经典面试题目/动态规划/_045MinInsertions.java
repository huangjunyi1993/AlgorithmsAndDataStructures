package _03经典面试题目.动态规划;

/**
 * https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
 *
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * 「回文串」是正读和反读都相同的字符串。
 *
 * Created by huangjunyi on 2022/12/30.
 */
public class _045MinInsertions {
    class Solution {
        public int minInsertions(String s) {
            char[] chs = s.toCharArray();
            /*
            dp[i][j]
            i~j范围内，至少添几个字符，使其变成回文串
            1、i~j-1添几个，然后前面再添一个chs[j]
            2、i+1~j添几个，然后后面再添一个chs[i]
            3、如果i==j，看i+1~j-1添几个
            3中情况PK
             */
            int[][] dp = new int[chs.length][chs.length];
            for (int i = 0; i < s.length() - 1; i++) {
                dp[i][i + 1] = chs[i] == chs[i + 1] ? 0 : 1;
            }
            for (int i = chs.length - 3; i >= 0; i--) {
                for (int j = i + 2; j < chs.length; j++) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                    if (chs[i] == chs[j]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                    }
                }
            }
            return dp[0][chs.length - 1];
        }
    }
}
