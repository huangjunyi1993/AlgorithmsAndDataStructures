package _03经典面试题目.预处理数组;

/**
 * https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/description/
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且全部数字和（3 * k 项）最大的子数组，并返回这三个子数组。
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 *
 * Created by huangjunyi on 2023/1/8.
 */
public class _08MaximumSumOf3NonOverLappingSubArrays {
    class Solution {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

            // rangeSum[i]：i为开头，长度为k的子数组范围累加和
            int n = nums.length;
            int[] rangeSum = new int[n];
            int sum = 0;
            for (int i = 0; i < k - 1; i++) {
                sum += nums[i];
            }
            for (int i = k - 1; i < n; i++) {
                sum += nums[i];
                rangeSum[i - k + 1] = sum;
                sum -= nums[i - k + 1];
            }

            // left[i]：0~i位置，长度为k的子数组的累加和最大的子数组，的起始下标
            int[] left = new int[n];
            left[k - 1] = 0;
            int max = rangeSum[0];
            for (int i = k; i < n; i++) {
                left[i] = left[i - 1];
                if (rangeSum[i - k + 1] > max) {
                    left[i] = i - k + 1;
                    max = rangeSum[i - k + 1];
                }
            }

            // right[i]：i~n-1位置，长度为k的子数组的累加和最大的子数组，的起始下标
            int[] right = new int[n];
            right[n - k] = n - k;
            max = rangeSum[n - k];
            for (int i = n - k - 1; i >= 0; i--) {
                right[i] = right[i + 1];
                if (rangeSum[i] >= max) {
                    right[i] = i;
                    max = rangeSum[i];
                }
            }

            // 长度为k的窗口 遍历
            // 在确定中间长度为k的子数组的情况下
            // 窗口左边的最大子数组
            // 窗口右边的最大子数组
            // 3个子数组累加和相加
            // 所有可能的情况PK，挑最好的
            max = Integer.MIN_VALUE;
            int a = 0;
            int b = 0;
            int c = 0;
            for (int i = k; i < n - 2 * k + 1; i++) {
                int leftSum = rangeSum[left[i - 1]];
                int midSum = rangeSum[i];
                int rightSum = rangeSum[right[i + k]];
                if (leftSum + midSum + rightSum > max) {
                    max = leftSum + midSum + rightSum;
                    a = left[i - 1];
                    b = i;
                    c = right[i + k];
                }
            }
            return new int[]{a, b, c};
        }
    }
}
