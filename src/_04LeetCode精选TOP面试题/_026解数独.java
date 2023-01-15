package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/sudoku-solver/
 *
 * Created by huangjunyi on 2022/10/25.
 */
public class _026解数独 {
    public void solveSudoku(char[][] board) {
        /*
        预处理：
        初始化好三张判重表
         */
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] cel = new boolean[9][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    cel[(i / 3) * 3 + (j / 3)][num] = true;
                }
            }
        }
        // 深度优先遍历加剪枝
        process(board, 0, 0, row, col, cel);
    }

    private boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] cel) {
        if (i == 9) return true;
        // 计算下一个位置的行列号
        int nexti = j == 8 ? i + 1 : i;
        int nextj = j == 8 ? 0 : j + 1;
        if (board[i][j] != '.') {
            // 当前位置不是点，不用填，去下一个位置
            return process(board, nexti, nextj, row, col, cel);
        } else {
            // 是点，尝试
            int bid = (i / 3) * 3 + (j / 3); // 计算桶号
            for (int num = 1; num <= 9; num++) {
                if (row[i][num] || col[j][num] || cel[bid][num]) continue;
                row[i][num] = true;
                col[j][num] = true;
                cel[bid][num] = true;
                board[i][j] = (char) (num + '0');
                if (process(board, nexti, nextj, row, col, cel)) return true;
                // 恢复现场
                row[i][num] = false;
                col[j][num] = false;
                cel[bid][num] = false;
                board[i][j] = '.';
            }
        }
        return false;
    }
}
