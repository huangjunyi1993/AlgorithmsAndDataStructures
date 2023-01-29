package _04LeetCode精选TOP面试题.数据结构设计;

/**
 * 设计一个井字棋游戏
 * 有一个方法 int move(int row, int col, int player)
 * 表示玩家player要在row行col列下一个棋子
 * 返回下完该步棋后哪个玩家赢
 * 返回0 没有赢家
 * 返回1 玩家1赢
 * 返回2 玩家2赢
 * Created by huangjunyi on 2022/11/12.
 */
public class _141设计井字棋 {
    class TicTacToe {

        int[][] rows; // 记录两个玩家在每一个行上下了几步棋
        int[][] cols; // 记录两个玩家在每一个列上下了几步棋
        int[] leftUp; // 记录两个玩家在正对角线上下了几步棋
        int[] rightUp; // 记录两个玩家在反对角线上下了几步棋
        boolean[][] matrix; // 棋盘
        int n; // 赢时的连子数，棋盘n*n


        /**
         * @param n n * n 的棋盘
         */
        public TicTacToe(int n) {
            n = n;
            rows = new int[n][3];
            cols = new int[n][3];
            leftUp = new int[3];
            rightUp = new int[3];
            matrix = new boolean[n][n];
        }

        /**
         * player玩家在row行col列下一步器
         * @param row 行
         * @param col 列
         * @param player 玩家
         * @return 谁赢 0-没人赢 player-player玩家赢
         */
        public int move(int row, int col, int player) {
            if (matrix[row][col]) return 0;
            matrix[row][col] = true;
            // row行上player下棋数++
            rows[row][player]++;
            // col列上player下棋数++
            cols[col][player]++;
            // 正对角线
            if (row == col) leftUp[player]++;
            // 反对角线
            if ((row + col) == n - 1) rightUp[player]++;
            if (rows[row][player] == n || cols[col][player] == n || leftUp[player] == n || rightUp[player] == n) return player;
            return 0;
        }

    }
}
