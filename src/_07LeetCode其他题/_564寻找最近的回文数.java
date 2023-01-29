package _07LeetCode其他题;

/**
 * https://leetcode.cn/problems/find-the-closest-palindrome/
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * “最近的”定义为两个整数差的绝对值最小。
 * Created by huangjunyi on 2023/1/23.
 */
public class _564寻找最近的回文数 {
    class Solution {
        public String nearestPalindromic(String n) {
            /*
            1、先根据num生成一个粗回文，如63279 => 63236
            2、中间位减一，得一个回文 a
            3、中间位加一，的一个回文 b
            a和b比较，谁里num最接近

            难点在于处理进位
             */
            Long num = Long.valueOf(n);
            Long raw = getRawPalindrome(n);
            Long big = raw > num ? raw : getBigPalindrome(raw);
            Long small = raw < num ? raw : getSmallPalindrome(raw);
            return String.valueOf(big - num >= num - small ? small : big);
        }

        public Long getRawPalindrome(String n) {
            char[] chs = n.toCharArray();
            int len = chs.length;
            for (int i = 0; i < len / 2; i++) {
                chs[len - 1 - i] = chs[i];
            }
            return Long.valueOf(String.valueOf(chs));
        }

        public Long getBigPalindrome(Long raw) {
            char[] chs = String.valueOf(raw).toCharArray();
            char[] res = new char[chs.length + 1];
            res[0] = '0';
            for (int i = 0; i < chs.length; i++) {
                res[i + 1] = chs[i];
            }
            int size = chs.length;
            for (int j = (size - 1) / 2 + 1; j >= 0; j--) {
                if (++res[j] > '9') {
                    res[j] = '0';
                } else {
                    break;
                }
            }
            int offset = res[0] == '1' ? 1 : 0;
            size = res.length;
            for (int i = size - 1; i >= (size + offset) / 2; i--) {
                res[i] = res[size - i - offset];
            }
            return Long.valueOf(String.valueOf(res));
        }

        public Long getSmallPalindrome(Long raw) {
            char[] chs = String.valueOf(raw).toCharArray();
            char[] res = new char[chs.length];
            int size = res.length;
            for (int i = 0; i < size; i++) {
                res[i] = chs[i];
            }
            for (int j = (size - 1) / 2; j >= 0; j--) {
                if (--res[j] < '0') {
                    res[j] = '9';
                } else {
                    break;
                }
            }
            if (res[0] == '0') {
                res = new char[size - 1];
                for (int i = 0; i < res.length; i++) {
                    res[i] = '9';
                }
                return size == 1 ? 0 : Long.parseLong(String.valueOf(res));
            }
            for (int k = 0; k < size / 2; k++) {
                res[size - 1 - k] = res[k];
            }
            return Long.valueOf(String.valueOf(res));
        }
    }
}
