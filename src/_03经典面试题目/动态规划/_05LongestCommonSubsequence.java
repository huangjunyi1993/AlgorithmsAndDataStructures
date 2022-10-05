package _03经典面试题目.动态规划;

/**
 * 求两个字符串的最长公共子序列的长度
 *
 * 使用空间压缩技巧
 * Created by huangjunyi on 2022/9/25.
 */
public class _05LongestCommonSubsequence {

    public static int getSubSeqLen(String str1, String str2) {
        if (str1 == null || str2 == null) return -1;
        if (str1.length() == 0 || str2.length() == 0) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        /*
        当前格子: dp[i][j]，表示字符串str1从0~i与字符串str2从0~j，的最长公共子序列
        4中情况：
        1、最长公共子序列即不以str1[i]结尾，也不以str2[j]结尾，则dp[i][j] = dp[i-1][j-1]
        2、最长公共子序列以str1[i]结尾，则dp[i][j] = dp[i][j-1]
        3、最长公共子序列以str2[j]结尾，则dp[i][j] = dp[i-1][j]
        4、str1[1] == str2[j]，则dp[i][j] = dp[i-1][j-1] + 1
        其中dp[i][j-1]和dp[i-1][j]已经包含dp[i-1][j-1]
        所以可以简化为三种情况

        动态转移方程
        dp[i][j] =  chars1[i] == chars2[j] ? Math.max(Math.max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1] + 1) : Math.max(dp[i-1][j], dp[i][j-1])
        所以当前格子只依赖到左边、上边、左上方，三个格子
        因此可以使用一个一位数组代替二维表
        左上方的格子用一个temp临时遍历记录即可
         */
        int[] dp = new int[chars1.length];
        dp[0] = (chars1[0] == chars1[1]) ? 1 : 0;
        for (int i = 1; i < chars1.length; i++) dp[i] = chars1[i] == chars2[0] ? 1 : dp[i - 1];
        int t = 0; //左上方格子的数目
        for (int i = 1; i < chars2.length; i++) {
            for (int j = 0; j < chars1.length; j++) {
                if (j == 0) {
                    t = dp[0];
                    dp[0] = chars1[0] == chars2[i] ? 1 : dp[0];
                } else {
                    int a = dp[j - 1]; //左边格子的数目
                    int b = dp[j]; //上方格子的数目
                    int p = Math.max(a, b); //左边和上边较大的数
                    if (chars1[j] == chars2[i]) p = Math.max(p, t + 1);
                    t = dp[j];
                    dp[j] = p;
                }
            }
        }
        return dp[dp.length - 1];
    }

}
