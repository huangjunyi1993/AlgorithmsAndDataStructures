package _03经典面试题目.动态规划;

/**
 * 给定一个正数1，裂开的方法有一种，(1)
 * 给定一个正数2，裂开的方法有两种，(1和1)、(2)
 * 给定一个正数3，裂开的方法有三种，(1、1、1)、(1、2)、(3)
 * 给定一个正数4，裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、(2、2)、 (4)
 * 给定一个正数n，求裂开的方法数。 动态规划优化状态依赖的技巧
 * Created by huangjunyi on 2022/10/8.
 */
public class _32SplitNumber {

    /**
     * 递归解法
     * @param n
     * @return
     */
    public static int splitWay1(int n) {
        if (n < 1) return 0;
        return process(1, n);
    }

    /**
     * 递归求解裂开的方法数
     * @param pre 第一个数不能小于pre
     * @param rest 要裂开的数
     * @return
     */
    private static int process(int pre, int rest) {
        if (rest == 0) return 1; // 没有数要裂开了，返回1，代表1中方法
        if (pre > rest) return 0; // pre比rest大，没办法裂开，返回0
        int ways = 0;
        /*
        枚举所有可能性
        例如当前pre为3，rest为6
        pre为3，代表前一个数是3，那么这里就不能比3小，因为会违规
        rest为6，代表现在要把6裂开
        所以就枚举
        3,3 裂开一个3，剩下的3交给后续的递归裂开
        4,2 裂开一个4，剩下的2交给后续的递归裂开
        5,1 。。。
        6,0 。。。
         */
        for (int i = pre; i <= rest; i++) ways += process(i, rest - i);
        return ways;
    }

    /**
     * 根据splitWay1方法的递归改成动态规划版本
     * @param n
     * @return
     */
    public static int splitWay2(int n) {
        if (n < 1) return 0;
        /*
        第一行弃而不用
        pre从1~n
        rest最大也是到n
        所以申请 (n + 1) * (n + 1) 大小的dp表
         */
        int[][] dp = new int[n + 1][n + 1];
        // rest为0的都是1，代表没有数要裂开，1中方法数
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int pre = n; pre >= 1; pre--) {
            for (int rest = pre; rest <= n; rest++) {
                int ways = 0;
                for (int i = pre; i <= rest; i++) ways += dp[i][rest - i];
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    /**
     * 堆splitWay2的动态规划进行枚举行为的优化
     * (3, 6) 的结果，依赖(3,3),(4,2),(5,3),(6,0)
     * (4, 6) 的结果，依赖      (4,2),(5,3),(6,0)
     * 随意可以把(4,2),(5,3),(6,0)的枚举行为优化掉
     *
     * @param n
     * @return
     */
    public static int splitWay3(int n) {
        if (n < 1) return 0;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        dp[n][n] = 1;
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(splitWay1(n));
        System.out.println(splitWay2(n));
        System.out.println(splitWay3(n));
    }

}
