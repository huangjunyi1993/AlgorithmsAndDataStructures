package _02进阶._26矩阵处理技巧;

import sun.security.util.Length;

/**
 * 转圈打印矩阵
 * Created by huangjunyi on 2022/9/11.
 */
public class Matrix02 {

    public static void printMatrix(int[][] matrix) {
        /*
            x ➡
           y
           ⬇
                (a, b)
                 -----------
                |           |
                |           |
                |           |
                 -----------
                        (c, d)
         */
        // 左上角点坐标
        int a = 0;
        int b = 0;
        // 右下角的左边
        int c = matrix[0].length - 1;
        int d = matrix.length - 1;
        // 分圈，从往往里，一圈一圈的打印
        while (a <= c && b <= d) {
            printMatrix(matrix, a++, b++, c--, d--);
        }
    }

    private static void printMatrix(int[][] matrix, int a, int b, int c, int d) {
        if (a == c) {
            while (b <= d) System.out.println(matrix[b++][a]);
            return;
        }
        if (b == d) {
            while (a <= c) System.out.println(matrix[b][a++]);
            return;
        }
        int curR = b;
        int curC = a;
        while (curC < c) System.out.println(matrix[curR][curC++]);
        while (curR < d) System.out.println(matrix[curR++][curC]);
        while (curC > a) System.out.println(matrix[curR][curC--]);
        while (curR > b) System.out.println(matrix[curR--][curC]);
    }

}
