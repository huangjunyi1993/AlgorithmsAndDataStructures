package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/word-search/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _048单词搜索 {
    public boolean exist(char[][] board, String word) {
        char[] chs = word.toCharArray();
        /*
        1、深度优先遍历
        2、恢复现场
        3、控制不走回头路
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, chs, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean process(char[][] board, char[] word, int i, int j, int k) {
        if (k == word.length) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] != word[k]) return false;
        char temp = board[i][j];
        board[i][j] = 0;
        boolean res =
                process(board, word, i - 1, j, k + 1) ||
                process(board, word, i, j - 1, k + 1) ||
                process(board, word, i + 1, j, k + 1) ||
                process(board, word, i, j + 1, k + 1);
        board[i][j] = temp;
        return res;
    }
}
