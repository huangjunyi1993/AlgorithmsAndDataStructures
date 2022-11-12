package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/move-zeroes/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _123移动零 {
    class Solution {
        public void moveZeroes(int[] nums) {
            int l = -1;
            for (int r = 0; r < nums.length; r++) {
                // 不是零的数，把它交换到有序区，是零的不处理，最后所有的零都会被挤到右边
                if (nums[r] != 0) {
                    swap(nums, ++l, r);
                }
            }
        }

        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }
}
