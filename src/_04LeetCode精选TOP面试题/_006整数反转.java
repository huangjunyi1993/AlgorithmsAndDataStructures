package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/reverse-integer/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _006整数反转 {

    public int reverse(int x) {

        // 同一转成负数处理，因int的系统最小值比系统最大值多1
        boolean neg = ((x >>> 31) & 1) == 1;
        x = neg ? x : -x;

        // m和o用于处理溢出
        int m = Integer.MIN_VALUE / 10;
        int o = Integer.MIN_VALUE % 10;
        int res = 0;
        while (x != 0) {
            if (res < m || (res == m && x % 10 < o)) return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return neg ? res : Math.abs(res);
    }

}
