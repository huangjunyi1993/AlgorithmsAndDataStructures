package _03经典面试题目.动态规划;

/**
 * Created by huangjunyi on 2022/10/9.
 */
public class _36BurstBalloons {

    public static int maxCoins1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];
        /*
        copy一个长度加2的数组，左右两边补1，保证左右两边有不被打爆的气球
        任何求中间范围的最大值
         */
        int[] newArr = new int[arr.length + 2];
        newArr[0] = 1;
        newArr[newArr.length - 1] = 1;
        for (int i = 1; i <= newArr.length-2; i++) {
            newArr[i] = arr[i - 1];
        }
        return process1(arr, 1, newArr.length-2);
    }

    private static int process1(int[] arr, int L, int R) {
        if (L == R) return arr[L - 1] * arr[L] * arr[R + 1];
        /*
        枚举每一个位置最后被打爆
        然后递归
         */
        int max = Math.max(
                arr[L - 1] * arr[L] * arr[R + 1] + process1(arr, L + 1, R),
                arr[L - 1] * arr[R] * arr[R + 1] + process1(arr, L, R - 1)
        );
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max,
                    arr[L - 1] * arr[i] * arr[R + 1]
                            + process1(arr, L, i - 1)
                            + process1(arr, i + 1, R));
        }
        return max;
    }

    public static int maxCoins2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];
        int[] newArr = new int[arr.length + 2];
        newArr[0] = 1;
        newArr[newArr.length - 1] = 1;
        for (int i = 1; i <= newArr.length-2; i++) {
            newArr[i] = arr[i - 1];
        }
        int[][] dp = new int[newArr.length][newArr.length];
        return process2(arr, 1, newArr.length-2, dp);
    }

    /**
     * 改成记忆化搜索的版本
     * @param arr
     * @param L
     * @param R
     * @param dp
     * @return
     */
    private static int process2(int[] arr, int L, int R, int[][] dp) {
        if (dp[L][R] != 0) return dp[L][R];
        if (L == R) {
            dp[L][R] = arr[L - 1] * arr[L] * arr[R + 1];
            return arr[L - 1] * arr[L] * arr[R + 1];
        }
        int max = Math.max(
                arr[L - 1] * arr[L] * arr[R + 1] + process1(arr, L + 1, R),
                arr[L - 1] * arr[R] * arr[R + 1] + process1(arr, L, R - 1)
        );
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max,
                    arr[L - 1] * arr[i] * arr[R + 1]
                            + process1(arr, L, i - 1)
                            + process1(arr, i + 1, R));
        }
        dp[L][R] = max;
        return max;
    }

}

/***************************************
 *
 *
 * 左神代码 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 *
 *
 ***************************************/
class Code03_BurstBalloons {

    public static int maxCoins1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }
        return process(help, 1, N);
    }

    // 打爆arr[L..R]范围上的所有气球，返回最大的分数
    // 假设arr[L-1]和arr[R+1]一定没有被打爆
    public static int process(int[] arr, int L, int R) {
        if (L == R) {// 如果arr[L..R]范围上只有一个气球，直接打爆即可
            return arr[L - 1] * arr[L] * arr[R + 1];
        }
        // 最后打爆arr[L]的方案，和最后打爆arr[R]的方案，先比较一下
        int max = Math.max(
                arr[L - 1] * arr[L] * arr[R + 1] + process(arr, L + 1, R),
                arr[L - 1] * arr[R] * arr[R + 1] + process(arr, L, R - 1));
        // 尝试中间位置的气球最后被打爆的每一种方案
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max,
                    arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1)
                            + process(arr, i + 1, R));
        }
        return max;
    }

    public static int maxCoins2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }
        int[][] dp = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
            System.out.println(dp[i][i]);
        }
        for (int L = N; L >= 1; L--) {
            for (int R = L + 1; R <= N; R++) {
                // 求解dp[L][R]，表示help[L..R]上打爆所有气球的最大分数
                // 最后打爆help[L]的方案
                int finalL = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
                // 最后打爆help[R]的方案
                int finalR = help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1];
                // 最后打爆help[L]的方案，和最后打爆help[R]的方案，先比较一下
                dp[L][R] = Math.max(finalL, finalR);
                // 尝试中间位置的气球最后被打爆的每一种方案
                for (int i = L + 1; i < R; i++) {
                    dp[L][R] = Math.max(dp[L][R], help[L - 1] * help[i]
                            * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
                }
            }
        }
        return dp[1][N];
    }

    public static void main(String[] args) {
        int[] arr = { 4, 2, 3, 5, 1, 6 };
        System.out.println(maxCoins1(arr));
        System.out.println(maxCoins2(arr));
    }

}

