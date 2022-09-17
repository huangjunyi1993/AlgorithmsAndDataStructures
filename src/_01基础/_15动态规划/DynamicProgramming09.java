package _01基础._15动态规划;

/**
 * 马踏棋盘问题
 * 规定马从0,0位置出发，棋盘是10*9大小的棋盘
 * 给定三个整形变量x,y,k，表示马走k部到达x,y位置
 * 问有几种走法
 * Created by huangjunyi on 2022/9/4.
 */
public class DynamicProgramming09 {

    public static int processByRecursive(int x, int y, int k) {
        if (k <= 0) return x == 0 && y == 0 ? 1 : 0;
        if (x < 0 || x >= 10 || y < 0 || y >= 9) return 0;
        return processByRecursive(x + 2, y + 1, k - 1) +
                processByRecursive(x + 1, y + 2, k - 1) +
                processByRecursive(x - 1, y + 2, k - 1) +
                processByRecursive(x - 2, y + 1, k - 1) +
                processByRecursive(x - 2, y - 1, k - 1) +
                processByRecursive(x - 1, y - 2, k - 1) +
                processByRecursive(x + 1, y - 2, k - 1) +
                processByRecursive(x + 2, y - 1, k - 1);
    }

    public static int processByDp(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[0][0][0] = 1;
        for (int level = 1; level <= k; level++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][level] = getFromDp(dp, i + 2, j + 1, level - 1) +
                            getFromDp(dp, i + 1, j + 2, level - 1) +
                            getFromDp(dp, i - 1, j + 2, level - 1) +
                            getFromDp(dp, i - 2, j + 1, level - 1) +
                            getFromDp(dp, i - 2, j - 1, level - 1) +
                            getFromDp(dp, i - 1, j - 2, level - 1) +
                            getFromDp(dp, i + 1, j - 2, level - 1) +
                            getFromDp(dp, i + 2, j - 1, level - 1);
                }
            }
        }
        return dp[x][y][k];
    }

    public static int getFromDp(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x >= 10 || y < 0 || y >= 9) return 0;
        return dp[x][y][k];
    }

    public static void main(String[] args) {
        System.out.println(processByRecursive(6, 8, 10));
        System.out.println(processByDp(6, 8, 10));
    }

}
