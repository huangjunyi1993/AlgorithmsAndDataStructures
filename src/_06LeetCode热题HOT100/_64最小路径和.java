package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/minimum-path-sum/description/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _64最小路径和 {
    class Solution {
        public int minPathSum(int[][] grid) {
            int N = grid.length;
            int M = grid[0].length;
            /*
            dp[i][j] 从左上角的位置走到i行j列的位置，最短的路径和
            从左边和上边的格子选一个最小的路径和，加上当前格子对应的值，就是当前格子的最短路径和
            因此当前行只依赖上边一行
            所以压缩为1维表
             */
            int[] dp = new int[M];
            dp[0] = grid[0][0];
            for (int i = 1; i < M; i++) {
                dp[i] = grid[0][i] + dp[i - 1];
            }
            for (int i = 1; i < N; i++) {
                dp[0] = dp[0] + grid[i][0];
                for (int j = 1; j < M; j++) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
            return dp[M - 1];
        }
    }
}
