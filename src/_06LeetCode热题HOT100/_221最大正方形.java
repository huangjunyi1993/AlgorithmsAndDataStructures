package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/maximal-square/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _221最大正方形 {
    class Solution {
        public int maximalSquare(char[][] matrix) {
            int N = matrix.length;
            int M = matrix[0].length;
            /*
            dp[i][j] 以i行j列格子为右下角的正方形的最大变长
            左边格子、左上方格子、上方格子中的边长的最小值加一
             */
            int[][] dp = new int[N][M];
            int max = 0;
            for (int i = 0; i < N; i++) {
                dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
                max = Math.max(max, dp[i][0]);
            }
            for (int i = 0; i < M; i++) {
                dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
                max = Math.max(max, dp[0][i]);
            }
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    if (matrix[i][j] == '1') {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j-1], dp[i][j - 1])) + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            return max * max;
        }
    }
}
