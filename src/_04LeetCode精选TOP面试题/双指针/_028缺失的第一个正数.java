package _04LeetCode精选TOP面试题.双指针;

/**
 * https://leetcode.cn/problems/first-missing-positive/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/26.
 */
public class _028缺失的第一个正数 {
    public int firstMissingPositive(int[] nums) {
        /*
        * l指针：代表0~l-1范围内，每一个位置的数满足l+1
        * r指针：代表现在最右预期锁缺少的正整数是r+1，比如nums长度12，那么一开始r就是12，代表预期0~11位置就是1~12，然后缺13
        * */
        int l = 0;
        int r = nums.length;
        while (l < r) {
            // 如果nums[l]刚好等于l+1，符合l指针的定义，l指针往右移
            if (nums[l] == l + 1) {
                l++;
            }
            /*
             以下三种情况不符合r指针的最优预期，
             nums[l] <= l 代表出现重复数字，
             nums[l] > r 代表出现超出预期的数
             nums[nums[l] - 1] == nums[l] 也是出现重复数字
              */
            else if (nums[l] <= l || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
                swap(nums, l, --r);
            }
            // 预期中的数，交换到预期的位置
            else {
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
