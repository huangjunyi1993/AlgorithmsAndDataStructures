package _03经典面试题目.动态规划;

/**
 * 给定两个字符串str1和str2,再给定三个整数ic,dc和rc,分别代表插入， 删除和替换一个字符的代价。
 * 返回将str1编辑成str2的最小代价。
 * 比如，str1="abc",str2="adc",ic=5,dc=3,rc=2.从"abc"编辑成adc, 把b替换成d是代价最小的所以返回2。
 * 再比如,str1="abc",str2="adc",ic=5,dc=3,rc=100.从abc编辑成adc，先删除d,然后插入d是代价最小的, 所以返回8。
 * Created by huangjunyi on 2022/9/25.
 */
public class _10EditCost {

    public static int getMinCost(String str1, String str2, int ic, int dc, int rc) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        /*
        申请一个二维数组dp
        dp[i][j]表示str1字符串从0~i-1位，转成字符串str2从0~j-1位，的最小代价
        长度多申请1，用于0位表示str1空串转成str2，和str1转成空串的代价
         */
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        dp[0][0] = 0;
        for (int i = 1; i < chars2.length + 1; i++) dp[0][i] = i * ic;
        for (int i = 1; i < chars1.length + 1; i++) dp[i][0] = i * dc;
        for (int i = 1; i < chars1.length + 1; i++) {
            for (int j = 1; j < chars2.length + 1; j++) {
                /*
                四种情况：
                1、str1第i-1位字符和str2第j-1位字符相等，则代价是str1从0~i-2位变为str2从0~j-2位的代价
                2、str1第i-1位字符和str2第j-1位字符不相等，则代价是str1从0~i-2位变为str2从0~j-2位的代价，再加一个替换rc的代价
                3、str1从0~i-1位变为str2从0~j-2位的代价，再加一个ic的代价（插入str2第j-1为字符）
                4、str1从0~i-2位变为str2从0~j-1位的代价，再加一个dc的代价（删除str1第i1为字符）
                 */
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[chars1.length][chars2.length];
    }

}
