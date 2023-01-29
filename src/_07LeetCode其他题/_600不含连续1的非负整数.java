package _07LeetCode其他题;

/**
 * https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 * Created by huangjunyi on 2023/1/24.
 */
public class _600不含连续1的非负整数 {
    class Solution {
        public int findIntegers(int n) {
            int i = 31;
            for (; i >= 0; i--) {
                if ((n & (1 << i)) != 0) break;
            }
            int[][][] dp = new int[2][2][i + 1];
            return process(0, false, i, n, dp);
        }

        /**
         * 前面一个数位做的决定是pre(0/1)，是否已经比num小（flag），现在来到了index位，
         * 从index位开始往后做决定，返回二进制数位1不相邻的数的个数
         * @param pre 前一个数位（index+1)做的决定
         * @param flag 是否已经比num小，true是已经小于num，后面可以随意做决定，false表示还没有小于num
         * @param index 当前来到的数位
         * @param num 题目给定的正整数n
         * @param dp 缓存表
         * @return 二进制数位1不相邻的数的个数
         */
        private int process(int pre, boolean flag, int index, int num, int[][][] dp) {
            if  (index == -1) return 1;
            if (dp[pre][flag ? 1 : 0][index] != 0) return dp[pre][flag ? 1 : 0][index];
            int res = 0;
            if (pre == 1) {
                // 前面是1，当前为只能填0
                // flag | (num & (1 << index)) != 0
                // 已经比num小了，或者num的index位为1，后面的就都是比num小，true
                res += process(0, flag | (num & (1 << index)) != 0, index - 1, num, dp);
            } else {
                // 前面是1，当前为能填0也能填1，但是要保证不能比num大
                // 填0的决定，和上面一样
                res += process(0, flag | (num & (1 << index)) != 0, index - 1, num, dp);
                // 填1前，先判断会不会超
                if (flag | (num & (1 << index)) != 0) {
                    res += process(1, flag,index - 1, num, dp);
                }
            }
            dp[pre][flag ? 1 : 0][index] = res;
            return res;
        }
    }
}
