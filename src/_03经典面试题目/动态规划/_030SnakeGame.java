package _03经典面试题目.动态规划;

/**
 * 给定一个二维数组matrix，每个单元都是一个整数，有正有负。
 * 最开始的时候小Q操纵 一条长度为0的蛇蛇从矩阵最左侧任选一个单元格进入地图，
 * 蛇每次只能够到达当前位 置的右上相邻，右侧相邻和右下相邻的单元格。
 * 蛇蛇到达一个单元格后，自身的长度会 瞬间加上该单元格的数值，任何情况下长度为负则游戏结束。
 * 小Q是个天才，他拥有一 个超能力，可以在游戏开始的时候把地图中的某一个节点的值变为其相反数(注:最多 只能改变一个节点)。
 * 问在小Q游戏过程中，他的蛇蛇最长长度可以到多少?
 *
 * 比如:
 * 1 -4 10
 * 3 -2 -1
 * 2 -1 0
 * 0 5 -2
 * 最优路径为从最左侧的3开始，3 -> -4(利用能力变成4) -> 10。所以返回17。
 *
 * Created by huangjunyi on 2022/10/8.
 */
public class _030SnakeGame {

    public static int getMaxLen(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int res = Integer.MIN_VALUE;
        /*
        遍历每一个格子，代表走到当前格子时，蛇的最长路径
         */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] ans = process(matrix, i, j);
                res = Math.max(res, Math.max(ans[0], ans[1]));
            }
        }
        return res;
    }

    private static int[] process(int[][] matrix, int i, int j) {

        /*
        递归，返回结果为[不使用能力当当前格子最大长度，使用能力到当前格子最大的长度]
        从当前格子，往左，左上，左下，递归
        更新不使用能力、使用能力两个值为3个递归中获得的最大值
        然后如果从递归中获取的不使用能力的值为正数，代表之前的路可以不使用能力走到当格子
        那么当前递归不使用能力的值就是当前格子的值加上递归获取的最大不使用能力的长度
        当前格子使用能力的长度，要取之前使用能力和现在使用能力的最大值
         */

        // base case，在最开始的位置，不使用能力，就是格子的值，使用能力，就是格子的相反值
        if (j == 0) {
            return new int[] {matrix[i][j], -matrix[i][j]};
        }
        // 递归左方向的路
        int[] preAns = process(matrix, i, j - 1);
        int preUnuse = preAns[0];
        int preUse = preAns[1];
        // 递归左上方的路
        if (i - 1 >= 0) {
            preAns = process(matrix, i - 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }
        // 递归左下方的路
        if (i + 1 < matrix.length) {
            preAns = process(matrix, i + 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }

        int no = -1;
        int yes = -1;
        // 如果之前的路不需要使用能力也能走到，则先更新当前格子不使用能力和使用能力的值
        if (preUnuse >= 0) {
            no = matrix[i][j] + preUnuse;
            yes = -matrix[i][j] + preUnuse;
        }
        // 当前格子使用能力的长度，要取之前使用能力和现在使用能力的最大值
        if (preUse >= 0) {
            yes = Math.max(yes, matrix[i][j] + preUse);
        }
        return new int[] {no, yes};
    }

}
