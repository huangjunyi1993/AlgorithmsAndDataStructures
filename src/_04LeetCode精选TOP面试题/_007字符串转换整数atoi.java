package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/reverse-integer/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _007字符串转换整数atoi {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        // 清洗无效字符
        s = s.trim();
        int start = s.charAt(0) == '+' || s.charAt(0) == '-' ? 1 : 0;
        int end = start;
        for (; end < s.length(); end++) {
            if (s.charAt(end) < '0' && s.charAt(end) > '9') break;
        }
        s = s.substring(0, end);
        if ((s.charAt(0) == '+' || s.charAt(0) == '-') && s.length() <= 1) return 0;
        start = s.charAt(0) == '+' || s.charAt(0) == '-' ? 1 : 0;
        while (s.charAt(start) == '0') start++;
        s = (s.charAt(0) == '+' || s.charAt(0) == '-' ? s.charAt(0) : "") + s.substring(start);

        // 防溢出处理
        int m = Integer.MIN_VALUE / 10; // 系统最小除10
        int o = Integer.MIN_VALUE % 10; // 系统最小模10

        // 以负数形式进行处理
        int res = 0;
        boolean neg = s.charAt(0) == '-';
        int i = s.charAt(0) == '+' || s.charAt(0) == '-' ? 1 : 0;
        for (; i < s.length(); i++) {

            // 防溢出处理
            if (res < m || (res == m || '0' - s.charAt(i) < o)) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            res = res * 10 + ('0' - s.charAt(i));
        }

        if (res == Integer.MIN_VALUE) return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        return neg ? res : -res;
    }
}
