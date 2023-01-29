package _04LeetCode精选TOP面试题.动态规划;

/**
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/11.
 */
public class _135矩阵中的最长递增路径 {
    class Solution {
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
            int N = matrix.length;
            int M = matrix[0].length;
            // dp[i][j] 从i行j列出发，能走出的最长路径
            int[][] dp = new int[N][M];
            int maxLen = 0;
            /*
            每个位置都试一遍
            递归
            记忆化搜索
             */
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    maxLen = Math.max(maxLen, process(matrix, i, j, dp, N, M));
                }
            }
            return maxLen;
        }

        private int process(int[][] matrix, int i, int j, int[][] dp, int N, int M) {
            if (dp[i][j] != 0) return dp[i][j];
            int maxLen = 0;
            if (i > 0 && matrix[i - 1][j] > matrix[i][j]) maxLen = Math.max(maxLen, process(matrix, i - 1, j, dp, N, M));
            if (i < N - 1 && matrix[i + 1][j] > matrix[i][j]) maxLen = Math.max(maxLen, process(matrix, i + 1, j, dp, N, M));
            if (j > 0 && matrix[i][j - 1] > matrix[i][j]) maxLen = Math.max(maxLen, process(matrix, i, j - 1, dp, N, M));
            if (j < M - 1 && matrix[i][j + 1] > matrix[i][j]) maxLen = Math.max(maxLen, process(matrix, i, j + 1, dp, N, M));
            dp[i][j] = maxLen + 1;
            return maxLen + 1;
        }
    }
}
