package _01基础._02位运算;

/**
 * 不使用额外变量，进行两个数的交换
 */
public class BitOperation01 {

    public static void main(String[] args) {
        int a = 2;
        int b = 3;

        a = a ^ b; // a = a ^ b
        b = a ^ b; // b = a ^ b ^ b = a
        a = a ^ b; // a = a ^ b ^ a = b

        System.out.println(a);
        System.out.println(b);
    }

}
