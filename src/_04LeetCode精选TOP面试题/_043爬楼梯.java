package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/climbing-stairs/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _043爬楼梯 {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int a = 1;
        int b = 2;
        // 斐波那契数列
        for (int i = 3; i <= n; i++) {
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return b;
    }
}
