package _04LeetCode精选TOP面试题.动态规划;

/**
 * https://leetcode.cn/problems/house-robber/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _096打家劫舍 {
    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return Math.max(nums[0], nums[1]);
            int prepre = nums[0]; // dp[i-2]
            int pre = Math.max(nums[0], nums[1]); // dp[i-1]
            for (int i = 2; i < nums.length; i++) {
                // 每个位置，要么不劫i，那么就是dp[i-1]，如果劫，那么就是nums[i] + dp[i-2]
                int cur = Math.max(pre, nums[i] + prepre);
                prepre = pre;
                pre = cur;
            }
            return pre;
        }
    }

    /**
     * https://leetcode.cn/problems/house-robber-ii/
     * 打家劫舍 II
     */
    class Solution1 {
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return Math.max(nums[0], nums[1]);

            /*
            跟打家劫舍1的解法一样
            只是要分两波，
            第一波是0~n-2
            第二波是1~n-1
            然后两波出来的答案p1/p2 PK一下
             */

            int prepre = nums[0]; // dp[i-2]
            int pre = Math.max(nums[0], nums[1]); // dp[i-1]
            for (int i = 2; i < nums.length - 1; i++) {
                // 每个位置，要么不劫i，那么就是dp[i-1]，如果劫，那么就是nums[i] + dp[i-2]
                int cur = Math.max(pre, nums[i] + prepre);
                prepre = pre;
                pre = cur;
            }
            int p1 = pre;

            prepre = nums[1]; // dp[i-2]
            pre = Math.max(nums[1], nums[2]); // dp[i-1]
            for (int i = 3; i < nums.length; i++) {
                // 每个位置，要么不劫i，那么就是dp[i-1]，如果劫，那么就是nums[i] + dp[i-2]
                int cur = Math.max(pre, nums[i] + prepre);
                prepre = pre;
                pre = cur;
            }
            int p2 = pre;

            return Math.max(p1, p2);
        }
    }
}
