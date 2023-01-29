package _04LeetCode精选TOP面试题.双指针;

/**
 * https://leetcode.cn/problems/rotate-array/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _093轮转数组 {
    class Solution {
        public void rotate(int[] nums, int k) {
            // k大于数组长度时，取模
            k = k % nums.length;
            int ll = 0;
            int lr = nums.length - k - 1;
            int rl = nums.length - k;
            int rr = nums.length - 1;
            // 左侧交换
            while (ll < lr) swap(nums, ll++, lr--);
            // 右侧交换
            while (rl < rr) swap(nums, rl++, rr--);
            int l = 0;
            int r = nums.length - 1;
            // 整体交换
            while (l < r) swap(nums, l++, r--);
        }

        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }
}
