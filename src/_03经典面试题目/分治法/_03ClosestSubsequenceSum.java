package _03经典面试题目.分治法;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://leetcode.cn/problems/closest-subsequence-sum/
 *
 * 给你一个整数数组 nums 和一个目标值 goal 。
 * 你需要从 nums 中选出一个子序列，使子序列元素总和最接近 goal 。也就是说，如果子序列元素和为 sum ，你需要 最小化绝对差 abs(sum - goal) 。
 * 返回 abs(sum - goal) 可能的 最小值 。
 * 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
 *
 * Created by huangjunyi on 2022/12/24.
 */
public class _03ClosestSubsequenceSum {
    class Solution {
        public int minAbsDifference(int[] nums, int goal) {
            int length = nums.length;
            int m = length / 2;
            /*
            分治
            左右各一半
            左边每个位置要和不要，收集一个有序表
            右边每个位置要和不要，收集一个有序表
            左边有序表找最接近的
            右边有序表找最接近的
            左边每个key和右边配对，找最接近的
             */
            TreeSet<Integer> leftSet = new TreeSet<>();
            TreeSet<Integer> rightSet = new TreeSet<>();
            process(nums, 0, m, 0, leftSet);
            process(nums, m + 1, nums.length - 1, 0, rightSet);
            Integer leftFoolr = leftSet.floor(goal);
            Integer leftCeiling = leftSet.ceiling(goal);
            Integer rightFloor = rightSet.floor(goal);
            Integer rightCeiling = rightSet.ceiling(goal);
            int res = Integer.MAX_VALUE;
            if (leftFoolr != null) res = Math.min(res, Math.abs(goal - leftFoolr));
            if (leftCeiling != null) res = Math.min(res, Math.abs(goal - leftCeiling));
            if (rightFloor != null) res = Math.min(res, Math.abs(goal - rightFloor));
            if (rightCeiling != null) res = Math.min(res, Math.abs(goal - rightCeiling));
            for (Integer leftSum : leftSet) {
                int diff = goal - leftSum;
                Integer floor = rightSet.floor(diff);
                if (floor != null) {
                    res = Math.min(res, Math.abs(goal - (leftSum + floor)));
                }
                Integer ceiling = rightSet.ceiling(diff);
                if (ceiling != null) {
                    res = Math.min(res, Math.abs(goal - (leftSum + ceiling)));
                }
            }
            return res;
        }

        private void process(int[] nums, int index, int R, int sum, TreeSet<Integer> set) {
            if (index > R) {
                set.add(sum);
                return;
            }
            process(nums, index + 1, R, sum, set);
            process(nums, index + 1, R, sum + nums[index], set);;
        }
    }
}
