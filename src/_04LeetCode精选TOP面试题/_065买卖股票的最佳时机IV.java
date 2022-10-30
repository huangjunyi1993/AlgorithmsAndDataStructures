package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _065买卖股票的最佳时机IV {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int N = prices.length;
        if (k >= N / 2) return maxProfit(prices);
        /*
        dp[i][j]：
        在0~i上做交易，交易不超过j次，最大收益

        dp[i][j]:
        1、i不参与交易，dp[i-1][j]
        2、i作为最后一次交易卖出点，枚举0~i作为最后一次卖出点的情况，取最大值
           dp[i][j-1]   - prices[i]   + prices[i]
           dp[i-1][j-1] - prices[i-1] + prices[i]
           dp[i-2][j-1] - prices[i-2] + prices[i]
           dp[i-3][j-1] - prices[i-3] + prices[i]
           dp[i-4][j-1] - prices[i-4] + prices[i]
        两种情况去最大值

        那么dp[i+1][j]的情况而呢？
           dp[i+1][j-1] - prices[i+1]   + prices[i + 1]
           dp[i][j-1]   - prices[i]   + prices[i + 1]
           dp[i-1][j-1] - prices[i-1] + prices[i + 1]
           dp[i-2][j-1] - prices[i-2] + prices[i + 1]
           dp[i-3][j-1] - prices[i-3] + prices[i + 1]
           dp[i-4][j-1] - prices[i-4] + prices[i + 1]

        可以观察到，有重复计算的部分，可以优化掉枚举行为
         */
        int[][] dp = new int[N][k + 1];
        for (int j = 1; j <= k; j++) {
            int best = dp[0][j] - prices[0];
            for (int i = 1; i < N; i++) {
                best = Math.max(best, dp[i][j - 1] - prices[i]);
                dp[i][j] = Math.max(dp[i - 1][j], best + prices[i]);
            }
        }
        return dp[N - 1][k];
    }
    public int maxProfit(int[] prices) {
        int res = 0;
        // 每个上坡抓一个答案
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                res += (prices[i] - prices[i - 1]);
            }
        }
        return res;
    }
}
