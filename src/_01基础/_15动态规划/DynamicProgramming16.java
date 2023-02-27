package _01基础._15动态规划;

/**
 * 较小集合的累加和
 * 给定一个正数数组arr
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 * Created by huangjunyi on 2022/11/30.
 */
public class DynamicProgramming16 {

    /**
     * 暴力递归
     */
    public static int smallSum01(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // arr数组累加和除以2，把题意转换为从arr数组自由选择，
        // 返回最接近但是不超过arr累加和一半的集合累加和
        return process(arr, 0 , sum / 2);
    }

    /**
     * 暴力递归，把题意转换为从index位置，arr后面的数只有做选择，返回最近但是不超过rest的数
     */
    private static int process(int[] arr, int index, int rest) {
        // base case 没有数需要选择了，返回0
        if (index == arr.length) return 0;
        // 不要index位置的数
        int p1 = process(arr, index + 1, rest);
        // 要index位置的数，但是要判断不能超过rest
        int p2 = 0;
        if (arr[index] <= rest) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }
        return Math.max(p1, p2);
    }

    public static int smallSum02(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        /*
        可变参数index，rest
        index：0 ~ arr.length
        rest: 0 ~ sum/2
         */
        int sum = 0;
        for (int num : arr) sum += num;
        int[][] dp = new int[arr.length + 1][(sum / 2) + 1];
        // base case： if (index == arr.length) return 0;
        // 数组初始值本身就是0，不用初始化

        /*
        暴力递归中外层与内层的依赖关系
        // 不要index位置的数
        int p1 = process(arr, index + 1, rest);
        // 要index位置的数，但是要判断不能超过rest
        int p2 = 0;
        if (arr[index] <= rest) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }
        return Math.max(p1, p2);
        所以index依赖index+1
        所以从下往上填
         */
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum / 2; rest++) {
                // 不要index位置的数
                int p1 = dp[index + 1][rest];
                // 要index位置的数，但是要判断不能超过rest
                int p2 = 0;
                if (arr[index] <= rest) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        // 暴力递归最外层：return process(arr, 0 , sum / 2);
        return dp[0][sum / 2];
    }

}
