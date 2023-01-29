package _04LeetCode精选TOP面试题.双指针;

/**
 * https://leetcode.cn/problems/sort-colors/?favorite=2ckc81c
 * 
 * Created by huangjunyi on 2022/10/30.
 */
public class _045颜色分类 {
    public void sortColors(int[] nums) {
        // 荷兰国旗问题
        int less = -1;
        int more = nums.length;
        int index = 0;
        while (index < more) {
            if (nums[index] == 1) {
                index++;
            } else if (nums[index] == 0) {
                swap(nums, index++, ++less);
            } else if (nums[index] == 2) {
                swap(nums, index, --more);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
