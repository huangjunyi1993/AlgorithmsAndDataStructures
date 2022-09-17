package _02进阶._27数组累加和问题;

/**
 * 给定一个数组，只有正整数，返回累加和等于k的所有子数组中的最大长度子数组的长度
 * Created by huangjunyi on 2022/9/11.
 */
public class ArrSum01 {

    public static int getMaxLen(int[] arr, int k) {
        int l = 0;
        int r = 0;
        int sum = arr[0];
        int max = 0;
        while (r < arr.length) {
            if (sum == k) {
                max = Math.max(max, r - l + 1);
                sum -= arr[l++];
                continue;
            }
            if (sum > k) {
                sum -= arr[l++];
                continue;
            }
            if (sum < k) {
                r++;
                if (r < arr.length) {
                    sum += arr[r];
                }
            }
        }
        return max;
    }

}
