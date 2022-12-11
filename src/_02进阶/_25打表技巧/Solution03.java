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
        // 1 + 2 + 3 + 4 + ...
        // 2 + 3 + 4 + ...
        // 3 + 4 + ....
        // 遍历开头的那个数
        for (int start = 1; start < n; start++) {
            // sum一开始为开头数
            int sum = start;
            // 一直加一个往后的连续数
            for (int num = start + 1; num < n; num++) {
                // 能加到n
                if (sum + num == n) return true;
                // 加超了
                if (sum + num > n) break;
                // 加
                sum += num;
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
        /*
        如果一个数的二进制形式只有一个1，就是2的某次方
        那么与上自己减一后的值，会变成0
        如果与上自己减一后的值，不是0，那么就不是2的某次方

        获取可以提取二进制最右侧的1，看是否和自己相等
        相等的话就是2的某次方
         */
        // if ((n & (-n)) == n) return true;

        if ((n & (n - 1)) != 0) return true;
        return false;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + " " + isMSum1(i) + " " + isMSum2(i));
        }
    }

}
