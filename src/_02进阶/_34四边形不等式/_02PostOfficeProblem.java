package _02进阶._34四边形不等式;

/**
 * 给定一个数组arr，arr为正整数有序数组，代表居民点左边
 * 再给定一个正整数num，代表邮局数量
 * 邮局可以建在任意居民点上
 * 求邮局离居民点的最短距离
 *
 * 用四边形不等式优化枚举行为
 * 只要符合三种情况：
 * 1）存在枚举行为
 * 2）每个格子和它上、右或下、左存在某种单调关系
 * 3）是一个区间划分问题
 * 就可以用四边形不等式尝试优化，优化的时枚举行为，缩短枚举行为的范围
 * 用一个choose[i][j]记录dp[i][j]是枚举行为最优取值的枚举值k
 * 下次填其他格子时就可以通过choose记录的当时获得最优值的枚举值，作为其枚举行为的上界或下界，缩短枚举行为的范围
 *
 * 还有第四个条件
 * 4）dp[i][j]不会同时依赖本行和本列的某个值或某些值，即最多只会依赖本行某个值或本列某个值，或者都不依赖
 * 比如只依赖本列的值，那就从上往下填，然后在从右往左，一个格子就依赖上一个格子和右边格子得出枚举k的上下界
 * 比如只依赖本行的值，那就从左往右填，然后从下往上填，一个格子就依赖左边格子和下以边格子得出枚举k的上下界
 * 比如只依赖左边和左上方的左右格子，但不依赖本列，可以先填好第一个列，然后从第二列最底下一个格子从下往上，从左往右填，一个格子就依赖左边和下边格子得出枚举k的上下界
 *
 * 优化的原理：
 * 比如：在数组上切一刀，求min(max(leftSum, rightSum))
 * 如果数组在0~N范围上切一刀，切在i位置
 * 现在数组长度加1，求0~N+1，那么其实不需要从0开始尝试，从i开始即可
 * 因为0~N和0~N+1是存在某种单调关系的
 * 0~N在i位置且一刀是最优解
 * 如果0~N+1在i左边的位置切一刀，肯定不是最优解，因为rightSum会更大
 *
 * Created by huangjunyi on 2022/10/15.
 */
public class _02PostOfficeProblem {

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
        有一个N行N列的二维表，记录填dp[i][j]是，k当时的值是多少
        那么在填一个格子的时候，枚举k的范围，就有一个下界和上界
        k的下界就是它上一个格子的k的取值，下界就是它右边一个格子的取值
         */
        int[][] choose = new int[N][N];

        /*
         填剩下的
         j大于等于i的也不用填
         因为那表示邮局数量最少和居民点一样多
         那就一人一个，距离为0
          */
        for (int i = 1; i < N; i++) {
            for (int j = Math.min(i, num); i >= 2; j--) {

                int down = choose[i - 1][j]; // 枚举k时的下界
                int up = j == Math.min(i, num) ? i : choose[i][j + 1]; // 枚举k时的上界

                // 初始化dp[i][j]，表示0~i居民点由一个邮局负责
                dp[i][j] = recode[0][i];
                // 枚举最后一个居民点的负责范围 k ~ i <= 这里用四边形不等式优化，枚举范围从1~i缩短到从down~up
                for (int k = Math.max(1, down); k <= Math.min(i, up); k++) {
                    if (dp[k - 1][j - 1] + recode[k][i] < dp[i][j]) {
                        dp[i][j] = dp[k - 1][j - 1] + recode[k][i];
                        choose[i][j] = k;
                    }
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
