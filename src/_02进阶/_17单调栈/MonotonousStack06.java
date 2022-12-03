package _02进阶._17单调栈;

/**
 * 子数组的最小值之和
 *
 * https://leetcode.cn/problems/sum-of-subarray-minimums/description/
 *
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 * Created by huangjunyi on 2022/12/2.
 */
public class MonotonousStack06 {
    class Solution {
        public int sumSubarrayMins(int[] arr) {
            // 记录左边最近的比当前数小的位置
            int[] left = new int[arr.length];
            // 记录右边最近的小于等于当前数的位置
            int[] right = new int[arr.length];
            // 通过单调栈的解法，填充两个数组
            initLeftAndRight(arr, left, right);
            long res = 0;
            // 填充好了这两个辅助数组，遍历arr数组每个数，求 => 假设以arr[i]作为最小值的数组,有多少个
            // 通过计算公式求解
            // 比如：
            // left[i] = 3，表示左边到不了的位置是3
            // right[i] = 9，表示右边到不了的位置是9
            // arr[i] = 8，假设以8作为最小值
            // 假设 i = 6
            // 子数组有：[4-6] [4-7] [4-8] [5-6] [5-7] [5-8] [6-6] [6-7] [6-8]，9个，然后子数组最小值为8，所以 => 9 * 8
            // 所以公式是：左边开头的个数（4,5,6） * 结尾的个数（6,7,8） * 子数组最小值
            for (int i = 0; i < arr.length; i++) {
                // 防溢出，右边其中一个数强转为long，则右边就是long类型的乘法运算，否则就是int类型的乘法运算
                res += (long) (i - left[i]) * (right[i] - i) * arr[i];
                // 结果也要防溢出
                res %= 1000000007;
            }
            return (int) res;
        }

        private void initLeftAndRight(int[] arr, int[] left, int[] right) {
            // 单调栈，数组实现，单调递增
            int[] stack = new int[arr.length];
            int index = -1;
            // 遍历arr数组，入栈，遇到栈顶比入栈数打的，弹出栈顶，结算它左边界和右边界
            for (int i = 0; i < arr.length; i++) {
                while (index != - 1 && arr[stack[index]] >= arr[i]) {
                    int cur = stack[index--];
                    // 左边界，左边离它最近又比它小的
                    left[cur] = index == -1 ? -1 : stack[index];
                    // 右边界，右边离它最近又比它小的
                    right[cur] = i;
                }
                stack[++index] = i;
            }
            // 结算栈中剩余元素
            while (index != - 1) {
                int cur = stack[index--];
                left[cur] = index == -1 ? -1 : stack[index];
                right[cur] = arr.length;
            }
        }
    }
}
