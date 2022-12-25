package _03经典面试题目.位运算;

/**
 * 给定一个非负整数num，
 * 如何不用循环语句，
 * 返回>=num，并且里num最近的，2的某次方
 * Created by huangjunyi on 2022/12/18.
 */
public class _02Near2Power {
    public static final int tableSizeFor(int n) {

        // n-- 是兼顾n正好是2都某次方
        n--;

        /*
        下面这一坨
        会把后面的二进制位都填满
        例如
        000011011001 =>
        000011111111
         */
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        // 填满加一，就是大于等于它又离它最近的2的某次方
        return n < 0 ? 1 : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(64));
    }
}
