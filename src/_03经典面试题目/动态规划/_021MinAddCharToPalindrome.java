package _03经典面试题目.动态规划;

/**
 * 给定一个字符串，如果可随意添加字符，最少添加几个能让字符串整体都是回文串
 *
 * Created by huangjunyi on 2022/10/3.
 */
public class _021MinAddCharToPalindrome {

    public static int dp(String str) {
        if (str == null || str.length() < 2) return 0;
        char[] chs = str.toCharArray();
        int N = chs.length;
        /*
        dp表
        dp[i][j]表示字符串从i~j，最少添加多少个字符，能形成回文串
         */
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                /*
                1、i+1~j范围形成回文串，然后再单独补一个i位置的字符
                2、i~j-1范围形成回文串，然后再单独补一个j位置的字符
                3、如果i位置字符和j位置字符相等，i+1~j-1形成回文串
                这三种情况，选最小的，填到当前格子
                 */
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (chs[i] == chs[j]) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
            }
        }
        return dp[0][N - 1];
    }

}
