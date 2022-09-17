package _02进阶._27数组累加和问题;

/**
 * 给定一个数组，返回累加和小于等于k的所有子数组中的最大长度子数组的长度
 * Created by huangjunyi on 2022/9/11.
 */
public class ArrSum03 {

    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {
                minSum[i] =  arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int end = 0;
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            len = Math.max(len, end - i);
            if (i < end) sum -= arr[i];
            else end = i + 1;
        }
        return len;
    }

}
