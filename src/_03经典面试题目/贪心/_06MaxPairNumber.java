package _03经典面试题目.贪心;

import java.util.Arrays;

/**
 * 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。
 * 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人
 * 返回最多可以同时有多少场比赛
 * Created by huangjunyi on 2022/12/23.
 */
public class _06MaxPairNumber {
    public static int maxPairNum(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k < 0) return 0;
        /*
        贪心：
        优先让小值的一对配成异常比赛

        排序 + 窗口双指针
         */
        Arrays.sort(arr);
        int left = 0;
        int right = 0;
        int res = 0;
        boolean[] visited = new boolean[arr.length];
        while (left < arr.length && right < arr.length) {
            if (visited[left]) {
                left++;
            } else if (left == right) {
                right++;
            } else {
                int distance = arr[right] - arr[left];
                if (distance == k) {
                    res++;
                    visited[right] = true;
                    left++;
                    right++;
                } else if (distance > k) {
                    left++;

                } else {
                    right++;
                }
            }
        }
        return res;
    }
}
