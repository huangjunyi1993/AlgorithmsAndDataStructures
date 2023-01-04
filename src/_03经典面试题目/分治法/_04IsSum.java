package _03经典面试题目.分治法;

import java.util.TreeSet;

/**
 * arr中的值可能为正，可能为负，可能为0
 * 自由选择arr中的数字，能不能累加得到sum
 *
 * 正常解法 动态规划
 *
 * 分治的方法
 * 如果arr中的数值特别大，动态规划方法依然会很慢
 * 此时如果arr的数字个数不算多(40以内)，哪怕其中的数值很大，分治的方法也将是最优解
 * Created by huangjunyi on 2023/1/4.
 */
public class _04IsSum {

    public static boolean isSum1(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        return process1(arr, arr.length - 1, sum);
    }

    // 可以自由使用arr[0...i]上的数字，能不能累加得到sum
    public static boolean process1(int[] arr, int i, int sum) {
        if (sum == 0) {
            return true;
        }
        if (i == -1) {
            return false;
        }
        return process1(arr, i - 1, sum) || process1(arr, i - 1, sum - arr[i]);
    }

    public static boolean isSum2(int[] arr, int sum) {
        if (sum == 0) return true;
        if (arr == null || arr.length == 0) return false;
        int min = 0;
        int max = 0;
        for (int num : arr) {
            if (num >= 0) max += num;
            else min += num;
        }
        if (sum < min || sum > max) return false;
        // dp[i][j] arr中从0~i，能否凑出累加和为j-min的子序列
        boolean[][] dp = new boolean[arr.length][max - min + 1];
        dp[0][0 - min] = true;
        dp[0][arr[0] - min] = true;
        for (int i = 1; i < arr.length; i++) {
            for (int j = min; j <= max; j++) {
                // i位置数不要，看前面
                dp[i][j - min] = dp[i - 1][j - min];
                if (j - min - arr[i] >= 0 && j - min - arr[i] <= max - min) {
                    // i位置的数要，看j-min-arr[i]
                    dp[i][j - min] |= dp[i - 1][j - min - arr[i]];
                }
            }
        }
        return dp[arr.length - 1][sum - min];
    }

    public static boolean isSum3(int[] arr, int sum) {
        if (sum == 0) return true;
        if (arr == null || arr.length == 0) return false;
        if (arr.length == 1) return arr[0] == sum;
        int min = 0;
        int max = 0;
        for (int num : arr) {
            if (num >= 0) max += num;
            else min += num;
        }
        if (sum < min || sum > max) return false;
        int mid = arr.length >> 1;
        // 分治，左边收一组累加和，右边收一组累加和
        TreeSet<Integer> leftSum = new TreeSet<>();
        TreeSet<Integer> rightSum = new TreeSet<>();
        process(arr, 0, mid, 0, leftSum);
        process(arr, mid, arr.length, 0, rightSum);
        // 两组间匹配，因为组内有一个是为0的累加和，所以不用单独看组内是否有累加和为sum的
        for (Integer left : leftSum) {
            if (rightSum.contains(sum - left)) {
                return true;
            }
        }
        return false;
    }

    private static void process(int[] arr, int i, int end, int sum, TreeSet<Integer> set) {
        if (i == end) {
            set.add(sum);
            return;
        }
        process(arr, i + 1, end, sum, set);
        process(arr, i + 1, end, sum +arr[i], set);
    }

    // 为了测试
    // 生成长度为len的随机数组
    // 值在[-max, max]上随机
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
        }
        return arr;
    }

    // 对数器验证所有方法
    public static void main(String[] args) {
        int N = 20;
        int M = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(size, M);
            int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
            boolean ans1 = isSum1(arr, sum);
            boolean ans2 = isSum2(arr, sum);
            boolean ans3 = isSum3(arr, sum);
            boolean ans4 = isSum3(arr, sum);
            if (ans1 ^ ans2 || ans3 ^ ans4 || ans1 ^ ans3) {
                System.out.println("出错了！");
                System.out.print("arr : ");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("sum : " + sum);
                System.out.println("方法一答案 : " + ans1);
                System.out.println("方法二答案 : " + ans2);
                System.out.println("方法三答案 : " + ans3);
                System.out.println("方法四答案 : " + ans4);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
