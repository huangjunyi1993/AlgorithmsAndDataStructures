package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _063买卖股票的最佳时机II {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
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
