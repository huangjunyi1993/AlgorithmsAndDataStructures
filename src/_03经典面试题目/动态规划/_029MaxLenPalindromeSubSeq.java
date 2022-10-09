package _03经典面试题目.动态规划;

/**
 * 给定一个字符串str，求最长回文子序列长度
 *
 * Created by huangjunyi on 2022/10/7.
 */
public class _029MaxLenPalindromeSubSeq {

    public static int process(String str) {
        if (str == null || str.length() == 0) return 0;

        /*
        利用动态规划范围尝试模型
        先填开始的两条对角线
        然后填表
        1、包含左侧字符
        2、包含右侧字符
        3、不包含左右侧字符
        4、如果左右侧字符相等
        4种情况求max，填到dp[i][j]中
         */
        int N = str.length();
        char[] chs = str.toCharArray();
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < N + 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 2 : 1;
        }
        for (int i = N - 3; i >= 0; i++) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], Math.max(dp[i][j - 1], dp[i + 1][j - 1]));
                if (chs[i] == chs[j]) dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1]);
            }
        }
        return dp[0][N - 1];
    }

}
