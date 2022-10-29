package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/maximum-subarray/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _037最大子数组和 {
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
            int cur = Math.max(nums[i], nums[i] + pre);
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;
    }

    /**
     * 加题，不能选相邻的数，求最大累加和
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        // dp[i] 0~i 不相邻数，最大累加和，不必以i结尾
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int p1 = nums[i]; // i位置的数自己 如[-1,-2,-3,9,...]
            int p2 = dp[i - 1]; // 不要i位置的数 如[1,2,3,-9,...]
            int p3 = nums[i] + dp[i - 2]; // i位置的数，接上0~i-2的最大累计和
            dp[i] = Math.max(p1, Math.max(p2, p3));
        }
        return dp[nums.length - 1];
    }
}
