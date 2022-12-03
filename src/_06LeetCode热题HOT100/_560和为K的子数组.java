package _06LeetCode热题HOT100;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/subarray-sum-equals-k/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _560和为K的子数组 {
    class Solution {
        public int subarraySum(int[] nums, int k) {
            // 前缀和出现了几次
            HashMap<Integer, Integer> preSumCountMap = new HashMap();
            int all = 0; // 当前前缀和
            preSumCountMap.put(all, 1); // 0的前缀和出现1次
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                all += nums[i];
                // 当前减去k，出现了几次，就有几个达标的子数组
                if (preSumCountMap.containsKey(all - k)) {
                    res += preSumCountMap.get(all - k);
                }
                // 更新前缀和出现次数
                if (preSumCountMap.containsKey(all)) {
                    preSumCountMap.put(all, preSumCountMap.get(all) + 1);
                } else {
                    preSumCountMap.put(all, 1);
                }
            }
            return res;
        }
    }
}
