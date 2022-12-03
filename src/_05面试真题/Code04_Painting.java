package _05面试真题;

import java.util.ArrayList;
import java.util.List;

/**
 * N * M的棋盘
 * 所有格子必须染色
 * 每种颜色的格子数必须相同的
 * 相邻格子染的颜色必须不同
 * 返回至少多少种颜色可以完成任务
 *
 * 通过打表法分析可知，是N*M最小的质数因子，原因不明，也不重要
 */
public class Code04_Painting {
    public static int minColors(int N, int M) {
        // 从2中颜色，试到n*m种颜色
        for (int i = 2; i <= N * M; i++) {
            int[][] matrix = new int[N][M];
            if (N * M % i == 0 && can(matrix, N, M, i)) return i;
        }
        return N * M;
    }

    private static boolean can(int[][] matrix, int n, int m, int colorType) {
        int everyCount = (n * m) / colorType;
        // 每种颜色剩余多少指标，颜色从1开始算起，所以先add一个0
        List<Integer> rest = new ArrayList<>();
        rest.add(0);
        for (int i = 0; i < colorType; i++) {
            rest.add(everyCount);
        }
        return process(matrix, n, m, colorType, 0, 0, rest);
    }

    private static boolean process(int[][] matrix, int n, int m, int colorType, int row, int col, List<Integer> rest) {
        // base case
        if (row == n) return true;
        // 换行
        if (col == m) return process(matrix, n, m, colorType, row + 1, col, rest);
        // 只管保证和左和上两个格子不同颜色就行
        int upColor = row == 0 ? 0 : matrix[row - 1][col];
        int leftColor = col == 0 ? 0 : matrix[row][col - 1];
        // 尝试每种颜色填到当前格子
        for (int color = 1; color <= colorType; color++) {
            if (color != upColor && color != leftColor && rest.get(color) > 0) {
                int count = rest.get(color);
                rest.set(color, count - 1);
                matrix[row][col] = color;
                if (process(matrix, n, m, colorType, row, col + 1, rest)) return true;
                matrix[row][col] = 0;
                rest.set(color, count);
            }
        }
        return false;
    }
}
