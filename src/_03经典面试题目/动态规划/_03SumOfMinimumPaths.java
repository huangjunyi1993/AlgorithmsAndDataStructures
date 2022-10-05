package _03经典面试题目.动态规划;

/**
 * 给你一个二维数组matrix，其中每个数都是正数，要求从左上角走到右下角。
 * 每一步只能向右或者向下，沿途经过的数字要累加起来。
 * 最后请返回最小的路径和。
 *
 * 使用空间压缩技巧
 * Created by huangjunyi on 2022/9/24.
 */
public class _03SumOfMinimumPaths {

    public static int getMinSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] dp = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            dp[i] = (i == 0 ? matrix[0][0] : (dp[i - 1] + matrix[0][i]));
        }
        /*
        动态规划的空间压缩技巧
        原本的dp表应该是一个二维表
        但是因为二维表中的每个格子都只依赖左边和上边的格子
        因此可以用一个一位数组代替二维表
         */
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) dp[j] = dp[j] + matrix[i][j];
                else dp[j] = Math.min(dp[j - 1], dp[j]) + matrix[i][j];
            }
        }
        return dp[matrix[0].length - 1];
    }

}
