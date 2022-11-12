package _04LeetCode精选TOP面试题;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/max-points-on-a-line/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/4.
 */
public class _081直线上最多的点数 {
    class Solution {
        public int maxPoints(int[][] points) {
            int result = 0;
            int N = points.length;
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            /*
            遍历每个起始点
            每个起始点，往后统计，有多少个共x，共y，共斜率的
            每步收一个答案
             */
            for (int i = 0; i < N; i++) {
                map.clear();
                int[] curPoint = points[i];
                int curX = curPoint[0];
                int curY = curPoint[1];
                int samePoint = 1;
                int line = 0;
                int sameX = 0;
                int sameY = 0;
                for (int j = i + 1; j < N; j++) {
                    int[] point = points[j];
                    int X = point[0];
                    int Y = point[1];
                    if (X == curX && Y == curY) samePoint++;
                    else if (X == curX) sameX++;
                    else if (Y == curY) sameY++;
                    else {
                        int diffX = X - curX;
                        int diffY = Y - curY;
                        int gcd = gcd(diffX, diffY);
                        diffX /= gcd;
                        diffY /= gcd;
                        if (map.containsKey(diffX)) {
                            if (map.get(diffX).containsKey(diffY)) {
                                Map<Integer, Integer> subMap = map.get(diffX);
                                subMap.put(diffY, subMap.get(diffY) + 1);
                            } else {
                                map.get(diffX).put(diffY, 1);
                            }
                        } else {
                            Map<Integer, Integer> subMap = new HashMap<>();
                            subMap.put(diffY, 1);
                            map.put(diffX, subMap);
                        }
                        line = Math.max(line, map.get(diffX).get(diffY));
                    }
                }
                result = Math.max(result, Math.max(line, Math.max(sameX, sameY)) + samePoint);
            }
            return result;
        }

        private int gcd(int x, int y) {
            return y == 0 ? x : gcd(y, x % y);
        }
    }
}
