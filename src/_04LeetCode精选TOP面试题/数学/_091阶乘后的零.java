package _04LeetCode精选TOP面试题.数学;

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
                // 能乘出来几个10，就有几个零，2的因子比5的因子多，所以其实是求5的因子
                // 每5个数，有一个5的因子，然后每25个数，又有一个5的因子......
                // n / 5 + n / 25 + n / 125 + ...... (n == 0 停)
                n /= 5;
                res += n;
            }
            return res;
        }
    }
}
