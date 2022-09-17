package _01基础._15动态规划;

/**
 * 两个字符串的最长公共子序列
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming07 {

    public static int getMaxCommon(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] dp = new int[chars1.length][chars2.length];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < chars1.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], chars1[i] == chars2[0] ? 1 : 0);
        }
        for (int i = 1; i < chars2.length; i++) {
            dp[0][i] = Math.max(dp[0][i-1], chars2[i] == chars1[0] ? 1 : 0);
        }
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (chars1[i] == chars2[j]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
            }
        }
        return dp[chars1.length - 1][chars2.length - 1];
    }

}
