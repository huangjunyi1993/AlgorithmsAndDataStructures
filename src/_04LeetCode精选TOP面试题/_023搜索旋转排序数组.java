package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 *
 * Created by huangjunyi on 2022/10/25.
 */
public class _023搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            // 拐点在左侧 [789123456]，右侧有序
            if (nums[l] > nums[mid]) {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            // 拐点值右侧 [345697812]，左侧有序
            else if (nums[mid] > nums[r]) {
                if (target < nums[mid] && target >= nums[l]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // 已经进入有序区域
            else {
                if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }
}
