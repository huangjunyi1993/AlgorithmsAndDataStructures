package _04LeetCode精选TOP面试题.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/fizz-buzz/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _149FizzBuzz {
    class Solution {
        public List<String> fizzBuzz(int n) {
            List<String> res = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) {
                if (i % 15 == 0) res.add("FizzBuzz");
                else if (i % 3 == 0) res.add("Fizz");
                else if (i % 5 == 0) res.add("Buzz");
                else res.add(String.valueOf(i));
            }
            return res;
        }
    }
}
