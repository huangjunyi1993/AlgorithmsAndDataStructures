package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/divide-two-integers/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _022两数相除 {
    public int divide(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE) {
            // 除数是系统最小值是，如果被除数也是系统最小值，则结果为1，否则为0，原理类似于 7 / -10 = 0
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        if (dividend == Integer.MIN_VALUE) {
            // 被除数为系统最小值，除数是-1，结果为系统最大值
            if (divisor == negNum(1)) return Integer.MAX_VALUE;
            /*
            * 因div除法运行，是先把两数转为正数，在处理，被除数是系统最小值，转为正数后有问题，
            * 所以被除数先减一，再进行除法运算，然后单独把减掉的部分在做一次除法运行后累加到结果
            * 也就是：
            * a / b = ((a - 1) / b) + (a - ((a - 1) / b) * b) / b
            * 但是因为这里被除数是负数，所以要加1，使它的绝对值变小
            * */
            int res = div(add(dividend, 1), divisor);
            res = add(res, div(minus(dividend, multi(res, divisor)), divisor));
            return res;
        }
        return div(dividend, divisor);
    }

    private int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a ;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        /*
        原理：
        x为被除数，y为除数
        让y不停的右移，直到最接近x但有不超过位置，记录移动的次数i
        然后1左移i位，累加到res中
        然后x减去y左移i位后的数，继续进行第二轮循环
         */
        for (int i = 31; i > negNum(1); i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        // 符号不同，返回为负数，符号相同，返回正数
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    private boolean isNeg(int num) {
        return num < 0;
    }

    /**
     * 两数相加
     * @param a
     * @param b
     * @return
     */
    private int add(int a, int b) {
        int res = a;
        while (b != 0) {
            res = a ^ b; // 两数异或，为无进位相加的信息
            b = (a & b) << 1; // 两数相与左移1位，为进位信息
            a = res; // 两信息继续进行加法运算，直到b为0，代表没有进位信息
        }
        return res;
    }

    /**
     * 两数相减
     * @param a
     * @param b
     * @return
     */
    private int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * 取负数
     * @param num
     * @return
     */
    private int negNum(int num) {
        return add(~num, 1); // 取反加一
    }

    /**
     * 两数相乘
     * @param a
     * @param b
     * @return
     */
    private int multi(int a, int b) {
        int res = 0;
        /*
        比如这两个数相乘：

                1010
                0110
                ————
                0000
               1010
              1010
             0000
             ——————
             0111100
         */
        while (b != 0) {
            // 看此时b的最低位是否为1，是则把当前a的值累计到结果中
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1; // 每次a左移1位
            b >>>= 1; // 每次b右移1位
        }
        return res;
    }

    /**
     * 打印一个数的二进制形式
     * @param num
     * @return
     */
    public static String printNumBinary(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            builder.append(((num >> i) & 1) == 0 ? '0' : '1');
        }
        return builder.toString();
    }
}
