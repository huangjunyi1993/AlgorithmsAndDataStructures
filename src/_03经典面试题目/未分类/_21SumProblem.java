package _03经典面试题目.未分类;

import java.util.Arrays;

public class _21SumProblem {

    /**
     * 给定一个整数数组arr
     * 返回arr的自己不能累加出的最小整数
     * 正常怎么做？ （有1走和下面一样，没有1返回1）
     * 如果arr肯定有1这个值，怎么做？
     */
    public static int unformedSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        Arrays.sort(arr);
        // range 当前能搞定的数的范围，1~range都能搞定
        int range = 0;
        for (int num : arr) {
            // 如果num > range + 1，那么range+1就永远搞定不了了，因为arr是排过序的
            if (num > range + 1) {
                return range + 1;
            } else {
                range += num;
            }
        }
        return range + 1;
    }

    /**
     * https://leetcode.cn/problems/patching-array/
     */
    class Solution {
        public int minPatches(int[] nums, int n) {
            // range 当前能搞定的数的范围，1~range都能搞定
            long range = 0;
            // 要补充的数的个数
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                // 最小代价使用 nums[i]这个数
                while (range < nums[i] - 1) {
                    range += range + 1;
                    res++;
                    if (range >= n) return res;
                }
                range += nums[i];
                if (range >= n) return res;
            }
            // 还没能搞定n，自己加数
            while (range < n) {
                range += range + 1;
                res++;
            }
            return res;
        }
    }

}
