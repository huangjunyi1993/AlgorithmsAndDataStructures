package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/longest-increasing-subsequence/?favorite=2ckc81c
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 解法：
 * 1、ends[i]长度为i+1的递增子序列的最小结尾
 * 2、二分找刚好大于等于num[i]的ends[i]
 *
 * Created by huangjunyi on 2022/11/7.
 */
public class _129最长递增子序列 {
    class Solution {
        public int lengthOfLIS(int[] nums) {
            // ends[i]长度为i+1的递增子序列的最小结尾
            int[] ends = new int[nums.length];
            ends[0] = nums[0];
            int rightEdge = 0; // ends当前开辟到的最右边界，+1就是目前发现的最长递增子序列
            /*
            每一个位置
            二分找刚好大于等于num[i]的ends[i]
            尝试更新这个ends[i]
            如果找不到，代表nums[i]现在是最大，开辟一个ends[i]
             */
            for (int i = 1; i < nums.length; i++) {
                int l = 0;
                int r = rightEdge;
                int p = rightEdge + 1;
                while (l <= r) {
                    int m = l + ((r - l) >> 1);
                    if (ends[m] >= nums[i]) {
                        p = m;
                        r = m - 1;
                    }
                    else l = m + 1;
                }
                if (p == rightEdge + 1) {
                    ends[p] = nums[i];
                    rightEdge = rightEdge + 1;
                } else {
                    ends[p] = Math.min(ends[p], nums[i]);
                }
            }
            return rightEdge + 1;
        }
    }

}
