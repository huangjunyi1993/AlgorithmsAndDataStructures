package _03经典面试题目.未分类;

/**
 * 求两个数的最大公约数
 * Created by huangjunyi on 2022/10/3.
 */
public class _05GCD {

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(20, 30));
    }

}
