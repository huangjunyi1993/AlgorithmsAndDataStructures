package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/plus-one/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _041加一 {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                // 当前位置小于9，直接++返回
                digits[i]++;
                return digits;
            }
            // 当前位置是9，需要进位
            digits[i] = 0;
        }
        // 没有返回，说明，每一位都是9，都要进位，搞一个新数组，最高位填1，返回
        int[] arr = new int[digits.length + 1];
        arr[0] = 1;
        return arr;
    }
}
