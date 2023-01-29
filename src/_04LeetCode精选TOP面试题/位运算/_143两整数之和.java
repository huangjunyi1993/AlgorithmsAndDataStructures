package _04LeetCode精选TOP面试题.位运算;

/**
 * https://leetcode.cn/problems/sum-of-two-integers/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _143两整数之和 {
    class Solution {
        public int getSum(int a, int b) {
            int sum = a;
            while (b != 0) { // 一直搞，搞到进位信息没了，就停
                sum = a ^ b; // 无进位相加
                b = (a & b) << 1; // 进位信息
                a = sum; // 无进位相加赋给a，让去除进位后的信息和进行信息相加
            }
            return sum;
        }
    }
}
