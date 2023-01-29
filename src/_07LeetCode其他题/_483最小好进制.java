package _07LeetCode其他题;

/**
 * https://leetcode.cn/problems/smallest-good-base/
 * 以字符串的形式给出 n , 以字符串的形式返回 n 的最小 好进制  。
 * 如果 n 的  k(k>=2) 进制数的所有数位全为1，则称 k(k>=2) 是 n 的一个 好进制 。
 * Created by huangjunyi on 2023/1/22.
 */
public class _483最小好进制 {
    class Solution {
        public String smallestGoodBase(String n) {
            long num = Long.valueOf(n);
            // 2进制下num是几位，假设是m
            // m开始试到2（不用到2），位数越多，进制越小
            // Math.log(num + 1) / Math.log(2) 是一个公式
            // 如果2进制不是num的好进制，那么就从m-1开始试
            for (int m = (int) (Math.log(num + 1) / Math.log(2)); m > 2; m--) {
                // 当m位是，k进制的k的下界和上界
                long l = (long) (Math.pow(num, 1.0 / m));
                long r = (long) (Math.pow(num, 1.0 / (m - 1))) + 1L;
                // 二分找当前位数最好的k进制
                while (l <= r) {
                    long k = l + ((r - l) >> 1);
                    // k进制，所有位都是1，求出sum
                    long sum = 0L;
                    long base = 1L;
                    for (int i = 0; i < m && sum <= num; i++) {
                        sum += base;
                        base *= k;
                    }
                    if (sum < num) {
                        // sum小于num，表示k进制的k定小了
                        l = k + 1;
                    } else if (sum > num) {
                        // sum大于num，表示k进制的k定大了
                        r = k - 1;
                    } else {
                        // 否则就是命中了答案，返回
                        return String.valueOf(k);
                    }
                }
            }
            // 2位，就一定是x-1进制
            return String.valueOf(num - 1);
        }
    }
}
