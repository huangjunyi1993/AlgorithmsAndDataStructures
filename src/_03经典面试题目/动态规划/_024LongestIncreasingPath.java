package _03经典面试题目.动态规划;

/**
 * 给定一个二维数组matrix，可以从任何位置出发，每一步可以走向上、下、左、右，四个方向。返回最大递增链的长度。
 * 例子：
 * matrix =
 * 5 4 3
 * 3 1 2
 * 2 1 3
 * 从最中心的1出发，是可以走出1 2 3 4 5的链的，而且这是最长的递增链。所以返回长度5
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _024LongestIncreasingPath {

    public static int getMaxLenPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int N = matrix.length;
        int M = matrix[0].length;

        /*
        枚举每个起始点，尝试走
        记录走出的路径长度到dp

        记忆化搜索降低时间复杂度
        重复走过的直接从dp取
         */
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, process(matrix, i, j, dp, N, M));
            }
        }
        return max;
    }

    private static int process(int[][] matrix, int i, int j, int[][] dp, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M) return -1;
        if (dp[i][j] != 0) return dp[i][j];
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) up = process(matrix, i - 1, j, dp, N, M);
        if (i + 1 < N && matrix[i + 1][j] > matrix[i][j]) down = process(matrix, i + 1, j, dp, N, M);
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) left = process(matrix, i, j - 1, dp, N, M);
        if (j + 1 < M && matrix[i][j +1] > matrix[i][j]) right = process(matrix, i, j + 1, dp, N, M);

        dp[i][j] = 1 + Math.max(up, Math.max(down, Math.max(left, right)));
        return dp[i][j];
    }

}
