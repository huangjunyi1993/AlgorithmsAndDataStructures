package _04LeetCode精选TOP面试题.数学;

/**
 * https://leetcode.cn/problems/power-of-three/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/9.
 */
public class _133_3的幂 {
    class Solution {
        public boolean isPowerOfThree(int n) {
            // 1162261467 整形范围内最大的3次幂，如果n也是3的幂，那么模n一定为0
            return n > 0 && 1162261467 % n == 0;
        }
    }
}
