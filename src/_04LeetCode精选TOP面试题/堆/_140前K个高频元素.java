package _04LeetCode精选TOP面试题.堆;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/top-k-frequent-elements/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _140前K个高频元素 {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            /*
            大根堆 + 频率统计表（key：数，value：出现的次数）
            堆没满（k），或满了但是能干掉堆顶，则入堆
             */
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (countMap.containsKey(nums[i])) {
                    countMap.put(nums[i], countMap.get(nums[i]) + 1);
                } else {
                    countMap.put(nums[i], 1);
                }
            }
            PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(countMap::get));
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                Integer num = entry.getKey();
                Integer count = entry.getValue();
                if (heap.size() < k || countMap.get(heap.peek()) < count) heap.add(num);
                if (heap.size() > k) heap.poll();
            }
            int[] res = new int[heap.size()];
            int index = 0;
            while (!heap.isEmpty()) {
                res[index++] = heap.poll();
            }
            return res;
        }
    }
}
