package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/factorial-trailing-zeroes/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _091阶乘后的零 {
    class Solution {
        public int trailingZeroes(int n) {
            int res = 0;
            while (n != 0) {
                // 没5个数，有一个5的因子，然后没25个数，又有一个5的因子......
                n /= 5;
                res += n;
            }
            return res;
        }
    }
}
