package _03经典面试题目.动态规划;

/**
 * 给定一个二维数组map，含义是一张地图，例如如下，矩阵：
 * -2 　-3　 3
 * -5　-10　1
 * 0 　30　-5
 *
 * 游戏规则如下：
 *    骑士从左上角出发，每次只能向右或者向下走，最后到达右下角见到公主。
 * 地图中每个位置的只代表骑士要遭遇的事。如果是负数，表示此处有怪兽，要让骑士损失血量。如果是非负数，表示此处有血瓶，能让骑士回血。
 * 骑士从左上角到右下角的过程，走到任何一个位置，血量都不能少于１。
 * 为了保证骑士能顺利见到公主，初始血量至少是多少？根据map，返回初始血量。
 *
 * Created by huangjunyi on 2022/10/5.
 */
public class _026DungeonsAndDragons {

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0) return 0;
        int N = dungeon.length;
        int M = dungeon[0].length;
        /*
        dp表
        从右下往左上填
        dp[i][j]表示从i行j列的格子，走到右下角，最少需要多少点初始血量

        dp[i][j]依赖于dp[i - 1][j]和dp[i][j - 1]
        选取两个值中的最小值，代表选择最经济的路线走
        如果dungeon[i][j]当前格子的数值为负数，dp表中依赖的前一个格子数加上dungeon中当前格子的数量
        如果dungeon[i][j]当前格子的数值为正数或者0，分开讨论
            1、dungeon[i][j]大于等于dp表中依赖的前一个格子的数值-1，则dp表当前格子填1，
            代表只需1点血量，登上dungeon当前格子捡了血瓶，就可以往下走
            2、dungeon[i][j]小于dp表中依赖的前一个格子的数值-1，
            则dp表中依赖的前一个格子的数值减去dungeon当前格子的数值
            表示捡了血瓶，还需要这么多的血量，在可以往下走
         */
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = dungeon[N - 1][M - 1] >= 0 ? 1 : -dungeon[N - 1][M - 1] + 1;
        for (int i = N - 2; i >= 0; i--) {
            if (dungeon[i][M - 1] < 0) {
                dp[i][M - 1] = dp[i + 1][M - 1] - dungeon[i][M - 1];
            } else if (dungeon[i][M - 1] >= dp[i + 1][M - 1] - 1) {
                dp[i][M - 1] = 1;
            } else {
                dp[i][M - 1] = dp[i + 1][M - 1] - dungeon[i][M - 1];
            }
        }
        for (int i = M - 2; i >= 0; i--) {
            if (dungeon[N - 1][i] < 0) {
                dp[N - 1][i] = dp[N - 1][i + 1] - dungeon[N - 1][i];
            } else if (dungeon[N - 1][i] >= dp[N - 1][i + 1] - 1) {
                dp[N - 1][i] = 1;
            } else {
                dp[N - 1][i] = dp[N - 1][i + 1] - dungeon[N - 1][i];
            }
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                int need = Math.min(dp[i + 1][j], dp[i][j + 1]);
                if (dungeon[i][j] < 0) {
                    need = need - dungeon[i][j];
                } else if (dungeon[i][j] >= need - 1) {
                    need = 1;
                } else {
                    need = need - dungeon[i][j];
                }
                dp[i][j] = need;
            }
        }
        return dp[0][0];
    }

}
