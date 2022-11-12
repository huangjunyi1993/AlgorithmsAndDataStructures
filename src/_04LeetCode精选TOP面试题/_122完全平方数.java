package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/perfect-squares/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _122完全平方数 {
    /**
     * 第一步：暴力解
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int res = n;
        int num = 2;
        while (num * num <= n) {
            int a = n / (num * num);
            int b = n % (num * num);
            int c = numSquares(b);
            res = Math.min(res, a + c);
            num++;
        }
        return res;
    }

    /**
     * 第二步，打表找规律
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(i + " : " + numSquares(i));
        }
    }

    /*
    第三步：观察规律
    1 : 1
    2 : 2
    3 : 3
    4 : 1
    5 : 2
    6 : 3
    7 : 4
    8 : 2
    9 : 1
    10 : 2
    11 : 3
    12 : 3
    13 : 2
    14 : 3
    15 : 4
    16 : 1
    17 : 2
    18 : 2
    19 : 3
    20 : 2
    21 : 3
    22 : 3
    23 : 4
    24 : 3
    25 : 1
    26 : 2
    27 : 3
    28 : 4
    29 : 2
    30 : 3
    31 : 4
    32 : 2
    33 : 3
    34 : 2
    35 : 3
    36 : 1
    37 : 2
    38 : 3
    39 : 4
    40 : 2
    41 : 2
    42 : 3
    43 : 3
    44 : 3
    45 : 2
    46 : 3
    47 : 4
    48 : 3
    49 : 1
    50 : 2
    51 : 3
    52 : 2
    53 : 2
    54 : 3
    55 : 4
    56 : 3
    57 : 3
    58 : 2
    59 : 3
    60 : 4
    61 : 2
    62 : 3
    63 : 4
    64 : 1
    65 : 2
    66 : 3
    67 : 3
    68 : 2
    69 : 3
    70 : 3
    71 : 4
    72 : 2
    73 : 2
    74 : 2
    75 : 3
    76 : 3
    77 : 3
    78 : 3
    79 : 4
    80 : 2
    81 : 1
    82 : 2
    83 : 3
    84 : 3
    85 : 2
    86 : 3
    87 : 4
    88 : 3
    89 : 2
    90 : 2
    91 : 3
    92 : 4
    93 : 3
    94 : 3
    95 : 4
    96 : 3
    97 : 2
    98 : 2
    99 : 3
    100 : 1
    101 : 2
    102 : 3
    103 : 4

    规律一：个数不超过4
    规律二：出现1个的时候，显而易见
    规律三：任何数 % 8 == 7，一定是4个
    规律四：任何数消去4的因子之后，剩下rest，rest % 8 == 7，一定是4个

     */




    class Solution {
        public int numSquares(int n) {
            int rest = n;
            while (rest % 4 == 0) {
                rest /= 4;
            }
            if (rest % 8 == 7) {
                return 4;
            }
            int num = (int) Math.sqrt(n);
            if (num * num == n) {
                return 1;
            }
            for (int firstNum = 1; firstNum * firstNum <= n; firstNum++) {
                int secondNum = (int) Math.sqrt(n - (firstNum * firstNum));
                if (secondNum * secondNum + firstNum * firstNum == n) {
                    return 2;
                }
            }
            return 3;
        }
    }
}
