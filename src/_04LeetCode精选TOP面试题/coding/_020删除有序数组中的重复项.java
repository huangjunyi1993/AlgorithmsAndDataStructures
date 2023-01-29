package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _020删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int done = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[done] != nums[i]) {
                nums[++done] = nums[i];
            }
        }
        return done + 1;
    }
}
