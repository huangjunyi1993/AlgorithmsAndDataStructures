package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.cn/problems/number-of-longest-increasing-subsequence/
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _152最长递增子序列的个数 {
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            /*
            dp表含义
            长度为i+1的递增子序列，以k结尾的，有v个
             0   1   2   3
            --- --- --- ---
            1:3 2:3 5:3 6:3
            2:2 3:2
            4:1


            遍历nums数组
            在dp中找到firstKey >= nums[i] 最左侧的 map （二分）


            然后看：
            1、没找到 => 新增一个表 => 填表
            2、找到 => 填表


            填表：
            前一个表的firstKey的value，减去前一个表中大于等于nums[i]的key的value
            然后加上当前表中firstKey的value

            比如前一个表的firstKey是1，value为3，当前nums[i]为2
            前一个表中大于等于2的key为2，value为2，
            当前表的firstKey是3，value为2
            那么就是：3 - 2 + 2 = 3


            含义：
            前一个表的firstKey表示以长度i结尾的最小的的数位firstKey的，有value个
            那么当前表表示是长度i+1结尾的，所以value就累加进来了
            但是前一个表可能又大于等于nums[i]，那么要扣掉这些value，因为这些是不递增的，但是也算到前一个表的firstKey中了
            然后还有加上当前表的firstKey对应的value，因为该firstKey肯定是比nums[i]大的，所以要累加进来

            最后返回dp最后一个表的firstKey对应的value
             */
            List<TreeMap<Integer, Integer>> dp = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int l = 0;
                int r = dp.size() - 1;
                int find = -1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (dp.get(mid).firstKey() >= nums[i]) {
                        find = mid;
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
                if (find == - 1) {
                    dp.add(new TreeMap<>());
                    int index = dp.size() - 1;
                    TreeMap<Integer, Integer> curMap = dp.get(index);
                    int size = 1;
                    if (index > 0) {
                        TreeMap<Integer, Integer> preMap = dp.get(index - 1);
                        size = preMap.firstEntry().getValue();
                        if (preMap.ceilingKey(nums[i]) != null) {
                            size -= preMap.ceilingEntry(nums[i]).getValue();
                        }
                    }
                    curMap.put(nums[i], size);
                } else {
                    int index = find;
                    TreeMap<Integer, Integer> curMap = dp.get(find);
                    int size = 1;
                    if (index > 0) {
                        TreeMap<Integer, Integer> preMap = dp.get(index - 1);
                        size = preMap.firstEntry().getValue();
                        if (preMap.ceilingKey(nums[i]) != null) {
                            size -= preMap.ceilingEntry(nums[i]).getValue();
                        }
                    }
                    if (curMap.containsKey(nums[i])) {
                        curMap.put(nums[i], curMap.get(nums[i]) + size);
                    } else {
                        curMap.put(nums[i], curMap.firstEntry().getValue() + size);
                    }
                }
            }
            return dp.get(dp.size() - 1).firstEntry().getValue();
        }
    }
}
