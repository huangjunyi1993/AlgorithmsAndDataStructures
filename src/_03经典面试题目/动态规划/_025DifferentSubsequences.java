package _03经典面试题目.动态规划;

/**
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * |||| ||
 * rabbbit
 * || ||||
 * rabbbit
 * ||| |||
 *
 * Created by huangjunyi on 2022/10/5.
 */
public class _025DifferentSubsequences {
    public int numDistinct(String s, String t) {
        if (s.length() == 0 && t.length() == 0) return 1;
        if (s.length() == 0) return 0;
        if (t.length() == 0) return 1;
        int N = s.length();
        int M = t.length();
        char[] chsS = s.toCharArray();
        char[] chsT = t.toCharArray();
        /*
        dp表
        dp[i][j]的含义是s中0~i子串，变成t中0~j子串
        有多少种删除方案（保留）
         */
        int[][] dp = new int[N][M];
        dp[0][0] = chsS[0] == chsT[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = chsS[i] == chsT[0] ? dp[i - 1][0] + 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                /*
                不保留S串中i位置字符，删除（保留）方案数，
                相当于S串中0~i-1位置的子串变成T串中0~j位置的子串，的方案数
                 */
                dp[i][j] = dp[i - 1][j];
                if (chsS[i] == chsT[j]) {
                    /*
                    保留S串中i位置字符，删除（保留）方案
                    保留S串中i位置字符，必须i位置字符等于t串中j位置字符
                    方案数等于S串0~i-1子串变成t串0~j-1子串，的方案数
                     */
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[N - 1][M - 1];
    }
}
