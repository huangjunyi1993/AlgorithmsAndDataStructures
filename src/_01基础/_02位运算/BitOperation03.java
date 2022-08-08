package _01基础._02位运算;

/**
 * 一个数组中，两种数出现了奇数次，其余数出现偶数次，找到并打印这两种数
 */
public class BitOperation03 {

    public static void main(String[] args) {
        int[] arr = {1,4,6,1,6,8,9,9}; //4和8出现奇数次，现在要找出4和8

        int eor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eor ^= arr[i];
        }

        //此时eor为 4 ^ 8
        //两个不等的数异或运算，得出的结果必然不等于0，取出eor最右侧的1
        eor = eor & (~eor + 1);

        //此时可以遍历数组，把数组分两组：1、和eor最右侧1相同二进制位上为1的；2、和eor最右侧1相同二进制位上为0的
        //此时出现奇数次的两个数，必然落在不同组，分别对这两组数进行异或运算，得出两个出现奇数次的数
        int a = 0;
        int b = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & eor) == 0) {
                a ^= arr[i];
            } else {
                b ^= arr[i];
            }
        }

        System.out.println(a);
        System.out.println(b);
    }

}
