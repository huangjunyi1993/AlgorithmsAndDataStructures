package _04LeetCode精选TOP面试题.哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/fraction-to-recurring-decimal/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _088分数到小数 {
    class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) return "0";
            StringBuilder res = new StringBuilder();
            res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
            // 防止溢出
            long num = Math.abs((long) numerator);
            long den = Math.abs((long) denominator);
            res.append(num / den);
            num = num % den;
            // 没有余数，被整除了，返回
            if (num == 0) return res.toString();
            res.append(".");
            // 记录余数出现的位置，出现重复余数，就知道左括号插在哪了
            Map<Long, Integer> map = new HashMap<>();
            map.put(num, res.length());
            while (num != 0) {
                // 计算小数点后的数字，
                // num * 10 / den，
                // 得出的数，放到小数点后
                // 然后num * 10模 den，
                // 得到的数，循环
                num *= 10;
                res.append(num / den);
                num = num % den;
                if (map.containsKey(num)) {
                    // 出现了小数点后出现过的，取出之前出现过的位置，插入括号
                    res.insert(map.get(num), "(");
                    res.append(")");
                    break;
                } else {
                    map.put(num, res.length());
                }
            }
            return res.toString();
        }
    }
}
