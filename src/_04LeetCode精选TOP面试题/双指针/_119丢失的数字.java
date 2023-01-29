package _04LeetCode精选TOP面试题.双指针;

/**
 * https://leetcode.cn/problems/missing-number/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _119丢失的数字 {
    class Solution {
        public int missingNumber(int[] nums) {
            /*
            初始化左右指针
            l表示已经搞定的在范围内的数，
            比如现在l==2
            那么表示
            nums[0]==0
            nums[1]==1
            就是l左边都是有序且未缺失的数
            r以及r往右表示垃圾区
            n==len表示，预期nums内是0~nums.len-1，缺失nums.len
             */
            int l = 0;
            int r = nums.length;
            while (l < r) {
                if (nums[l] == l) {
                    l++;
                }
                // 发现垃圾数，发送垃圾区，垃圾区往左扩，预期缺失的数缩小
                else if (nums[l] < l || nums[l] >= r || nums[l] == nums[nums[l]]) {
                    swap(nums, l, --r);
                }
                // 把数发到对应的位置上
                else {
                    swap(nums, l, nums[l]);
                }
            }
            return l;
        }

        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }
}
