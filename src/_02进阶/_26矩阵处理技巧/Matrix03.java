package _02进阶._26矩阵处理技巧;

/**
 * 旋转矩阵
 * Created by huangjunyi on 2022/9/11.
 */
public class Matrix03 {

    public static void rotate(int[][] matrix) {
        /*
        矩阵分圈，一圈一圈旋转，圈与圈之间是互不影响的
        圈内分组，每组就是4个点，循环交换位置

            x ➡
           y
           ⬇
                (x=a, y=b)
                 -----------
                |           |
                |           |
                |           |
                 -----------
                        (x=c, y=d)
         */
        int a = 0;
        int b = 0;
        int c = matrix[0].length - 1;
        int d = matrix.length - 1;
        while (a < c) rotate(matrix, a++, b++, c--, d--);
    }

    private static void rotate(int[][] matrix, int a, int b, int c, int d) {
        for (int i = 0; i < c - a; i++) {
            int temp = matrix[b][a + i];
            matrix[b][a + i] = matrix[d + i][a];
            matrix[d + i][a] = matrix[d][c - i];
            matrix[d][c - i] = matrix[d - i][c];
            matrix[d - i][c] = temp;
        }
    }

}
