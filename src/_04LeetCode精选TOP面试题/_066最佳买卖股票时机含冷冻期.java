package _04LeetCode精选TOP面试题;

/**
 * Created by huangjunyi on 2022/10/30.
 */
public class _066最佳买卖股票时机含冷冻期 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        // 买入表，0~i范围内，最后一个动作是买入，最大收益
        int[] buy = new int[prices.length];
        // 卖出表，0~i范围内，最后一个动作是卖出，最大收益
        int[] sell = new int[prices.length];
        // 初始化买入和卖出表的0、1位置
        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < prices.length; i++) {
            // 买入表i的最大收益：i-1的最大收益（i不参与买入），i参与买入，两个PK
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            // 卖出表i的最大收益：i-1的最大收益（i不参与卖出），i参与卖出，两个PK
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[prices.length - 1];
    }
}
