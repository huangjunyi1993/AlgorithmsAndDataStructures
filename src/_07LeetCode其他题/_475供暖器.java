package _07LeetCode其他题;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/heaters/
 * Created by huangjunyi on 2023/1/22.
 */
public class _475供暖器 {
    class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            /*
            贪心：
            把房子和火炉从小打到排序
            每次都为每个房子挑一个最接近的火炉
            每个答案PK一下
             */
            Arrays.sort(houses);
            Arrays.sort(heaters);
            int res = Integer.MIN_VALUE;
            for (int i = 0, j = 0; i < houses.length; i++) {
                while (!best(houses, heaters, i, j)) j++;
                res = Math.max(res, Math.abs(heaters[j] - houses[i]));
            }
            return res;
        }

        private boolean best(int[] houses, int[] heaters, int i, int j) {
            return j == heaters.length - 1
                    // 这里必须小于，如果小于等于，会存在被卡死的情况
                    || Math.abs(heaters[j] - houses[i]) < Math.abs(heaters[j + 1] - houses[i]);
        }
    }
}
