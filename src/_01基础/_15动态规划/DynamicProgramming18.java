package _01基础._15动态规划;

/**
 * N皇后问题
 *
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列，也不在同一条斜线上
 * 给定一个正数n，返回n皇后的摆法有多少种。
 * n=1，返回1
 * n=2或3,2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 *
 * 需要用递归，但是不能优化为动态规划的例子
 *
 * Created by huangjunyi on 2022/11/30.
 */
public class DynamicProgramming18 {

    public static int ways(int n) {
        if (n < 1) return 0;
        // record[i]表示在第i行的皇后摆在第几列
        int[] record = new int[n];
        return process(n, record, 0);
    }

    private static int process(int n, int[] record, int i) {
        // 来到第n行，表示全都摆好了，返回一种可行方法数
        if (i == n) return 1;
        int res = 0;
        // 放在第1列，放在第2列...，累加方法数
        for (int j = 0; j < n; j++) {
            // 摆在该列是否违规，违规则跳过，当前行只与往上的行进行比较，不用管下面的行，下面的行交给下层递归
            boolean flag = true;
            for (int k = 0; k < i; k++) {
                if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                    flag = false;
                    break;
                }
            }
            // 记录：第i行的皇后，摆在j列
            if (flag) {
                record[i] = j;
                res += process(n, record, i + 1);
            }
        }
        return res;
    }

}
