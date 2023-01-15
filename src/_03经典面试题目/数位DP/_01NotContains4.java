package _03经典面试题目.数位DP;

/**
 * 正常的里程表会依次显示自然数表示里层
 * 吉祥的里程表会忽略含有4的数字而跳到下一个完全不含有4的数
 * 正常：1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 * 吉祥：1 2 3 5 6 7 8 9 10 11 12 13 15 16 17 ... 35 39 50 51 52 53 55
 * 给定一个吉祥里程表的数字num（当然这个数字中不含有4）
 * 返回这个数字代表的真实里程
 *
 * Created by huangjunyi on 2023/1/10.
 */
public class _01NotContains4 {

    // arr[i] i-1位数里有多少个吉祥里程数
    public static long[] arr = { 0L, 1L, 9L, 81L, 729L, 6561L, 59049L, 531441L, 4782969L, 43046721L, 387420489L,
            3486784401L, 31381059609L, 282429536481L, 2541865828329L, 22876792454961L, 205891132094649L,
            1853020188851841L, 16677181699666569L, 150094635296999121L, 1350851717672992089L };

    public static long notContains4Nums(long num) {
        if (num <= 0) return 0;
        // num的十进制位数
        int len = getNumLen(num);
        // 比如len=5，返回100000
        long numByLen = getNumByLen(len);
        long firstNum = (int) (num / numByLen);
        // 比如求吉祥里程数65535是正常数的第几位
        // 先求1~4位数，有多少个吉祥里程数，然后求5位数时最高位为不为6有多少个吉祥里程数，最后求最高位为6有多少吉祥里程数
        return arr[len] - 1 // 1~4位数，有多少个吉祥里程数，因为吉祥里程数不含0，所以减1
                + arr[len] * (firstNum - (firstNum > 4 ? 2 : 1)) // 5位数时最高位为不为6有多少个吉祥里程数
                + process(num % numByLen, numByLen / 10, len - 1); // 最高位为6有多少吉祥里程数
    }

    private static long process(long num, long numByLen, int len) {
        if (len == 0) return 1;
        long firstNum = num / numByLen;
        // 比如求吉祥里程数65535是正常数的第几位
        // 然后它调了process，那么进来的就是5535
        // 然后这里就这样：
        return arr[len] * (firstNum - (firstNum > 4 ? 1 : 0)) // 5535中，最高位不是5，有多少吉祥里程数
                + process(num % numByLen, numByLen / 10, len - 1); // 最高位为5，有多少吉祥里程数，也就是535进去
    }

    /**
     * 比如len=5，返回100000
     * @param len
     * @return
     */
    private static long getNumByLen(int len) {
        long num = 1;
        for (int i = 1; i < len; i++) {
            num *= 10;
        }
        return num;
    }


    /**
     * num的十进制位数
     * @param num
     * @return
     */
    private static int getNumLen(long num) {
        int len = 0;
        while (num != 0) {
            num /= 10;
            len++;
        }
        return len;
    }

    // 暴力解
    public static long notContains4Nums1(long num) {
        long count = 0;
        for (long i = 1; i <= num; i++) {
            if (isNot4(i)) {
                count++;
            }
        }
        return count;
    }

    public static boolean isNot4(long num) {
        while (num != 0) {
            if (num % 10 == 4) {
                return false;
            }
            num /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        long max = 9999L;
        System.out.println("功能测试开始，验证 0 ~ " + max + " 以内所有的结果");
        for (long i = 0; i <= max; i++) {
            // 测试的时候，输入的数字i里不能含有4，这是题目的规定！
            if (isNot4(i) && notContains4Nums(i) != notContains4Nums1(i)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("如果没有打印Oops说明验证通过");
    }


}
