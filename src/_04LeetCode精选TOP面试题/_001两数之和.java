package _04LeetCode精选TOP面试题;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/two-sum/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/22.
 */
public class _001两数之和 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numIndexMap.containsKey(target - num)) {
                return new int[] {numIndexMap.get(target - num), i};
            }
            numIndexMap.put(num, i);
        }
        return new int[] {-1, -1};
    }
}
