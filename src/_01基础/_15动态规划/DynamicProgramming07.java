package _01基础._15动态规划;

/**
 * 求两个字符串的最长公共子序列长度
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming07 {

    /**
     * 暴力递归
     * @param str1
     * @param str2
     * @return
     */
    public static int getMaxCommon01(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return process01(chars1, chars2, chars1.length - 1, chars2.length - 1);
    }

    /**
     * 求chars1从0到r1与chars2从0到r2的最长公共子序列长度
     * @param chars1
     * @param chars2
     * @param r1
     * @param r2
     * @return
     */
    private static int process01(char[] chars1, char[] chars2, int r1, int r2) {
        if (r1 == 0 && r2 == 0) {
            // 只剩一个字符，相等时1，不等是0
            return chars1[r1] == chars2[r2] ? 1 : 0;
        } else if (r1 == 0) {
            // chars1只剩一个字符，如果和chars2[r2]相等，返回1，不等看chars2前面的字符
            return chars1[r1] == chars2[r2] ? 1 : process01(chars1, chars2, r1, r2 - 1);
        } else if (r2 == 0) {
            // chars2只剩一个字符，如果和chars1[r1]相等，返回1，不等看chars1前面的字符
            return chars1[r1] == chars2[r2] ? 1 : process01(chars1, chars2, r1 - 1, r2);
        } else {
            // 不要r1，看公共子序列多长
            int p1 = process01(chars1, chars2, r1 - 1, r2);
            // 不要r2，看公共子序列多长
            int p2 = process01(chars1, chars2, r1, r2 - 1);
            // r1 == r2，看公共子序列多长
            int p3 = chars1[r1] == chars2[r2] ? process01(chars1, chars2, r1 - 1, r2 - 1) : 0;
            // 三个结果PK
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    /**
     * 改成动态规划
     * @param str1
     * @param str2
     * @return
     */
    public static int getMaxCommon(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        /*
        dp[r1][r2]：
        chars1从0到r1与chars2从0到r2的最长公共子序列长度
         */
        int[][] dp = new int[chars1.length][chars2.length];
        // if (r1 == 0 && r2 == 0)
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        // else if (r1 == 0)
        for (int i = 1; i < chars2.length; i++) {
            dp[0][i] = Math.max(dp[0][i-1], chars2[i] == chars1[0] ? 1 : 0);
        }
        // else if (r2 == 0)
        for (int i = 1; i < chars1.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], chars1[i] == chars2[0] ? 1 : 0);
        }
        // else
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (chars1[i] == chars2[j]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
            }
        }
        return dp[chars1.length - 1][chars2.length - 1];
    }

}
