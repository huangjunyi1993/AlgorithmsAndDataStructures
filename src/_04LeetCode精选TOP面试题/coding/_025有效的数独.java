package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/valid-sudoku/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/25.
 */
public class _025有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        /*
        那三个数组记录
        row[i][j]表示第i行是否出现过j这个数
        col[i][j]表示第i列是否出现过j这个数
        cel[i][j]表示第i格是否出现过j这个数
         */
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] cel = new boolean[9][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (row[i][num]) return false;
                    if (col[j][num]) return false;
                    if (cel[(i / 3) * 3 + (j / 3)][num]) return false;
                    row[i][num] = true;
                    col[j][num] = true;
                    cel[(i / 3) * 3 + (j / 3)][num] = true;
                }
            }
        }
        return true;
    }
}
