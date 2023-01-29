package _04LeetCode精选TOP面试题.二分法;

/**
 * https://leetcode.cn/problems/increasing-triplet-subsequence/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _136递增的三元子序列 {
    class Solution {
        public boolean increasingTriplet(int[] nums) {
            int[] ends = new int[3];
            /*
            寻找最长递增子序列的做法：
            ends[i]表示长度为i的最长递增子序列，的最小结尾是ends[i]
            然后遍历nums中每个数
            在ends上二分查找小于nums[i]又最接近的结尾的下一个位置
            就是以nums[i]结尾的最长子序列长度
             */
            ends[0] = nums[0];
            int right = 0;
            for (int i = 1; i < nums.length; i++) {
                int l = 0;
                int r = right;
                while (l <= r) {
                    int mid = l + ((r - l) >> 1);
                    if (ends[mid] >= nums[i]) r = mid - 1;
                    else l = mid + 1;
                }
                right = Math.max(right, l);
                if (right > 1) return true;
                ends[l] = nums[i];
            }
            return false;
        }
    }
}
