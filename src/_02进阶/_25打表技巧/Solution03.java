package _02进阶._25打表技巧;

/**
 * 定义一种数：可以表示成若干（数量>1）连续正数和的数 。
 * 比如:5 = 2+3，5就是这样的数 ；12 = 3+4+5，12就是这样的数 。
 * 1不是这样的数，因为要求数量大于1个、连续正数和 。
 * 2 = 1 + 1，2也不是，因为等号右边不是连续正数 。
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数 。
 * Created by huangjunyi on 2022/9/11.
 */
public class Solution03 {

    /**
     * 暴力解找规律
     * @param n
     * @return
     */
    public static boolean isMSum1(int n) {
        for (int i = 1; i < n; i++) {
            int sum = i;
            for (int j = i + 1; j < n; j++) {
                if (sum + j == n) return true;
                if (sum + j > n) break;
                sum += j;
            }
        }
        return false;
    }

    /**
     1 false
     2 false
     3 true
     4 false
     ...
     8 false
     ...
     16 false
     ...
     32 false
     ...
     64 false
     * @param n
     * @return
     */
    public static boolean isMSum2(int n) {
        if (n < 3) return false;
        if ((n & (n - 1)) != 0) return true;
        return false;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + " " + isMSum1(i) + " " + isMSum2(i));
        }
    }

}
