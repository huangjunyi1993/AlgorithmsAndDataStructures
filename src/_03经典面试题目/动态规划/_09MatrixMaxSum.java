package _03经典面试题目.动态规划;

/**
 * 给定一个整型矩阵，返回子矩阵的最大累计和。
 * Created by huangjunyi on 2022/9/25.
 */
public class _09MatrixMaxSum {

    public static int getMatrixMaxSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int max = Integer.MIN_VALUE;
        /*
        假如数组有3行
        枚举0~0 0~1 0~2 1~1 1~2 2~2 的最大累加和
        返回最大的

        例如求0~1行的最大累加和
        先把0列上的两个数压缩成一个数（相加），然后填到一个一位数组
        后面的列依次这样处理
        最后转化为一个一位数组的最大累加和问题
         */
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = new int[matrix[0].length];
            for (int j = i; j < matrix.length; j++) {
                int cur = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    arr[k] += matrix[j][k];
                    cur += arr[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }

}
