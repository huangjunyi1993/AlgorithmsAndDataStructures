package _04LeetCode精选TOP面试题.二分法;

/**
 * https://leetcode.cn/problems/sqrtx/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _042x的平方根 {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x < 3) return 1;
        int res = 1;
        /*
        利用二分法求解
        逐步接近最优解
         */
        long l = 1;
        long r = x;
        long m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (m * m <= x) {
                res = (int) m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }
}
