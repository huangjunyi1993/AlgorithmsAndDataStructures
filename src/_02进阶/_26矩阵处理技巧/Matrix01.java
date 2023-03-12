package _02进阶._26矩阵处理技巧;

/**
 * 用Zigzag扫描打印矩阵matrix
 * Created by huangjunyi on 2022/9/11.
 */
public class Matrix01 {

    public static void printMatrix(int[][] matrix) {
        /*
        A点(a,b)
        B点(c,d)
        A点每次往右走一步，到最右侧了，则每次往下走一步
        B点每次往下走一步，到最下方了，则每次往右走一步
        那么每次AB两点间，都能全都一条斜线
        用一个boolean变量标识本次应该从上午往下打印还是从下往上打印

            a ➡        b⬇
           c -----------
           ⬇|           |
            |           |
            |           |
             -----------
            d ➡
         */
        // A点坐标
        int a = 0;
        int b = 0;
        // B点坐标
        int c = 0;
        int d = 0;
        int endRow = matrix.length - 1;
        int endColumn = matrix[0].length - 1;
        boolean fromUp = false;
        while (b <= endRow) {
            // 打印斜线
            printMatrix(matrix, a, b, c, d, fromUp);
            // A点每次往左走一步，到最右侧了，则每次往下走一步
            b = a == endColumn ? b + 1 : b;
            a = a == endColumn ? a : a + 1;
            // B点每次往下走一步，到最下方了，则每次往右走一步
            d = c == endRow ? d + 1 : d;
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
            while (d < matrix[0].length && c >= 0) System.out.println(matrix[c--][d++]);
        }
    }

}
