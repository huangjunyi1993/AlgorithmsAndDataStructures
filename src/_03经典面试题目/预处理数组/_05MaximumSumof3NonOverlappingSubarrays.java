package _03经典面试题目.预处理数组;

/**
 * 给定一个数组arr，给定一个正数k。选出3个不重叠的子数组，每个子数组长度都是k，返回最大的三个子数组的起始下标。
 *
 * Created by huangjunyi on 2022/10/15.
 */
public class _05MaximumSumof3NonOverlappingSubarrays {

    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        // range[i]表示从i开始往下的k个数的累加和
        int[] range = new int[N];
        // left[i]表示0~i范围内最大的长度为k的子数组中累加和最大的子数组的起始下标
        int[] left = new int[N];
        // right[i]表示i~N-1范围内最大的长度为k的子数组中累加和最大的子数组的起始下标
        int[] right = new int[N];

        /*
        初始化预处理数组left，range和right
        left和range的初始化同时进行
        单独计算right
         */
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        range[0] = sum;
        left[k - 1] = 0;
        int max = sum;
        for (int i = k; i < N; i++) {
            sum = sum - nums[i - k] + nums[i];
            range[i - k + 1] = sum;
            left[i] = left[i - 1];
            if (sum > max) {
                max = sum;
                left[i] = i - k + 1;
            }
        }
        sum = 0;
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        right[N - k] = N - k;
        max = sum;
        for (int i = N - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];
            right[i] = right[i + 1];
            if (sum > max) {
                max = sum;
                right[i] = i;
            }
        }

        /*
        遍历数组所可能的切分方案
        根据left，range，right 3个预处理数组
        计算最大的累加和
        然后记录3个数组的其实下标
         */
        int a = 0;
        int b = 0;
        int c = 0;
        max = 0;
        for (int i = k; i <= N - 2 * k + 1; i++) {
            int part1 = range[left[i - 1]];
            int part2 = range[i];
            int part3 = range[right[i + k]];
            if (part1 + part2 + part3 > max) {
                a = left[i - 1];
                b = i;
                c = right[i + k];
            }
        }
        return new int[] {a, b, c};
    }

}

class Code04_MaximumSumof3NonOverlappingSubarrays {

    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[] range = new int[N];
        int[] left = new int[N];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        range[0] = sum;
        left[k - 1] = 0;
        int max = sum;
        for (int i = k; i < N; i++) {
            sum = sum - nums[i - k] + nums[i];
            range[i - k + 1] = sum;
            left[i] = left[i - 1];
            if (sum > max) {
                max = sum;
                left[i] = i - k + 1;
            }
        }
        sum = 0;
        for (int i = N - 1; i >= N - k; i--) {
            sum += nums[i];
        }
        max = sum;
        int[] right = new int[N];
        right[N - k] = N - k;
        for (int i = N - k - 1; i >= 0; i--) {
            sum = sum - nums[i + k] + nums[i];
            right[i] = right[i + 1];
            if (sum >= max) {
                max = sum;
                right[i] = i;
            }
        }
        int a = 0;
        int b = 0;
        int c = 0;
        max = 0;
        for (int i = k; i < N - 2 * k + 1; i++) { // 中间一块的起始点  (0...k-1)选不了   i == N-1
            int part1 = range[left[i - 1]];
            int part2 = range[i];
            int part3 = range[right[i + k]];
            if (part1 + part2 + part3 > max) {
                max = part1 + part2 + part3;
                a = left[i - 1];
                b = i;
                c = right[i + k];
            }
        }
        return new int[] { a, b, c };
    }

    public static void main(String[] args) {
        int[] nums = { 9, 8, 7, 6, 2, 2, 2, 2 };
        int k = 2;
        int[] ans = maxSumOfThreeSubarrays(nums, k);
        System.out.println(ans[0] + "," + ans[1] + "," + ans[2]);

    }

}
