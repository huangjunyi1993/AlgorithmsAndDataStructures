package _03经典面试题目.动态规划;

/**
 * 股票问题三连：
 *
 * 1、给定一个数组arr，从左到右表示昨天从早到晚股票的价格。
 * 作为一个事后诸葛亮，你想知道如果只做一次交易，且每次交易只买卖一股，返回能挣到的最大钱数
 *
 * 2、给定一个数组arr，从左到右表示昨天从早到晚股票的价格,作为一个事后诸葛亮，
 * 你想知道如果随便交易，且每次交易只买卖一股，返回能挣到的最大钱数。
 *
 * 3、给定一个数组arr，从左到右表示昨天从早到晚股票的价格
 * 作为一个事后诸葛亮，你想知 道如果交易次数不超过K次，
 * 且每次交易只买卖一股，返回能挣到的最大钱数
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _023StockProblemThree {

    public static int stock01(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int min = arr[0];
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            res = Math.max(res, arr[i] - min);
        }
        return res;
    }

    public static int stock02(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int pre = arr[0];
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > pre) res += arr[i] - pre;
            pre = arr[i];
        }
        return res;
    }

    public static int stock03(int[] arr, int K) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;

        // 如果任意交易次数大于数组长度的二分之一，那么和任意交易无数次没区别
        if (K > N / 2) return stock02(arr);

        /*
        dp表
        表示从0~i时间点，任意交易j次
        能获得的最大收益
         */
        int[][] dp = new int[N][K + 1];
        int res = 0;
        for (int j = 1; j <= K; j++) {
            /*
            dp[i][j]：
            1、dp[i - 1][j] 代表i时间点不做交易，j次交易都发生在i - 1时间点及以前（同一列上一行）
            2、dp[i][j - 1] - arr[i] + arr[i]，最后一次在i时间点买进又卖出
            3、dp[i - 1][j - 1] - arr[i - 1] + arr[i]，最后一次交易在i - 1时间点买进，i时间点卖出
            4、dp[i - 2][j - 1] - arr[i - 2] + arr[i]，最后一个交易在i - 2时间点买进，i时间点卖出
            5、dp[i - 3][j - 1] - arr[i - 3] + arr[i]，最后一次交易在i - 3时间点买进，i时间点卖出
            ......
            取max

            优化
            dp[i + 1][j]：
            1、dp[i][j] 代表i + 1时间点不做交易，j次交易都发生在i时间点及以前（同一列上一行）
            2、dp[i + 1][j - 1] - arr[i + 1] + arr[i + 1]，最后一次在i + 1时间点买进又卖出
            3、(dp[i][j - 1] - arr[i]) + arr[i + 1]，最后一次交易在i时间点买进，i + 1时间点卖出
            4、(dp[i - 1][j - 1] - arr[i - 1]) + arr[i + 1]，最后一个交易在i - 1时间点买进，i + 1时间点卖出
            5、(dp[i - 2][j - 1] - arr[i - 2]) + arr[i + 1]，最后一次交易在i - 2时间点买进，i + 1时间点卖出
            5、(dp[i - 3][j - 1] - arr[i - 3]) + arr[i + 1]，最后一次交易在i - 3时间点买进，i + 1时间点卖出
            上面括号的部分，在求dp[i][j]是重复的，可以在求dp[i][j]是取出来，比较一个max，然后在加上arr[i]
            比较出的max作为临时遍历t，在求dp[i + 1][j]是可以重用

             */
            int t = dp[0][j - 1] - arr[0];
            for (int i = 1; i < N; i++) {
                t = Math.max(
                        t,
                        dp[i][j - 1] - arr[i]
                );
                dp[i][j] = Math.max(
                        dp[i - 1][j],
                        t + arr[i]
                );
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

}
