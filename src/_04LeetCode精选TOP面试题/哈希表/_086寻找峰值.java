package _04LeetCode精选TOP面试题.哈希表;

/**
 * https://leetcode.cn/problems/find-peak-element/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _086寻找峰值 {
    class Solution {
        public int findPeakElement(int[] nums) {
            if (nums.length < 2) return 0;
            // [0] > [1]
            if (nums[0] > nums[1]) return 0;
            // [N - 1] > [N - 2]
            if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;
            int l = 1;
            int r = nums.length - 2;
            while (l < r) {
                int m = (l + r) >> 1;
                if (nums[m - 1] < nums[m] && nums[m] > nums[m + 1]) return m;
                // [0] < [1] && [m - 1] > [m] 中间必有最高点
                if (nums[m - 1] > nums[m]) r = m - 1;
                    // [m] < [m + 1] && [N - 2] > [N - 1] 中间必有最高点
                else l = m + 1;
            }
            return l;
        }
    }
}
