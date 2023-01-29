package _07LeetCode其他题;

/**
 * https://leetcode.cn/problems/koko-eating-bananas/
 */
public class _875爱吃香蕉的珂珂 {
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            /*
            二分
            L速度1
            R速度piles[max]
            二分到死
            尝试最慢的达标速度
             */
            int L = 1;
            int R = 0;
            for (int pile : piles) {
                R = Math.max(R, pile);
            }
            int ans = 0;
            int M = 0;
            while (L <= R) {
                M = L + ((R - L) >> 1);
                if (hours(piles, M) <= h) {
                    ans = M;
                    R = M - 1;
                } else {
                    L = M + 1;
                }
            }
            return ans;
        }

        /**
         * 给定一堆香蕉piles，给定一个速度
         * @param piles 香蕉堆
         * @param speed 速度
         * @return 耗时
         */
        public long hours(int[] piles, int speed) {
            long ans = 0;
            int offset = speed - 1;
            for (int pile : piles) {
                ans += (pile + offset) / speed;
            }
            return ans;
        }
    }
}
