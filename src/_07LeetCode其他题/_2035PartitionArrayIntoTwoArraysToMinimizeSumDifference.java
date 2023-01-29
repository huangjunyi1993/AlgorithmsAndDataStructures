package _07LeetCode其他题;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * https://leetcode.cn/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
 * Created by huangjunyi on 2023/1/21.
 */
public class _2035PartitionArrayIntoTwoArraysToMinimizeSumDifference {
    class Solution {
        public int minimumDifference(int[] nums) {
            /*
            分治，左边一张表，右边一张表
            左右表弄好后
            然后遍历左边每条数据
            跟右表匹配，匹配出一个最接近数组累加和一半的值
            然后算出答案
            然后每个答案PK一下
             */
            HashMap<Integer, TreeSet<Integer>> lMap = new HashMap<>();
            HashMap<Integer, TreeSet<Integer>> rMap = new HashMap<>();
            int mid = nums.length >> 1;
            process(nums, 0, mid, lMap, 0, 0);
            process(nums, mid, nums.length, rMap, 0, 0);
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            int halfSum = sum >> 1;
            int halfLen = nums.length >> 1;
            int res = Integer.MAX_VALUE;
            for (Integer lPick : lMap.keySet()) {
                for (Integer lSum : lMap.get(lPick)) {
                    Integer rSum = rMap.get(halfLen - lPick).floor(halfSum - lSum);
                    if (rSum != null) {
                        res = Math.min(res, sum - (lSum + rSum) - (lSum + rSum));
                    }
                }
            }
            return res;
        }

        private void process(int[] nums, int index,
                             int end, HashMap<Integer, TreeSet<Integer>> map,
                             int pick, int sum) {
            if (index == end) {
                if (!map.containsKey(pick)) map.put(pick, new TreeSet<>());
                map.get(pick).add(sum);
                return;
            }
            process(nums, index + 1, end, map, pick, sum);
            process(nums, index + 1, end, map, pick + 1, sum + nums[index]);
        }
    }
}
