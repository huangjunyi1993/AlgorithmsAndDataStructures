package _01基础._15动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 货币组合问题2
 * 给定一个整形数组arr，表示一组货币
 * 数组每个值都是一张货币
 * 但是值相同的货币没有任何不同
 * 给定一个整形aim，表示需要通过不同的货币拼凑一起到总额aim
 * 从arr中取货币
 * 求有多少种组合方法
 *
 * 例如：arr = [1,2,1,1,2,1,2]，aim = 4
 * 方法：1+1+1+1 1+1+2 2+2
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming12 {

    /**
     * 暴力递归
     * @param arr 货币数组
     * @param aim 目标总额
     * @return 方法数
     */
    public static int method01(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        // key：货币面值，value：货币张数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) map.put(arr[i], map.get(arr[i]) + 1);
            else map.put(arr[i], 1);
        }
        // 货币面值数组
        int[] money = new int[map.size()];
        // 货币张数数组
        int[] count = new int[map.size()];
        int index = 0;
        // 根据map初始化money和count数组
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            money[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        return process01(money, count, 0, aim);
    }

    /**
     * 暴力递归
     * @param money 货币面值数组
     * @param count 货币张数数组
     * @param index 第index号货币开始，往后的货币，自由选择
     * @param rest 剩余要凑够的钱
     * @return 方法数
     */
    private static int process01(int[] money, int[] count, int index, int rest) {
        if (index == money.length) return rest == 0 ? 1 : 0;
        int res = 0;
        // index号货币，使用0张，使用1张，使用2张，......，使用count[index]张
        for (int i = 0; rest - money[index] * i >= 0 && i <= count[index]; i++) {
            res += process01(money, count, index + 1, rest - money[index] * i);
        }
        return res;
    }

    /**
     * 动态规划
     * @param arr 货币数组
     * @param aim 目标总额
     * @return 方法数
     */
    public static int method02(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        // key：货币面值，value：货币张数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) map.put(arr[i], map.get(arr[i]) + 1);
            else map.put(arr[i], 1);
        }
        // 货币面值数组
        int[] money = new int[map.size()];
        // 货币张数数组
        int[] count = new int[map.size()];
        int index = 0;
        // 根据map初始化money和count数组
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            money[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }

        /*
        暴力递归2个可变参数，二维表，观察可变参数范围，确定dp表的长度
        dp[index][rest] index号货币开始往后自由选择，凑够rest元，的方法数
         */
        int[][] dp = new int[money.length + 1][aim + 1];
        // 根据 base case 初始化dp表：if (index == money.length) return rest == 0 ? 1 : 0;
        dp[money.length][0] = 1;

        /*
        index号货币，使用0张，使用1张，使用2张，......，使用count[index]张
        for (int i = 0; rest - money[index] * i >= 0 && i <= count[index]; i++) {
            res += process01(money, count, index + 1, rest - money[index] * i);
        }
        */
        for (index = money.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int res = 0;
                for (int i = 0; rest - money[index] * i >= 0 && i <= count[index]; i++) {
                    res += dp[index + 1][rest - money[index] * i];
                }
                dp[index][rest] = res;
            }
        }
        // return process01(money, count, 0, aim);
        return dp[0][aim];
    }

    /**
     * 动态规划 枚举优化
     * @param arr 货币数组
     * @param aim 目标总额
     * @return 方法数
     */
    public static int method03(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) map.put(arr[i], map.get(arr[i]) + 1);
            else map.put(arr[i], 1);
        }
        int[] money = new int[map.size()];
        int[] count = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            money[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }

        int[][] dp = new int[money.length + 1][aim + 1];
        dp[money.length][0] = 1;
        for (index = money.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                /*
                观察：
                假设count[index] = 3，index号货币有3张
                dp[index][rest]                    = dp[index + 1][rest] + dp[index + 1][rest - 1 * money[index]] + dp[index + 1][rest - 2 * money[index]] + dp[index + 1][rest - 3 * money[index]]
                dp[index][rest - 1 * money[index]] =                       dp[index + 1][rest - 1 * money[index]] + dp[index + 1][rest - 2 * money[index]] + dp[index + 1][rest - 3 * money[index]] + dp[index + 1][rest - 4 * money[index]]
                所以：
                dp[index][rest]                    = dp[index + 1][rest] + dp[index][rest - 1 * money[index]] - dp[index + 1][rest - 4 * money[index]];
                dp[index][rest]                    = dp[index + 1][rest] + dp[index][rest - 1 * money[index]] - dp[index + 1][rest - (count[index] + 1) * money[index]];
                 */
                dp[index][rest] = dp[index + 1][rest];
                if (rest - money[index] >= 0) dp[index][rest] += dp[index][rest - money[index]];
                if (rest - (count[index] + 1) * money[index] >= 0) dp[index][rest] -= dp[index + 1][rest - (count[index] + 1) * money[index]];
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,1,1,2,1,2};
        int aim = 4;
        System.out.println(method01(arr, aim));
        System.out.println(method02(arr, aim));
        System.out.println(method03(arr, aim));
    }
}
