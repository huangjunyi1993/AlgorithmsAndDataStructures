package _03经典面试题目.预处理数组;

import java.util.TreeSet;

/**
 * 给定一个二维数组matrix，再给定一个k值,返回累加和小于等于k，但是离k最近的子矩阵累加和
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _07MaxSubMatrixSumLessOrEqualsK {

    public static int getMaxSubMatrixSumLessOrEqualsK(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int res = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            /*
            压缩数组技巧
            把矩阵问题转化为一维数组问题
             */
            int[] arr = new int[M];
            for (int j = i; j < N; j++) {
                set.clear();
                set.add(0);
                int sum = 0;
                for (int x = 0; x < M; x++) {
                    arr[x] += matrix[j][x];
                    sum += arr[x];
                    Integer ceiling = set.ceiling(sum - k);
                    if (ceiling != null) res = Math.max(res, sum - ceiling);
                    set.add(sum);
                }
            }
        }
        return res;
    }

}
