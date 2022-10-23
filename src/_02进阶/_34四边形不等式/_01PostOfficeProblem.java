package _02进阶._34四边形不等式;

/**
 * 给定一个数组arr，arr为正整数有序数组，代表居民点左边
 * 再给定一个正整数num，代表邮局数量
 * 邮局可以建在任意居民点上
 * 求邮局离居民点的最短距离
 *
 * 还没有使用四边形不等式优化
 * Created by huangjunyi on 2022/10/15.
 */
public class _01PostOfficeProblem {

    public static int minDis(int[] arr, int num) {
        /*
        生成一个N行N列二维数组record，recode[i][j]表示i~j范围上建一个邮局，最短距离是多少
        生成一张N行num列二维表dp，dp[i][j]表示0~i号居民点，建立j个邮局，最短距离是多少
        dp表的生成依赖到recode表
         */
        if (arr == null || arr.length < 2 || num < 1) return 0;
        int[][] recode = getRecode(arr);
        int N = arr.length;
        int[][] dp = new int[N][num + 1];

        /*
        dp表第0列不用填，0个邮局，距离无限大
        dp表第0行不用填，1个居民点，距离都是0
        先初始化第1列
         */
        for (int i = 0; i < N; i++) {
            dp[i][1] = recode[0][i];
        }

        /*
         填剩下的
         j大于等于i的也不用填
         因为那表示邮局数量最少和居民点一样多
         那就一人一个，距离为0
          */
        for (int i = 1; i < N; i++) {
            for (int j = 2; j < Math.min(i, num); j++) {
                // 初始化dp[i][j]，表示0~i居民点由一个邮局负责
                dp[i][j] = recode[0][i];
                // 枚举最后一个居民点的负责范围 k ~ i <= 这里存在枚举行为，可以用四边形不等式优化
                for (int k = i; k > 0; k--) {
                    dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + recode[k][i]);
                }
            }
        }
        return dp[N - 1][num];
    }

    private static int[][] getRecode(int[] arr) {
        /*
        每次都假设邮局正处于范围的中间
        就recode[L][R]时，就是L~R-1范围的距离recode[L][R - 1]，加上新增居民点离邮局的距离
        只需要填上半部分，下半部分不需要填，因为L不可能大于R
         */
        int N = arr.length;
        int[][] recode = new int[N][N];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                recode[L][R] = recode[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        return recode;
    }

}
