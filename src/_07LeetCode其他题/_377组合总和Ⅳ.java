package _07LeetCode其他题;

/**
 * https://leetcode.cn/problems/combination-sum-iv/
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * Created by huangjunyi on 2023/1/23.
 */
public class _377组合总和Ⅳ {
    class Solution {
        public int combinationSum4(int[] nums, int target) {
            /*
            dp[rest] 剩余rest数要搞定，从nums中自由挑选数，有几种方法
             */
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int rest = 1; rest <= target; rest++) {
                int ways = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= rest && rest - nums[i] >= 0) ways += dp[rest - nums[i]];
                }
                dp[rest]  = ways;
            }
            return dp[target];
        }
    }
    class SolutionLow {
        public int combinationSum4(int[] nums, int target) {
            return process(nums, target);
        }

        /**
         * 从nums中自由挑选，搞定rest，有几种方法数
         * nums中每个数的尝试，然后往下递归
         * @param nums 数组
         * @param rest 剩余数
         * @return
         */
        private int process(int[] nums, int rest) {
            if (rest == 0) return 1;
            int ways = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= rest) ways += process(nums, rest - nums[i]);
            }
            return ways;
        }
    }
}
