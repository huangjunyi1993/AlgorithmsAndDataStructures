package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/maximum-product-subarray/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/4.
 */
public class _083乘积最大子数组 {
    class Solution {
        public int maxProduct(int[] nums) {
            int res = nums[0];
            int min = nums[0]; // 以i-1结尾的最小累成积
            int max = nums[0]; // 以i-1结尾的最大累成积
            for (int i = 1; i < nums.length; i++) {
                int curMax = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min)); // 求以i结尾的最大累成积
                int curMin = Math.min(nums[i], Math.min(nums[i] * max, nums[i] * min)); // 求以i结尾的最小累成积
                max = curMax;
                min = curMin;
                res = Math.max(res, max);
            }
            return res;
        }
    }
}
