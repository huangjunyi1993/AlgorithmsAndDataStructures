package _02进阶._27数组累加和问题;

/**
 * 给定一个数组，只有正整数，返回累加和等于k的所有子数组中的最大长度子数组的长度
 * Created by huangjunyi on 2022/9/11.
 */
public class ArrSum01 {

    public static int getMaxLen(int[] arr, int k) {
        int l = 0; // 窗口左边界
        int r = 0; // 窗口右边界
        int sum = arr[0]; // 当前窗口内累加和
        int max = 0; // 子数组为k的最大长度
        while (r < arr.length) {
            if (sum == k) { // 窗口累计和等于k，收集答案，PK一下
                max = Math.max(max, r - l + 1);
                sum -= arr[l++];
                continue;
            }
            if (sum > k) { // 窗口累计和大于k，左指针右移，窗口缩小
                sum -= arr[l++];
                continue;
            }
            if (sum < k) { // 窗口累计和大于k，右指针右移，窗口扩大
                r++;
                if (r < arr.length) {
                    sum += arr[r];
                }
            }
        }
        return max;
    }

}
