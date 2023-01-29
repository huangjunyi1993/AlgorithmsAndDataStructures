package _04LeetCode精选TOP面试题.股票问题;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _064买卖股票的最佳时机III {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int min = prices[0]; // i以前的最低价格
        int maxOnce = 0; // i以前最大收益的一次买卖
        int res = 0; // 最好结果
        int maxOnceMinBuy = -prices[0]; // i以前经过最大的一次买卖，然后又挑了一个最低价格再买入的最优值
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, maxOnceMinBuy + prices[i]);
            min = Math.min(min, prices[i]);
            maxOnce = Math.max(maxOnce, prices[i] - min);
            maxOnceMinBuy = Math.max(maxOnceMinBuy, maxOnce - prices[i]);
        }
        return res;
    }
}
