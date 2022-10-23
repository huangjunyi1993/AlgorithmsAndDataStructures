package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/integer-to-roman/
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _010整数转罗马数字 {
    public String intToRoman(int num) {
        String[][] c = {
                { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
                { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
                { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
                { "", "M", "MM", "MMM" }
        };
        return new StringBuilder()
                .append(c[3][(num / 1000) % 10])
                .append(c[2][(num / 100) % 10])
                .append(c[1][(num / 10) % 10])
                .append(c[0][num % 10])
                .toString();
    }
}
