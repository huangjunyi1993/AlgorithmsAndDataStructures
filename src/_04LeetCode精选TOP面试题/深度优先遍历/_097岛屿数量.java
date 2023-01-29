package _04LeetCode精选TOP面试题.深度优先遍历;

/**
 * https://leetcode.cn/problems/number-of-islands/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _097岛屿数量 {
    class Solution {
        public int numIslands(char[][] grid) {
            int N = grid.length;
            int M = grid[0].length;
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == '1') {
                        // 把整座岛感染掉，然后记一个岛屿数
                        infect(grid, i, j, N, M);
                        count++;
                    }
                }
            }
            return count;
        }

        private void infect(char[][] grid, int i, int j, int N, int M) {
            if (i < 0 || i >= N || j < 0 || j >= M) return;
            if (grid[i][j] == '1') {
                grid[i][j] = '2';
            } else {
                return;
            }
            infect(grid, i - 1, j, N, M);
            infect(grid, i + 1, j, N, M);
            infect(grid, i, j + 1, N, M);
            infect(grid, i, j - 1, N, M);
        }
    }
}
