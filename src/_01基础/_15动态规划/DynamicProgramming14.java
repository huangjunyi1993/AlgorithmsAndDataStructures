package _01基础._15动态规划;

/**
 * 货币组合问题3
 * arr是面值数组，其中的值都是正数且没有重复。
 * 再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数。
 *
 * Created by huangjunyi on 2022/11/30.
 */
public class DynamicProgramming14 {

    /**
     * 暴力递归
     */
    public static int minCoins01(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    /**
     * 暴力递归
     * @param arr 货币数组
     * @param index 当前货币
     * @param rest 剩余要凑够的钱数
     * @return
     */
    private static int process(int[] arr, int index, int rest) {
        // base case 货币都决定完了，剩余要凑够的钱数为0，返回0（代表凑够0元需要0张货币），否则返回MAX，代表无效
        if (index == arr.length) return rest == 0 ? 0 : Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        // index号货币要0张，要1张，要2张......拿最少值
        for (int i = 0; i * arr[index] <= rest; i++) {
            int next = process(arr, index + 1, rest - i * arr[index]);
            if (next != Integer.MAX_VALUE) min = Math.min(min, next + i);
        }
        return min;
    }

    /**
     * 动态规划
     * @param arr 货币数组
     * @param aim 要凑够的目标货币数
     * @return
     */
    public static int minCoins02(int[] arr, int aim) {
        /*
        根据暴力递归，可变参数的个数，范围，推测dp表的结构
        可变参数：index rest => 二维dp表
        范围：index：0~arr.length rest: 0~aim
        dp[index][rest] 从index号货币开始往后自由选择，凑出rest元的最少货币数
         */
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 根据暴力递归 base case 初始化dp表
        // if (index == arr.length) return rest == 0 ? 1 : Integer.MAX_VALUE;
        dp[arr.length][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }
        /*
        观察暴力递归外层与内层的依赖关系，确定填表的方向

        int min = Integer.MAX_VALUE;
        // index号货币要0张，要1张，要2张......拿最少值
        for (int i = 0; i * arr[index] <= rest; i++) {
            int next = process(arr, index + 1, rest - i * arr[index]);
            if (next != Integer.MAX_VALUE) min = Math.min(min, next + i);
        }
        index 依赖 index + 1
        所以从下往上填
         */
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int min = Integer.MAX_VALUE;
                // index号货币要0张，要1张，要2张......拿最少值
                for (int i = 0; i * arr[index] <= rest; i++) {
                    int next = dp[index + 1][rest - i * arr[index]];
                    if (next != Integer.MAX_VALUE) min = Math.min(min, next + i);
                }
                dp[index][rest] = min;
            }
        }
        // 根据暴力递归最外层递归的参数，确定返回值
        // return process(arr, 0, aim);
        return dp[0][aim];
    }

    /**
     * 动态规划 枚举行为优化
     * @param arr 货币数组
     * @param aim 要凑够的目标货币数
     * @return
     */
    public static int minCoins03(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                /*int min = Integer.MAX_VALUE;
                for (int i = 0; i * arr[index] <= rest; i++) {
                    int next = dp[index + 1][rest - i * arr[index]];
                    if (next != Integer.MAX_VALUE) min = Math.min(min, next + i);
                }
                dp[index][rest] = min;*/

                /*
                观察dp[5][10] 假设arr[5]=3：
                dp[5][10] = Math.min(dp[6][10], dp[6][7] + 1, dp[6][4] + 2, dp[6][1] + 3)
                dp[5][7]  = Math.min(           dp[6][7],     dp[6][4] + 1, dp[6][1] + 2)
                所以：
                dp[5][10] = Math.min(dp[6][10], dp[5][7] + 1)
                dp[index][rest] = Math.min(dp[index + 1][rest], dp[index][rest- arr[index]] + 1)
                 */

                dp[index][rest] = dp[index + 1][rest];
                if (rest- arr[index] >= 0 && dp[index][rest- arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest- arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

}
