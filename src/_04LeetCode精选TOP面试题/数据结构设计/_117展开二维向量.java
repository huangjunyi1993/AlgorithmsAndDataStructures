package _04LeetCode精选TOP面试题.数据结构设计;

/**
 * 设计一个迭代器，用于迭代二维数组
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _117展开二维向量 {
    public class Vector2D {
        int[][] matrix; // 二维数组
        int row; // 当前迭代到的行
        int col; // 当前迭代到的列
        boolean isUsed; // 当前指着的数，使用过没有？
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
            if (row == matrix.length) return false; // 终止行，没了
            if (!isUsed) return true; // 当前数还没使用，true
            if (col < matrix[row].length - 1) {
                // 当前行每迭代尽
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
