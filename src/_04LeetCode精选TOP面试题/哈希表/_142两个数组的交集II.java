package _04LeetCode精选TOP面试题.哈希表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/intersection-of-two-arrays-ii/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _142两个数组的交集II {
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            /*
            用两个map分别统计两个数组
            然后再合并就行了
             */
            HashMap<Integer, Integer> map1 = new HashMap<>();
            HashMap<Integer, Integer> map2 = new HashMap<>();
            for (int i = 0; i < nums1.length; i++) {
                if (map1.containsKey(nums1[i])) {
                    map1.put(nums1[i], map1.get(nums1[i]) + 1);
                } else {
                    map1.put(nums1[i], 1);
                }
            }
            for (int i = 0; i < nums2.length; i++) {
                if (map2.containsKey(nums2[i])) {
                    map2.put(nums2[i], map2.get(nums2[i]) + 1);
                } else {
                    map2.put(nums2[i], 1);
                }
            }
            List<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
                if (map2.containsKey(entry.getKey())) {
                    int len = Math.min(entry.getValue(), map2.get(entry.getKey()));
                    for (int i = 0; i < len; i++) {
                        list.add(entry.getKey());
                    }
                }
            }
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }
    }
}
