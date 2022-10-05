package _03经典面试题目.动态规划;

/**
 * 给定一个矩阵matrix，先从左上角开始，每一步只能往右或者往下走，走到右下角。
 * 然后从右下角出发，每一步只能往上或者往左走，再回到左上角。
 * 任何一个位置的数字，只能获得一遍。
 * 返回最大路径和
 *
 * Created by huangjunyi on 2022/10/5.
 */
public class _027BackAndForthMaximumPathSum {

    public static int maxPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        return process(matrix, 0, 0, 0, matrix.length, matrix[0].length);
    }

    /**
     * 把来回路径和转换为两个小人同时从左上角走到右下角的最大路径和，结果一样
     * @param matrix 矩阵
     * @param ar a小人行号
     * @param ac a小人列号
     * @param br b小人行号，b小人列号通过 ar + ac - br 获得
     * @param N 矩阵高度
     * @param M 矩阵长度
     * @return
     */
    private static int process(int[][] matrix, int ar, int ac, int br, int N, int M) {
        if (ar == N - 1 && ac == M - 1) return matrix[N - 1][M - 1];
        int bc = ar + ac - br;
        int res = Integer.MIN_VALUE;
        /*
        四种情况
        1、a往下走，b往右走
        2、a往下走，b往下走
        3、a往右走，b往右走
        4、a往右走，b往下走
        取最大值
         */
        if (ar + 1 < N && ac + 1 < M) Math.max(res, process(matrix, ar + 1, ac, br, N, M));
        if (ar + 1 < N && br + 1 < N) Math.max(res, process(matrix, ar + 1, ac, br + 1, N, M));
        if (ac + 1 < M && bc + 1 < M) Math.max(res, process(matrix, ar, ac + 1, br, N, M));
        if (ac + 1 < M && br + 1 < N) Math.max(res, process(matrix, ar, ac + 1, br + 1, N, M));
        /*
        如果a和b同一个格子，则只取一份值
        不同格子，则两人站的格子的值，相加，加上上面递归获得的值

        因为a和b同时从左上角出发，且只能往右或往下走
        所以判断是否同一个格子
        只需判断行号是否相等
        两人行号相等，列号比人也相当，因为走不出行号相等列号不等的情况
         */
        if (ar == br) res += matrix[ar][ac];
        else res += matrix[ar][ac] + matrix[br][bc];
        return res;
    }

}
