package _02进阶._26矩阵处理技巧;

/**
 * 用Zigzag扫描打印矩阵matrix
 * Created by huangjunyi on 2022/9/11.
 */
public class Matrix01 {

    public static void printMatrix(int[][] matrix) {
        /*
            a ➡        b⬇
           c -----------
           ⬇|           |
            |           |
            |           |
             -----------
            d ➡
         */
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int endRow = matrix.length - 1;
        int endColumn = matrix[0].length - 1;
        boolean fromUp = false;
        while (b <= endRow) {
            printMatrix(matrix, a, b, c, d, fromUp);
            b = a == endColumn ? b + 1 : b;
            a = a == endColumn ? a : a + 1;
            d = c == endRow ? c + 1 : c;
            c = c == endRow ? c : c + 1;
        }
    }

    private static void printMatrix(int[][] matrix, int a, int b, int c, int d, boolean fromUp) {
        /*
                     (a, b)
                    /
                   /
                  /
                 /
                /
               /
              /
             (d, c)
         */
        if (fromUp) {
            //从右上往左下打印
            while (b < matrix.length && a >= 0) System.out.println(matrix[b++][a--]);
        } else {
            //从左下往右上打印
            while (a < matrix[0].length && c >= 0) System.out.println(matrix[c--][d++]);
        }
    }

}
