package _04LeetCode精选TOP面试题.二分法;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/25.
 */
public class _024在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int low = -1;
        int high = -1;
        int l = 0;
        int r = nums.length - 1;
        // 寻找左边界，命中target也不break，二分到死
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                low = mid;
                r = mid - 1;
            }
        }
        l = 0;
        r = nums.length - 1;
        // 寻找右边界，命中target也不break，二分到死
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                high = mid;
                l = mid + 1;
            }
        }
        return new int[]{low, high};
    }
}
