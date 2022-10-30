package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _062买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int res = 0;
        /*
        每一步，表示以i为卖出点，结算一下
        min记录i以前股本价格最低点
         */
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return res;
    }
}
