package _07LeetCode其他题;

import java.util.TreeSet;

/**
 * https://leetcode.cn/problems/max-sum-of-rectangle-no-larger-than-k/
 * Created by huangjunyi on 2023/1/21.
 */
public class _363矩形区域不超过K的最大数值和 {
    class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
            // 1、如果行比列多，先进性行列转换
            if (matrix.length > matrix[0].length) matrix = rotate(matrix);
            int row = matrix.length;
            int col = matrix[0].length;
            // 2、前缀和有序表，用于快速求子数组累加和
            TreeSet<Integer> preSumSet = new TreeSet<>();
            int res = Integer.MIN_VALUE;
            /*
            3、
            0~0,0~1,0~2
            1~1,1~2,1~3
            求矩阵累加和最接近k的答案，进行PK

            通过数组压缩，转成一维数组求子数组累加和问题

            通过有序表加速查询子数组累加
             */
            for (int startRow = 0; startRow < row; startRow++) {
                int[] colSum = new int[col];
                for (int endRow = startRow; endRow < row; endRow++) {
                    int rowSum = 0;
                    preSumSet.add(rowSum);
                    for (int colIndex = 0; colIndex < col; colIndex++) {
                        colSum[colIndex] += matrix[endRow][colIndex];
                        rowSum += colSum[colIndex];
                        Integer ceiling = preSumSet.ceiling(rowSum - k);
                        if (ceiling != null) {
                            res = Math.max(res, rowSum - ceiling);
                        }
                        preSumSet.add(rowSum);
                    }
                    preSumSet.clear();
                }
            }
            return res;
        }

        private int[][] rotate(int[][] matrix) {
            int[][] copy = new int[matrix[0].length][matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    copy[j][i] = matrix[i][j];
                }
            }
            return copy;
        }
    }
}
