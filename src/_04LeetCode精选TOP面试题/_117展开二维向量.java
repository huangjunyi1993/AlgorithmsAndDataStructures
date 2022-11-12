package _04LeetCode精选TOP面试题;

/**
 * 设计一个迭代器，用于迭代二维数组
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _117展开二维向量 {
    public class Vector2D {
        int[][] matrix;
        int row;
        int col;
        boolean isUsed;
        public Vector2D(int[][] v) {
            matrix = v;
            row = 0;
            col = -1;
            isUsed = true;
        }

        public int next() {
            if (isUsed) {
                hasNext();
            }
            int val = matrix[row][col];
            isUsed = true;
            return val;
        }

        public boolean hasNext() {
            if (row == matrix.length) return false;
            if (!isUsed) return true;
            if (col < matrix[row].length - 1) {
                col++;
                isUsed = false;
                return true;
            }
            do {
                row++;
                col = 0;
            } while (row < matrix.length && matrix[row].length == 0);
            if (row != matrix.length) {
                isUsed = false;
                return true;
            } else {
                return false;
            }
        }
    }
}
