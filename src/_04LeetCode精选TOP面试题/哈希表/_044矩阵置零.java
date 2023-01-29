package _04LeetCode精选TOP面试题.哈希表;

/**
 * https://leetcode.cn/problems/set-matrix-zeroes/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _044矩阵置零 {
    public void setZeroes(int[][] matrix) {
        boolean rowZero = false; // 第0行是否变0
        boolean colZero = false; // 第0列是否变0
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colZero = true;
                break;
            }
        }
        // 把第0行作为标记行,第0列作为标记列，记录该行，该列是否要变0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // 第i行要变0
                    matrix[0][j] = 0; // 第j列要变0
                }
            }
        }
        // 遍历除标记行和标记列外的格子，根据标记行行或标记列看是否变0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 ||  matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 单独处理标记行
        if (rowZero) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        // 单独处理标记列
        if (colZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
