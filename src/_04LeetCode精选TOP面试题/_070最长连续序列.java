package _04LeetCode精选TOP面试题;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/
 * Created by huangjunyi on 2022/12/16.
 */
public class _070最长连续序列 {
    class Solution {
        public int longestConsecutive(int[] nums) {
            int max = 0;
            // 数字（开头、结尾） => 长度
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                if (!map.containsKey(num)) {
                    map.put(num, 1);

                    // 连接上num-1作为尾的，和num+1作为头的
                    int preLen  = map.getOrDefault(num - 1, 0);
                    int postLen = map.getOrDefault(num + 1, 0);
                    // 连接后的总长度
                    int all = preLen + 1 + postLen;
                    // 更新新头尾对应的新长度
                    map.put(num - preLen,  all);
                    map.put(num + postLen, all);
                    // 新长度PK一下
                    max = Math.max(max, all);
                }
            }
            return max;
        }
    }
}
