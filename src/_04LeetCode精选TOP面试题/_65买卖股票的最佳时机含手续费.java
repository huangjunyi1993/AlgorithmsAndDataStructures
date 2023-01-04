package _04LeetCode精选TOP面试题;

/**
 *
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * Created by huangjunyi on 2023/1/3.
 */
public class _65买卖股票的最佳时机含手续费 {
    class Solution {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            // 之前的最优买入时机，买入后，剩余的钱
            // 之前的最优卖出 - 当前买入的花费
            int bestBuy  = -prices[0] - fee;
            // 之前的最好卖出时机，卖出后，剩余的钱
            // 之前的最好买入 + 当前卖出获得的钱
            int bestSell = 0;
            for (int i = 1; i < prices.length; i++) {
                int curBuy  = bestSell - prices[i] - fee; // 如果决定在当前i号点买入
                int curSell = bestBuy + prices[i]; // 如果决定在当前i号点卖出
                bestBuy  = Math.max(bestBuy, curBuy); // 更新最优买入时机
                bestSell = Math.max(bestSell, curSell); // 更新最优卖出时机
            }
            return bestSell;
        }
    }
}
