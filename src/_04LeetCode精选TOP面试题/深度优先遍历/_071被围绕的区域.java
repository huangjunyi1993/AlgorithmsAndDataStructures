package _04LeetCode精选TOP面试题.深度优先遍历;

/**
 * https://leetcode.cn/problems/surrounded-regions/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _071被围绕的区域 {
    public void solve(char[][] board) {
        boolean[] flag = new boolean[1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    flag[0] = true;
                    can(board, i, j, flag);
                    board[i][j] = flag[0] ? 'T' : 'F';
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == 'T' || c == 'F') {
                    board[i][j] = '.';
                    change(board, i, j, c);
                }
            }
        }
    }

    private void change(char[][] board, int i, int j, char c) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return;
        }
        if (board[i][j] == '.') {
            board[i][j] = c == 'T' ? 'X' : 'O';
            change(board, i - 1, j, c);
            change(board, i, j - 1, c);
            change(board, i + 1, j, c);
            change(board, i, j + 1, c);
        }
    }

    private void can(char[][] board, int i, int j, boolean[] flag) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            flag[0] = false;
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '.';
            can(board, i - 1, j, flag);
            can(board, i, j - 1, flag);
            can(board, i + 1, j, flag);
            can(board, i, j + 1, flag);
        }
    }
}
