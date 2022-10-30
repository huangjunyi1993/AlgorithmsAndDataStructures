package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/unique-paths/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _040不同路径 {
    public int uniquePaths(int m, int n) {
        int all = (m + n - 2); // 总步数
        /*
        假设c(9,3) all = 9, m - 1 = 3
        c(9, 3) = 9!/3!*6* = all!/(m-1)!(n-1)!
        all! = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9
        (m-1)! = 1 * 2 * 3
        (n-1)! = 1 * 2 * 3 * 4 * 5 * 6
        all!/(m-1)!(n-1)! = (7 * 8 * 9) / (1 * 2 * 3)
        简化为 a/b
        所以这里part = n - 1
        那么a就是part+1 * ... * all
        那么b就是1 * ... * (m-1)!
         */
        int part = n - 1;
        long a = 1;
        long b = 1;
        for (int i = part + 1, j = 1; i <= all || j <= (m - 1); i++, j++) {
            a *= i;
            b *= j;
            long gcd = gcd(a, b);
            a /= gcd;
            b /= gcd;
        }
        return (int) a;
    }

    private long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
