package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/powx-n/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _034Pow {
    public double myPow(double x, int n) {
        // 任何数的0次幂，都是1
        if (n == 0) return 1;
        int pow = Math.abs(n);
        // 因为系统最小次方转不出正数，所以先+1，然后转成正数
        if (n == Integer.MIN_VALUE) pow = Math.abs(n + 1);
        /*
        计算方法：
        t最开始等于x，然后每次循环都是t = t * t
        pow每次右移一位，如果pow与1不等于0，则t乘到res中
         */
        double t = x;
        double res = 1;
        while (pow != 0) {
            if ((pow & 1) != 0) res *= t;
            pow >>= 1;
            t = t * t;
        }
        // 系统最小次方，那么在乘一个x
        if (n == Integer.MIN_VALUE) res *= x;
        return n < 0 ? 1 / res : res;
    }
}
