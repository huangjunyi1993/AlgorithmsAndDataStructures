package _03经典面试题目.双指针;

/**
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/description/
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * Created by huangjunyi on 2022/12/20.
 */
public class _02MinLengthForSort {
    class Solution {
        public int findUnsortedSubarray(int[] nums) {
            /*
            左侧全局最小且有序，不用排
            右侧全局最大且有序，不用排
            只需排中间的部分
             */

            int leftMax = nums[0]; // 左边最大值（不含当前位置）
            int noSortRight = -1; // 待排序子数组最右侧
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < leftMax) noSortRight = i; // 当前位置的数小于左边最大值，更新待排序子数组最右侧
                if (nums[i] > leftMax) leftMax = nums[i];
            }
            int rightMin = nums[nums.length - 1]; // 右边最小值（不含当位置）
            int noSortLeft = nums.length; // 待排序子数组最左侧位置
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < rightMin) rightMin = nums[i]; // 当前位置的数大于右边最小值，更新待排序子数组最左侧
                if (nums[i] > rightMin) noSortLeft = i;
            }
            return noSortRight > noSortLeft ? noSortRight - noSortLeft + 1 : 0;
        }
    }
}
