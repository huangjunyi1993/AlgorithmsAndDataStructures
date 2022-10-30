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
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] arr = new int[digits.length + 1];
        arr[0] = 1;
        return arr;
    }
}
