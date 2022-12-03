package _01基础._15动态规划;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2，
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)。
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种？
 * 给定四个参数 N、M、K、P，返回方法数。
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming01 {

    /**
     * 暴力递归求解
     * @param n 总位置数
     * @param m 初始化位置
     * @param k 可走步数
     * @param p 目标位置
     * @return
     */
    public static int method01(int n, int m, int k, int p) {
        // 不合法参数判断
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) return 0;
        return processo1(n, m, k, p);
    }

    /**
     * 暴力递归求解
     * @param n 总位置数
     * @param curr 当前位置
     * @param remain 剩余可走步数
     * @param p 目标位置
     * @return
     */
    private static int processo1(int n, int curr, int remain, int p) {
        // base case：还剩0步要走，如果当前位置curr在目标位置p，则发现一种方法
        if (remain == 0) return curr == p ? 1 : 0;
        // 1位置，只能往右走
        if (curr == 1) return processo1(n, 2, remain - 1, p);
        // n位置，只能往左走
        if (curr == n) return processo1(n, n - 1, remain - 1, p);
        // 两个递归 往左走，往右走 返回的方法数累加
        return processo1(n, n - 1, remain - 1, p) + processo1(n, n + 1, remain - 1, p);
    }

    /**
     * 粗糙的动态规划，记忆化搜索
     * @param n 总位置数
     * @param m 初始化位置
     * @param k 可走步数
     * @param p 目标位置
     * @return
     */
    public static int method02(int n, int m, int k, int p) {
        // 不合法参数判断
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) return 0;
        // 初始化缓存表
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; k++) {
                dp[i][j] = -1;
            }
        }
        return processo2(n, m, k, p, dp);
    }

    /**
     * 粗糙的动态规划，记忆化搜索
     * @param n 总位置数
     * @param curr 当前位置
     * @param remain 剩余可走步数
     * @param p 目标位置
     * @param dp 记忆表
     * @return
     */
    private static int processo2(int n, int curr, int remain, int p, int[][] dp) {
        // 如果缓存中有值，直接拿值，返回
        if (dp[curr][remain] != -1) return dp[curr][remain];
        if (remain == 0) {
            dp[curr][remain] = curr == p ? 1 : 0;
            return dp[curr][remain];
        }
        if (curr == 1) {
            // 返回得到的值前，先存缓存表
            dp[curr][remain] = processo2(n, 2, remain - 1, p, dp);
            return dp[curr][remain];
        }
        if (curr == n) {
            // 返回得到的值前，先存缓存表
            dp[curr][remain] = processo2(n, n - 1, remain - 1, p, dp);
            return dp[curr][remain];
        }
        // 返回得到的值前，先存缓存表
        dp[curr][remain] = processo2(n, curr - 1, remain - 1, p, dp) + processo2(n, curr + 1, remain - 1, p, dp);
        return dp[curr][remain];
    }

    /**
     * 动态规划求解
     * @param n 总位置数
     * @param m 初始化位置
     * @param k 可走步数
     * @param p 目标位置
     * @return
     */
    public static int method03(int n, int m, int k, int p) {
        // 不合法参数判断
        if (n < 2 || k < 1 || m < 1 || m > n || p < 1 || p > n) return 0;
        /*
          dp表
          dp[i][j]
          表示当前在i位置
          表示还剩j步要走
         */
        int[][] dp = new int[n + 1][k + 1];
        /*for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= k; k++) {
            dp[0][j] = 0;
        }*/
        // 根据暴力递归的base case，初始化
        dp[p][0] = 1;
        /*
        根据暴力递归的依赖关系，填表
        dp[1][j]依赖dp[2][j-1]
        dp[n][j]依赖dp[n-1][j-1]
        dp[i][j]依赖dp[i-1][j-1]和dp[i+1][j-1]
         */
        for (int remain = 1; remain <= k; remain++) {
            for (int curr = 1; curr <= n; curr++) {
                if (curr == 1) {
                    dp[curr][remain] = dp[2][remain - 1];
                } else if (curr == n) {
                    dp[curr][remain] = dp[n - 1][remain - 1];
                } else {
                    dp[curr][remain] = dp[curr + 1][remain - 1] + dp[curr - 1][remain - 1];
                }
            }
        }
        return dp[m][k];
    }

}
