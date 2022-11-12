package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/coin-change/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/11.
 */
public class _132零钱兑换 {
    class Solution {
        public int coinChange(int[] coins, int amount) {
            if (coins == null || coins.length == 0 || amount < 0) return -1;
            /*
            dp[i][j]:
            只能使用0~i号货币，凑够j元钱，最少用多少张钞，-1位无法凑出
            1、不使用i号货币，则dp[i-1][j]
            2、使用1张i号货币，则dp[i-1][j-coins[i]] + 1
            3、使用2张i号货币，则dp[i-1][j-2*coins[i] + 2
            ......
            可以进行斜率优化
             */
            int[][] dp = new int[coins.length][amount + 1];
            for (int i = 1; i <= amount; i++) {
                if (i % coins[0] == 0) {
                    dp[0][i] = i / coins[0];
                } else {
                    dp[0][i] = -1;
                }
            }
            for (int i = 1; i < coins.length; i++) {
                for (int j = 1; j <= amount; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                    if (dp[i - 1][j] != - 1) {
                        dp[i][j] = dp[i - 1][j];
                    }
                    if (j - coins[i] >= 0 && dp[i][j - coins[i]] != -1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
                    }
                    if (dp[i][j] == Integer.MAX_VALUE) {
                        dp[i][j] = -1;
                    }
                }
            }
            return dp[coins.length - 1][amount];
        }
    }
}
