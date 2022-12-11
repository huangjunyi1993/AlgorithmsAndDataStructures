package _03经典面试题目.动态规划;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 *
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 *
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 *
 * Created by huangjunyi on 2022/10/21.
 */
public class _40StrangePrinter {
    public int strangePrinter(String s) {
        /*
        范围的上的尝试模型

        先把对角线而第二条对接线填好

        对角线是1

        第二条对角线两个字符相同为1，不同为2

        然后从下往上，左右往左开始填

        每个格子枚举从L到R先单独转左半部分，再单独转有半部分的情况，切分点记为K

        如果L位置的字符，和K位置字符相等，那么总转数-1，因为L和K位置可以在同一转转得
         */

        char[] chs = s.toCharArray();
        int N = chs.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 1 : 2;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = R - L + 1;
                for (int K = L + 1; K <= R; K++) {
                    dp[L][R] = Math.min(dp[L][R], dp[L][K - 1] + dp[K][R] - (chs[L] == chs[R] ? 1 : 0));
                }
            }
        }
        return dp[0][N - 1];
    }
}
