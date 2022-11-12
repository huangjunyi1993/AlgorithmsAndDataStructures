package _04LeetCode精选TOP面试题;

import java.util.HashSet;

/**
 * https://leetcode.cn/problems/happy-number/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _098快乐数 {
    class Solution {
        public boolean isHappy(int n) {
            HashSet<Integer> set = new HashSet<>();
            while (n != 1) {
                int sum = 0;
                int num = n;
                while (num != 0) {
                    int mod = num % 10;
                    sum += (mod * mod);
                    num = num / 10;
                }
                // set中已有，表示死循环了，退出
                if (set.contains(sum)) break;
                set.add(sum);
                n = sum;
            }
            // 如果不是因为死循环退出的，那么n就是1
            return n == 1;
        }
    }
}
