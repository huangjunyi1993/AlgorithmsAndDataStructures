package _04LeetCode精选TOP面试题;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/4sum-ii/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/9.
 */
public class _150四数相加II {
    class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            // 每个nums1和nums2的和统计到map
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    int sum = nums1[i] + nums2[j];
                    if (!map.containsKey(sum)) map.put(sum, 1);
                    else map.put(sum, map.get(sum) + 1);
                }
            }
            // 遍历每一个nums3和nums4的和，看在map是否有负的与之对应，累加到结果中
            int res = 0;
            for (int i = 0; i < nums3.length; i++) {
                for (int j = 0; j < nums4.length; j++) {
                    int sum = nums3[i] + nums4[j];
                    if (map.containsKey(-sum)) res += map.get(-sum);
                }
            }
            return res;
        }
    }
}
