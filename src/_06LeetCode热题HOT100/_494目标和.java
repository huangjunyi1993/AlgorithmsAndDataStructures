package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/target-sum/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _494目标和 {
    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int N = nums.length;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (Math.abs(target) > sum) return 0;
            int M = 2 * sum + 1;
            int[][] dp = new int[N + 1][M];
            dp[N][sum] = 1;
            for (int index = N - 1; index >= 0; index--) {
                for (int rest = -sum; rest <= sum; rest++) {
                    int res = 0;
                    // 当前数，前面添+号
                    res += sum + rest - nums[index] >= 0 ? dp[index + 1][sum + rest - nums[index]] : 0;
                    // 当前数，前面添-号
                    res += sum + rest + nums[index] < M ? dp[index + 1][sum + rest + nums[index]] : 0;
                    dp[index][rest + sum] = res;
                }
            }
            return dp[0][target + sum];
        }
    }
    /*class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            return process(nums, 0, target);
        }

        *//**
         * 从index位置开始，凑出rest，共有几种方法
         * @param nums
         * @param index
         * @param rest
         * @return
         *//*
        private int process(int[] nums, int index, int rest) {
            if (index == nums.length) return rest == 0 ? 1 : 0;
            int res = 0;
            // 当前数，前面添+号
            res += process(nums, index + 1, rest - nums[index]);
            // 当前数，前面添-号
            res += process(nums, index + 1, rest + nums[index]);
            return res;
        }
    }*/
}
