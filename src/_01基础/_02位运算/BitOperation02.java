package _01基础._02位运算;

/**
 * 一个数组中，一种数出现了奇数次，其余数出现偶数次，找到并打印这种数
 */
public class BitOperation02 {

    public static void main(String[] args) {
        int[] arr = {1,4,6,1,6,8,4,9,9};

        int eor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eor ^= arr[i];
        }

        System.out.println(eor);
    }

}
