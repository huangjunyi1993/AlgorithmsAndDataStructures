package _04LeetCode精选TOP面试题.动态规划;

/**
 * https://leetcode.cn/problems/maximum-subarray/description/?favorite=2ckc81c
 * Created by huangjunyi on 2022/12/17.
 */
public class _035最大子数组和 {
    class Solution {
        public int maxSubArray(int[] nums) {
            /*
            动态规划
            dp[i]表示以i位置的数位结尾的子数组的最大累加和是多少
            那么求dp[i]时就是nums[i]和nums[i]+dp[i-1]的最大值
            但是因为求dp[i]是只依赖到dp[i-1]，所以可以把dp优化掉，改用一个变量去存之前的值
             */
            int max = nums[0];
            int pre = nums[0];
            for (int i = 1; i < nums.length; i++) {
                pre = Math.max(nums[i], nums[i] + pre);
                max = Math.max(max, pre);
            }
            return max;
        }
    }
}
