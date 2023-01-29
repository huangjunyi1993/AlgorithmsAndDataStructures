package _07LeetCode其他题;

import java.util.HashSet;

/**
 * https://leetcode.cn/problems/programmable-robot/
 *
 * Created by huangjunyi on 2023/1/26.
 */
public class LCP03机器人大冒险 {
    class Solution {
        public boolean robot(String command, int[][] obstacles, int x, int y) {
            /*
            1、用一个set，记录一轮里面走的每一步的记录，高10为记录x轴方向上往右走的距离，低10位记录y轴方向上向上走的距离
            2、一个meet函数，判断是否能到达指定的目的地
            3、先用meet函数看目标地点是否能到达，不能达到返回false
            4、在遍历每个障碍，用meet函数，看是否会撞到，撞到返回false
             */
            int X = 0;
            int Y = 0;
            HashSet<Integer> set = new HashSet<>();
            set.add(0);
            for (char c : command.toCharArray()) {
                X += c == 'R' ? 1 : 0;
                Y += c == 'U' ? 1 : 0;
                set.add((X << 10) | Y);
            }
            if (!meet(x, y, X, Y, set)) return false;
            for (int[] obstacle : obstacles) {
                if (obstacle[0] <= x && obstacle[1] <= y && meet(obstacle[0], obstacle[1], X, Y, set))
                    return false;
            }
            return true;
        }

        /**
         * 从(0,0)位置出发，是否能达到目标地点(x,y)
         * 一轮以内，一共会在x轴方向上往右走X步，在y轴方向上向上走Y步
         *
         * Math.min(x / X, y / Y) 得出最少要完整的走多少轮
         * 然后得出剩下的距离rx，ry
         * 看set中是否有(rx,ry)，如果有说明能到，否则到不了
         *
         * @param x 目标地点x轴坐标
         * @param y 目标地点y轴坐标
         * @param X 一轮以内，一共会在x轴方向上往右走X步
         * @param Y 一轮以内，一共会在y轴方向上向上走Y步
         * @param set 一轮里面走的每一步的记录
         * @return 是否能达到目标地点(x,y)
         */
        private boolean meet(int x, int y, int X, int Y, HashSet<Integer> set) {
            if (X == 0) return x == 0;
            if (Y == 0) return y == 0;
            int round = Math.min(x / X, y / Y);
            int rx = x - round * X;
            int ry = y - round * Y;
            return set.contains((rx << 10) |ry);
        }
    }
}
