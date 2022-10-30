package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/merge-sorted-array/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _050合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n;
        /*
        从有往左填
        比较两个数组
        先填较大的
         */
        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[--index] = nums1[m - 1];
                m--;
                continue;
            } else {
                nums1[--index] = nums2[n - 1];
                n--;
            }
        }
        while (m > 0) {
            nums1[--index] = nums1[m - 1];
            m--;
        }
        while (n > 0) {
            nums1[--index] = nums2[n - 1];
            n--;
        }
    }
}
