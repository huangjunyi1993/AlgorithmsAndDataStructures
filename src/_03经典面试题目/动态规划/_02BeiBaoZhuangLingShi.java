package _03经典面试题目.动态规划;

/**
 * 背包容量为w
 * 一共有n袋零食，第1袋零食体积为v[i] > 0
 * 总体积不超过背包容量的情况下
 * 一共有多少种零食方法？（总体积为0也算一种方法）
 * Created by huangjunyi on 2022/9/23.
 */
public class _02BeiBaoZhuangLingShi {

    public static int process(int[] v, int w) {
        int n = v.length;
        /*
        弄一个dp数组（二维）
        dp[i][j]表示零食从0~i自由选择，刚好凑齐j体积的放法数量
         */
        int[][] dp = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        if (v[0] <= w) {
            dp[0][v[0]] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                /*
                动态转移方程
                dp[i][j]等于i号零食不要时的放法数（dp[i - 1][j])加上i号零食要时的放法数（dp[i - 1][j - v[i]]）
                 */
                dp[i][j] = dp[i - 1][j] + (j - v[i] >= 0 ? dp[i - 1][j - v[i]] : 0);
            }
        }
        //把最后一行累加，得到结果
        int res = 0;
        for (int i = 0; i <= w; i++) {
            res += dp[n - 1][i];
        }
        return res;
    }

}
