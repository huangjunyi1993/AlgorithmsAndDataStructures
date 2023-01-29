package _04LeetCode精选TOP面试题.位运算;

/**
 * https://leetcode.cn/problems/number-of-1-bits/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _095位1的个数 {
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int bits = 0;
            int rightOne = 0;
            while (n != 0) {
                bits++; // 位1计数++
                rightOne = (n & (~n + 1)); // 取出最右侧1
                n ^= rightOne; // 抹掉最右侧1
            }
            return bits;
        }
    }
}
