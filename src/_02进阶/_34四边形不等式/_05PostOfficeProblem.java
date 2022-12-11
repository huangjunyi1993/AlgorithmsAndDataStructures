package _02进阶._34四边形不等式;

import java.util.Arrays;

/**
 * 给定一个数组arr，arr为正整数有序数组，代表居民点坐标
 * 再给定一个正整数num，代表邮局数量
 * 邮局可以建在任意居民点上
 * 求邮局离居民点的最短距离
 *
 * 还没有使用四边形不等式优化
 * Created by huangjunyi on 2022/10/15.
 */
public class _05PostOfficeProblem {

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
            for (int j = 2; j <= Math.min(i, num); j++) {
                // 初始化dp[i][j]，表示0~i居民点都由最后一个邮局负责
                dp[i][j] = recode[0][i];
                // 枚举最后一个居民点的负责范围 k ~ i <= 这里存在枚举行为，可以用四边形不等式优化
                for (int k = i; k > 0; k--) {
                    dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + recode[k][i]);
                }
                // 还有一个是0~i范围，都由前j-1个邮局负责，最后一个邮局歇菜，不过也不可能
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
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

    public static int min2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int N = arr.length;
        int[][] w = new int[N + 1][N + 1];
        for (int L = 0; L < N; L++) {
            for (int R = L + 1; R < N; R++) {
                w[L][R] = w[L][R - 1] + arr[R] - arr[(L + R) >> 1];
            }
        }
        int[][] dp = new int[N][num + 1];
        int[][] best = new int[N][num + 1];
        for (int i = 0; i < N; i++) {
            dp[i][1] = w[0][i];
            best[i][1] = -1;
        }
        for (int j = 2; j <= num; j++) {
            for (int i = N - 1; i >= j; i--) {
                int down = best[i][j - 1];
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    int rightCost = leftEnd == i ? 0 : w[leftEnd + 1][i];
                    int cur = leftCost + rightCost;
                    if (cur <= ans) {
                        ans = cur;
                        bestChoose = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestChoose;
            }
        }
        return dp[N - 1][num];
    }

    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = minDis(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");

    }

}
