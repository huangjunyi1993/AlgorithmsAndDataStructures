package _01基础._05归并排序;

/**
 * https://leetcode.cn/problems/count-of-range-sum/
 * 力扣327题
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 *
 * Created by huangjunyi on 2022/11/19.
 */
public class MergeSort06 {
    class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {

            /*
            思路：
            1、求一个子数组的累加和，例如[l...r]，相当于求sum[r] - sum[l - 1]，sum是前缀和数组
            2、可以以每个数作为结尾，看有多少个子数组达标
            3、接着2，转换一下思维，以nums[i]=x为结尾有多少个子数组达标，其实等于[0...i-1]有多少个数组前缀和在[x-upper, x-lower]上
            4、接着3，先生成前缀和数组sum，再对sum进行merge排序，在每一个merge前，去搞步骤3的事情，搞完在merge
            5、每一个merge前搞的事情就是，遍历右组每一个数，看左组在[x-upper, x-lower]上的个数有多少个
             */

            // 求出前缀和数组
            int N = nums.length;
            long[] sum = new long[N];
            sum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum[i] = sum[i - 1] + nums[i];
            }
            // 利用前缀和数组 + merge排序，求出达标的子数组个数
            return process(sum, 0, N - 1, lower, upper);
        }

        /**
         * 求在l到r的范围上，有多少个累加和在[lower, upper]范围上的子数组
         * @param sum 前缀和数组
         * @param l 左边界
         * @param r 右边界
         * @param lower 范围下限
         * @param upper 范围上限
         * @return
         */
        private int process(long[] sum, int l, int r, int lower, int upper) {
            // 这个是检查0...l这个子数组是否达标
            if (l == r) return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
            int m = l + ((r - l) >> 1);
            return process(sum, l, m, lower, upper)
                    + process(sum, m + 1, r, lower, upper)
                    + merge(sum, l, m, r, lower, upper);
        }

        /**
         * merge左右两侧子数组，使其整体有序，
         * 并返回对与右组中的每一个数而言，左组中有多少个数在[x - upper, x - lower]范围上
         * @param sum 前缀和数组
         * @param l 左边界
         * @param m 中点
         * @param r 右边界
         * @param lower 范围下限
         * @param upper 范围上限
         * @return
         */
        private int merge(long[] sum, int l, int m, int r, int lower, int upper) {
            // [windowL, windowR)，[x-upper, x-lower]的个数等于windowR - windowL
            int windowL = l; // 滑到大于等于x-upper的时候停
            int windowR = l; // 划出x-lower的时候停
            int res = 0;
            for (int i = m + 1; i <= r; i++) {
                long min = sum[i] - upper; // x-upper
                long max = sum[i] - lower; // x-lower
                while (windowR <= m && sum[windowR] <= max) windowR++;
                while (windowL <= m && sum[windowL] < min) windowL++;
                res += windowR - windowL; // 累加结果
            }

            long[] help = new long[r - l + 1];

            int left = l;
            int right = m + 1;
            int index = 0;
            while (left <= m && right <= r) {
                if (sum[left] <= sum[right]) {
                    help[index++] = sum[left++];
                } else {
                    help[index++] = sum[right++];
                }
            }
            while (left <= m) {
                help[index++] = sum[left++];
            }
            while (right <= r) {
                help[index++] = sum[right++];
            }
            index = 0;
            for (int i = l; i <= r; i++) {
                sum[i] = help[index++];
            }
            return res;
        }
    }
}
