package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/game-of-life/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _126生命游戏 {
    class Solution {
        public void gameOfLife(int[][] board) {
            int N = board.length;
            int M = board[0].length;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int neighbors = neighbors(board, i, j);
                    // 3个邻居，或者当前位置是活的，并且有两个邻居，设置当前位置下一轮是活的
                    if (neighbors == 3 || board[i][j] == 1 && neighbors == 2) {
                        set(board, i, j);
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    get(board, i, j);
                }
            }
        }

        private void get(int[][] board, int i, int j) {
            // 右移一位，使得该位置是下一轮的位置
            board[i][j] >>= 1;
        }

        private boolean ok(int[][] board, int i, int j) {
            // 该位置是否有效且为活
            return i >= 0 && i < board.length && j >= 0 && j < board[0].length && ((board[i][j] & 1) == 1);
        }

        private void set(int[][] board, int i, int j) {
            // 利用二进制第二个位置，记录下一轮是活的
            board[i][j] |= 2;
        }

        private int neighbors(int[][] board, int i, int j) {
            // 计算活邻居个数
            int count = 0;
            count += ok(board, i - 1, j - 1) ? 1 : 0;
            count += ok(board, i - 1, j) ? 1 : 0;
            count += ok(board, i - 1, j + 1) ? 1 : 0;
            count += ok(board, i, j - 1) ? 1 : 0;
            count += ok(board, i, j + 1) ? 1 : 0;
            count += ok(board, i + 1, j - 1) ? 1 : 0;
            count += ok(board, i + 1, j) ? 1 : 0;
            count += ok(board, i + 1, j + 1) ? 1 : 0;
            return count;
        }
    }
}
