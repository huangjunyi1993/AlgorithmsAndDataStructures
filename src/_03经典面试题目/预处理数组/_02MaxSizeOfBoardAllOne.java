package _03经典面试题目.预处理数组;

/**
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 * Created by huangjunyi on 2022/9/17.
 */
public class _02MaxSizeOfBoardAllOne {

    public static int getMaxSize(int[][] matrix) {
        int[][] right = new int[matrix.length][matrix[0].length];
        int[][] down = new int[matrix.length][matrix[0].length];
        //生成辅助数组
        fillHelpMatrix(matrix, right, down);
        //遍历数组，边长从大到小，根据辅助数组检查是否有满足条件的边框
        for (int size = Math.min(matrix.length, matrix[0].length); size > 0; size--) {
            if (hasSizeBoard(size, down, right)) return size;
        }
        return 0;
    }

    /**
     * 检查是否有合格的指定边长的边框
     * 遍历数组：
     * 看每一个点上是否往右有大于等于size个1 right[i][j] >= size
     * 看每一个点上是否往下有大于等于size个1 down[i][j] >= size
     * 看该点往右推size位，往下是否有大于等于size个1 down[i][j + size - 1] >= size
     * 看该点往下推size位，往右是否有大于等于size个1 right[i + size - 1][j] >= size
     * @param size
     * @param down
     * @param right
     * @return
     */
    private static boolean hasSizeBoard(int size, int[][] down, int[][] right) {
        for (int i = 0; i < right.length - size; i++) {
            for (int j = 0; i < right[0].length - size; i++) {
                if (right[i][j] >= size
                        && down[i][j] >= size
                        && down[i][j + size - 1] >= size
                        && right[i + size - 1][j] >= size) return true;
            }
        }
        return false;
    }

    /**
     * 填充辅助数组
     * right数组从有往左统计连续1的个数
     * down数组从下往上统计连续1的个数
     * @param matrix
     * @param right
     * @param down
     */
    private static void fillHelpMatrix(int[][] matrix, int[][] right, int[][] down) {
        int r = matrix.length;
        int c = matrix[0].length;
        if (matrix[r-1][c-1] == 1) {
            right[r-1][c-1] = 1;
            down[r-1][c-1] = 1;
        }
        for (int i = r - 2; i >= 0; i--) {
            if (matrix[i][c-1] == 1) {
                right[i][c-1] = 1;
                down[i][c-1] = down[i+1][c-1] + 1;
            }
        }
        for (int i = c - 2; c >= 0; c--) {
            if (matrix[r-1][i] == 1) {
                right[r-1][i] = right[r-1][i+1] + 1;
                down[r-1][i] = 1;
            }
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    right[i][j] = right[i][j+1] + 1;
                    down[i][j] = down[i+1][j] + 1;
                }
            }
        }
    }

}
