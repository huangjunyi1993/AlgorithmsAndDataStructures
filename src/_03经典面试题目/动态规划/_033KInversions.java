package _03经典面试题目.动态规划;

/**
 * 给定一个整数N，代表你有1～N这些数字。在给定一个整数K。
 * 你可以随意排列这些数字，但是每一种排列都有若干个逆序对。
 * 返回有多少种排列，正好有K个逆序对
 *
 * 例子1:
 * Input: n = 3, k = 0
 * Output: 1
 * 解释：
 * 只有[1,2,3]这一个排列有0个逆序对。
 *
 * 例子2:
 * Input: n = 3, k = 1
 * Output: 2
 * 解释
 * [3,2,1]有(3,2)、(3,1)、(2,1)三个逆序对，所以不达标。
 * 达标的只有：
 * [1,3,2]只有(3,2)这一个逆序对，所以达标。
 * [2,1,3]只有(2,1)这一个逆序对，所以达标。
 *
 * Created by huangjunyi on 2022/10/8.
 */
public class _033KInversions {

    public static int inversionsNum1(int N, int K) {
        if (N < 1 || K < 0) return 0;
        int[][] dp = new int[N + 1][K + 1];
        /*
        N = 7 K = 3
        * * * * * * <= 7 插哪？
        * * * * * * 7 dp[6][3]
        * * * * * 7 * dp[6][2]，7 * 1个逆序对
        * * * * 7 * * dp[6][1]，7 * * 2个逆序对
        * * * 7 * * * dp[6][0]，7 * * * 3个逆序对
        所以：dp[7][3] = dp[6][3] + dp[6][2] + dp[6][1] + dp[6][0]
             dp[i][j] = dp[i - 1][j...0]

        N = 7 K = 7
        7 * * * * * * dp[6][1]，7 * * * * * * 6个逆序对，还需补一个
        所以：i <= j
        dp[7][7] = dp[6][7] + dp[6][6] + ... + dp[6][1]
        dp[i][j] = dp[i - 1][j...(j - i + 1)]

        所以：
        i > j  dp[i][j] = dp[i - 1][j...0]
        i <= j dp[i][j] = dp[i - 1][j...(j - i + 1)]
         */

        // dp[0][j]，1~0数字，j个逆序对，无效
        // dp[1][j]，1~1，j个逆序对，只有dp[1][0]为1
        dp[1][0] = 1;
        // dp[i][0]，0个逆序对，1
        for (int i = 2; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                for (int k = j; k >= Math.max(0, j - i + 1); k--) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }
        return dp[N][K];
    }

    public static int inversionsNum2(int N, int K) {
        if (N < 1 || K < 0) return 0;
        int[][] dp = new int[N + 1][K + 1];
        dp[1][0] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = 1;
        }
        /*
        N = 7 K = 3
        dp[7][3] 依赖 => dp[6][3] + dp[6][2] + dp[6][1] + dp[6][0]
        N = 7 K = 2
        dp[7][2] 依赖 => dp[6][2] + dp[6][1] + dp[6][0]
        dp[7][3] = dp[6][3] + dp[7][2]
        所以：i > j 时
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

        N = 7 K = 9
        dp[7][9] 依赖 => dp[6][9] + dp[6][8] + ... + dp[6][3]  dp[6][3] -> dp[i][j - 1 + 1]
        dp[7][8] 依赖 =>            dp[6][8] + ... + dp[6][3] + dp[6][2]  dp[6][2] -> dp[i - 1][j - 1]
        dp[7][9] = dp[6][9] + dp[7][8] - dp[6][2]
        所以：i <= j 时
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j-1]
         */
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - (i <= j ? dp[i - 1][j-1] : 0);
            }
        }
        return dp[N][K];
    }

}
