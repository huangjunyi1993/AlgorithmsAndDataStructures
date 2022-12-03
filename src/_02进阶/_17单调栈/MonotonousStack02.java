package _02进阶._17单调栈;

import java.util.LinkedList;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出（sub累加和）*（sub中的最小值）是什么，
 * 那么所有子数组中，这个值最大是什么
 *
 * Created by huangjunyi on 2022/12/1.
 */
public class MonotonousStack02 {

    public static int max(int[] arr) {
        int N = arr.length;

        // 前缀和数组，用于单调栈弹出时辅助求答案
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        // 单调栈，单调递增，以数组每个数作为子数组中的最小值，求一个可扩到的最大范围的答案
        LinkedList<Integer> stack = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            // 要入栈的数小于等于栈顶，则要弹出栈顶，栈顶弹出时，通过前缀和数组结算一个答案
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int pop = stack.pop();
                // 假设弹出的时下标a，它在栈中压着b，则arr[b] < arr[a]
                // 那么以下标a作为最小值的子数组，往左括扩不到b
                // 要入栈的下标c，也比它小，arr[a] < arr[c]
                // 那么以下标a作为最小值的子数组，往右括扩不到b
                // 则通过前缀和数组 sum[c - 1] - sum[b]，就可以得到这个子数组的累加和，
                // 然后乘以arr[a]，就是以arr[a]作为子数组的最小值，扩到最大范围时，求得的答案
                // 然后和max pk一下
                max = Math.max(max, arr[pop] * (stack.isEmpty() ? sum[i - 1] : (sum[i - 1] - sum[stack.peek()])));
            }
            stack.push(i);
        }
        // 结算还留在栈中没弹出的
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            max = Math.max(max, arr[pop] * (stack.isEmpty() ? sum[N - 1] : (sum[N - 1] - sum[stack.peek()])));
        }
        return max;
    }

}
