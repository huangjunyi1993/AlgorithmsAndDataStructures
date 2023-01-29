package _05面试真题;

import java.util.Arrays;

/**
 * 来自微软面试
 * 给定一个正数数组arr长度为n、正数x、正数y
 * 你的目标是让arr整体的累加和<=0
 * 你可以对数组中的数num执行以下三种操作中的一种，且每个数最多能执行一次操作 :
 * 1）不变
 * 2）可以选择让num变成0，承担x的代价
 * 3）可以选择让num变成-num，承担y的代价
 * 返回你达到目标的最小代价
 * 数据规模 : 面试时面试官没有说数据规模
 * Created by huangjunyi on 2023/1/20.
 */
public class Code35_SumNoPositiveMinCost {
    /**
     * 暴力递归（从左往右）
     */
    public static int minOpStep1(int[] arr, int x, int y) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process(arr, x, y, 0, sum);
    }

    /**
     * arr数组从index位置开始（arr[index...]），往右，
     * 剩余rest数要扣掉，扣到小于等于0，最少需要多少代价
     * 每个位置不要、变0、变负数，3种尝试走一遍
     * @param arr 正数数组
     * @param x x代价（把数变为0）
     * @param y y代价（把数变为负）
     * @param index arr数组下标
     * @param rest 剩余要扣掉的数
     * @return
     */
    private static int process(int[] arr, int x, int y, int index, int rest) {
        if (rest <= 0) return 0;
        if (index == arr.length) return Integer.MAX_VALUE;
        int p1 = process(arr, x, y ,index + 1, rest);
        int p2 = process(arr, x, y, index + 1, rest - arr[index]);
        if (p2 != Integer.MAX_VALUE) {
            p2 += x;
        }
        int p3 = process(arr, x, y, index + 1, rest - 2 * arr[index]);
        if (p3 != Integer.MAX_VALUE) {
            p3 += y;
        }
        return Math.min(p1, Math.min(p2, p3));
    }

    /**
     * 贪心（最优解）
     */
    public static int minOpStep2(int[] arr, int x, int y) {

        // 1、数组从大到小排序
        Arrays.sort(arr);
        reverse(arr);

        // 2.1 如果x代价大于等于y，没有必要选x代价，直接通过y代价搞定
        if (x >= y) {
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            int cost = 0;
            for (int i = 0; i < arr.length; i++) {
                if (sum <= 0) break;
                sum -= 2 * arr[i];
                cost += y;
            }
            return cost;
        }
        // 2.2 前后指针搞定y代价和y代价的分割点
        // 分成三个区域[y|x|无]
        // 因为一个更大的数都没有选y，后面的也不应该再选，x同理
        // jzl,wtzgdm,tl3gxs,zjsswdlxcd,qnmd!!!!!!!!!!!!!
        else {
            int leftSum = 0;
            int rightSum = 0;
            int cost = arr.length * x;
            for (int l = 0, r = arr.length; l < r - 1; l++) {
                leftSum += arr[l];
                while (r - 1 > l && rightSum + arr[r - 1] <= leftSum) {
                    rightSum += arr[r - 1];
                    r--;
                }
                cost = Math.min(cost, (l + 1) * y + (r - l - 1) * x);
            }
            return cost;
        }
    }

    private static void reverse(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
        }
    }

    // 为了测试
    public static int[] randomArray(int len, int v) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int n = 12;
        int v = 20;
        int c = 10;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n);
            int[] arr = randomArray(len, v);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int x = (int) (Math.random() * c);
            int y = (int) (Math.random() * c);
            int ans1 = minOpStep1(arr1, x, y);
            int ans2 = minOpStep2(arr2, x, y);
            if (ans1 != ans2) {
                System.out.println("出错了! ans1:" + ans1 + " ans2:" + ans2);
            }
        }
        System.out.println("测试结束");

    }
}
