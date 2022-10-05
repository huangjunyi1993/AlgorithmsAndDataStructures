package _03经典面试题目.动态规划;

/**
 * 现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，后n2种为纪念币，
 * 每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值?
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _022MoneyWays {

    public static int moneyWays(int[] arbitrary, int[] onlyone, int money) {
        if (money < 0) return 0;
        if ((arbitrary == null || arbitrary.length == 0) && (onlyone == null || onlyone.length == 0)) return money == 0 ? 1 : 0;

        int[][] dp1 = getDpArb(arbitrary, money);
        int[][] dp2 = getDpOne(onlyone, money);

        if (dp1 == null) return dp2[onlyone.length - 1][money];
        if (dp2 == null) return dp1[arbitrary.length - 1][money];

        /*
        两种dp表的最后一行，相同列相乘，然后结果累加
         */
        int res = 0;
        for (int i = 0; i < money; i++) {
            res += dp1[arbitrary.length - 1][i] * dp2[onlyone.length - 1][money - i];
        }
        return res;
    }

    /**
     * 计算纪念币的dp表
     *
     * dp[i][j]表示使用0~i号货币，凑出j元，有几种方法
     *
     * @param onlyone
     * @param money
     * @return
     */
    private static int[][] getDpOne(int[] onlyone, int money) {
        if (onlyone == null || onlyone.length == 0) return null;
        int N = onlyone.length;
        int[][] dp = new int[N][money + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        dp[0][onlyone[0]] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= money; j++) {
                /*
                i号货币不使用，看0~i-1号货币，凑出j元的方法数
                i号货币使用，看0~i-1号货币，凑出j-i号货币面值，的方法数
                两个结果相加
                 */
                dp[i][j] = dp[i - 1][j];
                if (j - onlyone[i] >= 0) dp[i][j] += dp[i - 1][j - onlyone[i]];
            }
        }
        return dp;
    }

    /**
     * 计算普通币的dp表
     *
     * dp[i][j]表示使用0~i号货币，凑出j元，有几种方法
     *
     * @param arbitrary
     * @param money
     * @return
     */
    private static int[][] getDpArb(int[] arbitrary, int money) {
        if (arbitrary == null || arbitrary.length == 0) return null;
        int N = arbitrary.length;
        int[][] dp = new int[N][money + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i * arbitrary[0] <= money; i++) {
            dp[0][i * arbitrary[0]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= money; j++) {
                /*
                i号货币不使用，看0~i-1号货币，凑出j元的方法数
                i号货币使用1枚，看0~i-1号货币，凑出j-i号货币面值，的方法数
                i号货币使用2枚，看0~i-1号货币，凑出j-i号货币面值，的方法数
                ......
                结果相加
                 */
                dp[i][j] = dp[i - 1][j];
                if (j - arbitrary[i] >= 0) dp[i][j] += dp[i][j - arbitrary[i]];
            }
        }
        return dp;
    }

}
