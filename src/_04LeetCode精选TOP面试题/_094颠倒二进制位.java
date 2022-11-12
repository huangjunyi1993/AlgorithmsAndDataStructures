package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/reverse-bits/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _094颠倒二进制位 {
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            // 前16 后16 反转
            n = (n >>> 16) | (n << 16);
            // 前16位中的左右8位反转，后16位的左右前8位反转 一个f等于1111 ff就是11111111
            n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
            // 每8位中的前后4位反转 一个f等于1111
            n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
            // 每4位中的前后2位反转 16进制c=1100, 3=0011
            n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
            // 每两位中的前后1位反转 16进制a=1010, 5=0101
            n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
            return n;
        }
    }
}
