package _07LeetCode其他题;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/arithmetic-slices-ii-subsequence/
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 * Created by huangjunyi on 2023/1/23.
 */
public class _446等差数列划分II子序列 {
    class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;
            int res = 0;
            /*
            等差数列计数表
            maps[i]
            以下标为i的数结尾，
            差值为key的，
            长度大于等于2的等差数列有几个
             */
            HashMap<Integer, Integer>[] maps = new HashMap[n];
            for (int i = 0; i < n; i++) {
                maps[i] = new HashMap<>();
                for (int j = i - 1; j >= 0; j--) {
                    long diff = (long) nums[i] - (long) nums[j];
                    if (diff <= Integer.MIN_VALUE || diff >= Integer.MAX_VALUE) continue;
                    int dif = (int) diff;
                    Integer count = maps[j].getOrDefault(dif, 0);
                    res += count;
                    maps[i].put(dif, maps[i].getOrDefault(dif, 0) + count + 1);
                }
            }
            return res;
        }
    }
}
