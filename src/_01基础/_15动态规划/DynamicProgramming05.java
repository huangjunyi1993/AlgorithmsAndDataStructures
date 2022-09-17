package _01基础._15动态规划;

/**
 * 货币组合问题
 * 给定一个整形数组arr表示一组不同面值的货币
 * 给定一个整形aim，表示需要通过不同的货币拼凑一起到总额aim
 * 从arr中取货币，每种货币都可以取若干张
 * 求有多少种组合方法
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming05 {

    public static int method01(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        return process01(arr, 0, aim);
    }

    /**
     * 暴力递归
     * @param arr 货币数组
     * @param index 当前货币
     * @param remain 剩余要组出的总额
     * @return
     */
    private static int process01(int[] arr, int index, int remain) {
        if (index == arr.length) return remain == 0 ? 1 : 0;
        int res = 0;
        for (int i = 0; i * arr[index] <= remain; i++) res += process01(arr, index + 1, remain - i * arr[index]);
        return res;
    }

    public static int method02(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i <= arr.length; i++) for (int j = 0; j <= aim; j++) dp[i][j] = -1;
        return process02(arr, 0, aim, dp);
    }

    /**
     * 记忆化搜索
     * @param arr 货币数组
     * @param index 当前货币
     * @param remain 剩余要组出的总额
     * @return
     */
    private static int process02(int[] arr, int index, int remain, int[][] dp) {
        if (dp[index][remain] != -1) return dp[index][remain];
        if (index == arr.length) return dp[index][remain] = remain == 0 ? 1 : 0;
        int res = 0;
        for (int i = 0; i * arr[index] <= remain; i++) res += process02(arr, index + 1, remain - i * arr[index], dp);
        return dp[index][remain] = res;
    }

    /**
     * 动态规划
     * @param arr
     * @param aim
     * @return
     */
    public static int method03(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--)
            for (int remain = 0; remain <= aim; remain++)
                for (int i = 0; i * arr[index] <= remain; i++)
                    dp[index][remain] += dp[index + 1][remain- i * arr[index]];
        return dp[0][aim];
    }

    /**
     * 动态规划优化
     * @param arr
     * @param aim
     * @return
     */
    public static int method04(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--)
            for (int remain = 0; remain <= aim; remain++)
                dp[index][remain] = remain >= arr[index] ?
                        dp[index + 1][remain] + dp[index][remain - arr[index]] :
                        dp[index + 1][remain];
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 50, 100};
        int aim = 1000;
        System.out.println(method01(arr, aim));
        System.out.println(method02(arr, aim));
        System.out.println(method03(arr, aim));
        System.out.println(method04(arr, aim));
    }
}
