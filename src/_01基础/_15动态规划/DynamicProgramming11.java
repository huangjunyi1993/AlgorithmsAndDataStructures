package _01基础._15动态规划;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后达到右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * Created by huangjunyi on 2022/11/27.
 */
public class DynamicProgramming11 {

    /**
     * 动态规划
     * @param matrix
     * @return
     */
    public static int getMinPathSum01(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int N = matrix.length;
        int M = matrix[0].length;
        // dp[i][j] 走左上角出发，走到i行j列的格子，最小距离累加和是多少
        int[][] dp = new int[N][M];
        // 初始化第0行
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        // dp[i][j] 从左边走过来(dp[i][j-1])，从上边走过来(dp[i-1][j])，两个方向，选最路径和最短
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + matrix[i][j];
            }
        }
        return dp[N - 1][M - 1];
    }

    /**
     * 动态规划，空间压缩
     * @param matrix
     * @return
     */
    public static int getMinPathSum02(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int N = matrix.length;
        int M = matrix[0].length;
        // 因为dp[i][j]只依赖左边和上边一个格子，所以不需要二维dp表，一位表滚动更新即可
        int[] dp = new int[M];
        // 初始化第0行
        dp[0] = matrix[0][0];
        for (int i = 1; i < M; i++) {
            dp[i] = dp[i - 1] + matrix[0][i];
        }
        /*
        假设滚动更新到i行，现在要填dp[j]，此时还没更新dp[j]，那么：
        dp[j-1] 对应 dp[i][j-1]
        dp[j] 对应 dp[i-1][j]

        dp[j] = Math.min(dp[j-1], dp[j]) + matrix[i][j];
         */
        for (int i = 1; i < N; i++) {
            dp[0] = dp[0] + matrix[i][0];
            for (int j = 1; j < M; j++) {
                dp[j] = Math.min(dp[j-1], dp[j]) + matrix[i][j];
            }
        }
        return dp[M - 1];
    }

}
