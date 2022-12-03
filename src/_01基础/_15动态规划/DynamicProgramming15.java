package _01基础._15动态规划;

/**
 * 数字切分
 * 给你一个数字num，将它切开
 * 比如给你7，你可以切成2/2/3，也可以切成3/4，但是不能切成3/2/2或4/3，也就是前面的数不能大于后面的数
 * 返回一个数一共有多少中切分方法
 * Created by huangjunyi on 2022/11/30.
 */
public class DynamicProgramming15 {

    /**
     * 暴力递归
     * @param n 要切分的数字
     */
    public static int splitNumber01(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return process(1, n);
    }

    /**
     * 暴力递归
     * 前一个数字是preNum，剩下要切分的数字是restNum，切分restNum的切分方法数
     * @param preNum 前一个数字
     * @param restNum 剩下要切分的数字
     * @return
     */
    private static int process(int preNum, int restNum) {
        // restNum切成了0，有效切分方法数1
        if (restNum == 0)return 1;
        // 前一个数比restNum，无效切分
        if (preNum > restNum) return 0;
        // 当前切出preNum，切出preNum+1，切出preNum+2......，方法数累加
        int ways = 0;
        for (int i = preNum; i <= restNum; i++) {
            ways += process(i, restNum - i);
        }
        return ways;
    }

    /**
     * 动态规划
     * @param n 要切分的数字
     * @return
     */
    private static int splitNumber02(int n) {
        /*
        观察暴力递归的可变参数个数，已经可变参数范围
        preNum：1~n
        restNum：0~n
        dp[preNum][restNum]
        前一个数字是preNum，剩下要切分的数字是restNum，切分restNum的切分方法数
         */
        int[][] dp = new int[n + 1][n + 1];
        /*
        根据暴力递归的base case初始化dp表
        if (restNum == 0)return 1;
        if (preNum > restNum) return 0;
         */
        for (int preNum = 1; preNum <= n; preNum++) {
            dp[preNum][0] = 1;
        }
        /*
        观察暴力递归的外层递归和内层递归的依赖关系，确定dp表的填写顺序
        int ways = 0;
        for (int i = preNum; i <= restNum; i++) {
            ways += process(i, restNum - i);
        }
        return ways;
        preNum依赖preNum+1、preNum+2......
        所以从下往上填

        因为preNum > restNum 都是无效的，所以只需要填上半部分
         */
        for (int preNum = n; preNum >= 1; preNum--) {
            for (int restNum = preNum; restNum <= n; restNum++) {
                int ways = 0;
                for (int i = preNum; i <= restNum; i++) {
                    ways += dp[i][restNum - i];
                }
                dp[preNum][restNum] = ways;
            }
        }
        // 根据暴力递归的最外层递归确定返回值
        // return process(1, n);
        return dp[1][n];
    }

    /**
     * 动态规划 枚举行为优化
     * @param n 要切分的数字
     * @return
     */
    private static int splitNumber03(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int preNum = 1; preNum <= n; preNum++) {
            dp[preNum][0] = 1;
        }
        for (int preNum = n; preNum >= 1; preNum--) {
            for (int restNum = preNum; restNum <= n; restNum++) {
                /*int ways = 0;
                for (int i = preNum; i <= restNum; i++) {
                    ways += dp[i][restNum - i];
                }
                dp[preNum][restNum] = ways;*/

                /*
                观察dp[2][10]
                dp[2][10] = dp[2][8] + dp[3][7] + dp[4][6] + dp[5][5]
                dp[3][10] =            dp[3][7] + dp[4][6] + dp[5][5]
                所以：
                dp[2][10] = dp[2][8] + dp[3][10]
                dp[preNum][restNum] = dp[preNum][restNum - preNum] + dp[preNum + 1][restNum]
                 */
                dp[preNum][restNum] = dp[preNum][restNum - preNum];
                if (preNum != n) dp[preNum][restNum] += dp[preNum + 1][restNum];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int n = 68;
        System.out.println(splitNumber01(n));
        System.out.println(splitNumber02(n));
        System.out.println(splitNumber03(n));
    }
}
