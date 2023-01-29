package _07LeetCode其他题;

/**
 * https://leetcode.cn/problems/next-permutation/description/
 *
 * Created by huangjunyi on 2023/1/19.
 */
public class _31下一个排列 {
    class Solution {
        public void nextPermutation(int[] nums) {
            // 从右往左遍历，找到第一个降序的数
            int firstSmallIndex = -1;
            int preNum = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < preNum) {
                    firstSmallIndex = i;
                    break;
                }
                preNum = nums[i];
            }
            // 如果从右往左，一直都是升序，那么整体数组逆序
            if (firstSmallIndex == -1) {
                reverse(nums, 0, nums.length - 1);
                return;
            }
            // 否则，再次再次从右往左遍历，找到第一个刚刚大于该降序数的数
            // 然后两数交换位置
            // 然后降序数原先所在的位置的后面，整体逆序
            for (int i = nums.length - 1; i > firstSmallIndex; i--) {
                if (nums[i] > nums[firstSmallIndex]) {
                    swap(nums, firstSmallIndex, i);
                    reverse(nums, firstSmallIndex + 1, nums.length - 1);
                    return;
                }
            }
        }

        private void reverse(int[] nums, int l, int r) {
            while (l < r) swap(nums, l++, r--);
        }

        private void swap(int[] nums, int l, int r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
    }
}
