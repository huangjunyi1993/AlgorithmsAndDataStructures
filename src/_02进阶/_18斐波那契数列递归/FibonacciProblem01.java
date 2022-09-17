package _02进阶._18斐波那契数列递归;

/**
 * 斐波那契第N项，矩阵求法
 * Created by huangjunyi on 2022/9/4.
 */
public class FibonacciProblem01 {

    public static int getFibonacciItemByMatrix(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;
        /*
        斐波那契数列是一个递推式：
        fn = f(n-1) + f(n-2)
        满足一个特点：
        |f3,f2| = |f2,f1| * ||a,b|,|c,d||
        |f4,f3| = |f3,f2| * ||a,b|,|c,d||
        按照这个公式可以求得||a,b|,|c,d|| = ||1,1|,|1,0||
        设矩阵||1,1|,|1,0||为base
        又有：
        |f4,f2| = |f3,f2| * base = |f2,f1| * base * base = |f2,f1| * base^2
        所以：
        |fn,f(n-1)| = |f2,f1| * base^(n-2) = |1,1| * base^(n-2)
        假设base^(n-2)求出的时||q,w|,|e,r||
        则fn = 1 * q + 1 * e = q + e
         */
        int[][] base = {{1, 1},{1,0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    /**
     * 求base矩阵的p次方
     * @param base
     * @param p
     * @return
     */
    private static int[][] matrixPower(int[][] base, int p) {
        int[][] res = new int[base.length][base[0].length];
        //初始化单位矩阵，左上到右下的对角线全为1
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }
        /*
        快速的求n次方的方法：
        假设求10^75
        75的二进制1001011
        1  0  0  1 0 1 1
        64 32 16 8 4 2 1
        10^75 等价于：
        10^64 * 10^8 * 10^2 * 10^1
        所以base^75是同样的求解法方法：
        base^64 * base^8 * base^2 * base^1
         */
        int[][] temp = base;
        for (int i = p; i > 0; i >>= 1) {
            if ((i & 1) == 1) {
                res = MatrixMulti(res, temp);
            }
            temp = MatrixMulti(temp, temp);
        }

        return res;
    }

    /**
     * 两个矩阵相乘
     * @param m1
     * @param m2
     * @return
     */
    public static int[][] MatrixMulti(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getFibonacciItemByMatrix(19));
    }
}
