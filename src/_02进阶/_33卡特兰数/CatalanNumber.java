package _02进阶._33卡特兰数;

/**
 *  k(0) = 1, k(1) = 1
 *
 *  满足下面三个公式的其中一个的数列，都是卡特兰数，它们是等价的：
 *
 *	k(n) = k(0) * k(n - 1) + k(1) * k(n - 2) + ... + k(n - 2) * k(1) + k(n - 1) * k(0)
 *	或者
 *	k(n) = c(2n, n) / (n + 1)
 *	或者
 *	k(n) = c(2n, n) - c(2n, n-1)
 *
 * Created by huangjunyi on 2022/12/9.
 */
public class CatalanNumber {
    public static long num1(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int leftSize = 0; leftSize < i; leftSize++) {
                dp[i] += dp[leftSize] * dp[i - 1 - leftSize];
            }
        }
        return dp[N];
    }
}
