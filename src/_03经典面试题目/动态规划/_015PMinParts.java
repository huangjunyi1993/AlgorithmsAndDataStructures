package _03经典面试题目.动态规划;

/**
 * 给定一个字符串，对其进行切割，使其每个部分都是一个回文串，返回能切割出的最少的回文串部分数
 * Created by huangjunyi on 2022/10/2.
 */
public class _015PMinParts {

    public static int minParts(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chars = str.toCharArray();
        int N = chars.length;

        /*
        构造回文表
        isP[i][j]表示字符串中从下标i到j的部分，是否是回文
        利用范围的尝试模型

        对角线先填true，表示每个字符都是一个回文

        每个格子依赖左下方的值
        所以把第二条对接线也单独填好，因为第二条对接线的做下方超出了第一条对角线，范围的尝试模型下半部分是不用填的
         */
        boolean[][] isP = new boolean[N][N];
        for (int i = 0; i < N; i++) isP[i][i] = true;
        for (int i = 0; i < N - 1; i++) isP[i][i + 1] = chars[i] == chars[i + 1];
        for (int row = N - 3; row >= 0; row--) {
            for (int col = row + 2; col < N; col++) {
                isP[row][col] = chars[row] == chars[col] && isP[row + 1][col - 1];
            }
        }

        /*
        start位从右往左
        dp[start]表示从字符串从下标start开始后面的部分，最少的回文部分
        假如从start~end是回文，并且 1 + dp[end + 1] 更小，则更新dp[start]
         */
        int[] dp = new int[N + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[N] = 0;
        for (int start = N - 1; start >= 0; start--) {
            for (int end = start; end < N; end++) {
                if (isP[start][end]) {
                    dp[start] = Math.min(dp[start], 1 + dp[end + 1]);
                }
            }
        }
        return dp[0];
    }

}
