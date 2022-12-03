package _01基础._15动态规划;

/**
 * 给定三个参数，N、M、K
 * 怪兽有N滴血，等着英雄来看自己
 * 英雄每一次打击，都会让怪兽流失[0-M]的血量
 * 到底流失多少？每一次在[0-M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 * Created by huangjunyi on 2022/11/28.
 */
public class DynamicProgramming13 {

    /**
     * 暴力递归
     * @param N 怪兽有N滴血
     * @param M 怪兽流失血量范围
     * @param K 英雄打击K次
     * @return
     */
    public static double killMonster01(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) return 0;
        // 所有情况数
        long all = (long) Math.pow(M + 1, K);
        // 砍死怪兽的情况数
        long kill = process01(N, M, K);
        return ((double) kill) / ((double) all);
    }

    /**
     * 暴力递归
     * @param hp 怪兽剩余血量
     * @param M 怪兽流失血量范围
     * @param rest 英雄剩K次要砍
     * @return
     */
    private static long process01(int hp, int M, int rest) {
        // base case：砍完了，怪兽血量减到0以下，返回1中有效方法数
        if (rest == 0) return hp <= 0 ? 1 : 0;
        // 怪兽已经死了，也要继续看，因为往后的都是算入有效方法数的
        if (hp <= 0) return (long) Math.pow(M + 1, rest);
        // 枚举砍出的伤害值，累加方法数
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process01(hp - i, M, rest - 1);
        }
        return ways;
    }

    /**
     * 动态规划
     * @param N 怪兽有N滴血
     * @param M 怪兽流失血量范围
     * @param K 英雄打击K次
     * @return
     */
    public static double killMonster02(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) return 0;
        // 所有情况数
        long all = (long) Math.pow(M + 1, K);
        /*
        暴力递归两个可变参数，剩余要砍次数、怪兽剩余血量，所以是一张二维dp表
        dp[rest][hp]
        英雄剩余要看rest次，怪兽剩余血量hp，砍死怪兽的方法数
        观察暴力递归的参数范围，可得dp表长度
         */
        long[][] dp = new long[K + 1][N + 1];
        /*
        根据base case，初始化dp表：
        if (rest == 0) return hp <= 0 ? 1 : 0;
        if (hp <= 0) return (long) Math.pow(M + 1, rest);
         */
        dp[0][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[rest][0] =  (long) Math.pow(M + 1, rest);
        }
        /*
         观察暴力递归中，外层递归与里层递归的依赖关系
         确定dp表的填表顺序
         long ways = 0;
         for (int i = 0; i < M; i++) {
             ways += process01(hp - i, M, rest - 1);
         }
         dp[rest][...] 依赖 dp[rest - 1][...]
         所以是从上往下填
          */
        for (int rest = 1; rest <= K; rest++) {
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    // 这里要注意hp减完有可能越界，越界了就通过公式算
                    ways += hp - i >= 0 ? dp[rest - 1][hp - i] : (long) Math.pow(M + 1, rest - 1);
                }
                dp[rest][hp] = ways;
            }
        }
        /*
        根据暴力递归的最外层递归，确定返回值
        long kill = process01(N, M, K);
        return ((double) kill) / ((double) all);
         */
        return ((double) dp[K][N]) / ((double) all);
    }

    /**
     * 动态规划 枚举行为优化
     * @param N 怪兽有N滴血
     * @param M 怪兽流失血量范围
     * @param K 英雄打击K次
     * @return
     */
    public static double killMonster03(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) return 0;
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[rest][0] =  (long) Math.pow(M + 1, rest);
        }
        for (int rest = 1; rest <= K; rest++) {
            for (int hp = 1; hp <= N; hp++) {
//                long ways = 0;
//                for (int i = 0; i < M; i++) {
//                    // 这里要注意hp减完有可能越界，越界了就通过公式算
//                    ways += hp - i >= 0 ? dp[hp - i][rest - 1] : (long) Math.pow(M + 1, rest - 1);
//                }
//                dp[rest][hp] = ways;

                /*
                有枚举行为，就随便算一个格子看依赖关系就行了
                观察dp[5][10]，M=5，英雄剩5次要砍，怪兽剩10点血
                dp[5][10] = dp[4][5...10]
                dp[5][11] = dp[4][6...11]
                所以：
                dp[5][11] = dp[4][11] + dp[5][10] - dp[4][5]
                推断出：
                dp[rest][hp] = dp[rest - 1][hp] + dp[rest][hp - 1] - dp[rest - 1][hp - M - 1]
                但是那是hp - M - 1不越界的情况，如果越界：
                dp[rest][hp] = dp[rest - 1][hp] + dp[rest][hp - 1] - (long) Math.pow(M + 1, rest - 1)
                 */
                dp[rest][hp] = dp[rest][hp - 1] + dp[rest - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[rest][hp] -= dp[rest - 1][hp - 1 - M];
                } else {
                    dp[rest][hp] -= Math.pow(M + 1, rest - 1);
                }
            }
        }
        return ((double) dp[K][N]) / ((double) all);
    }

    public static void main(String[] args) {
        System.out.println("begin");
        for (int i = 0; i < 20000; i++) {
            int N = (int) (Math.random() * 10);
            int M = (int) (Math.random() * 10);
            int K = (int) (Math.random() * 10);
            double res1 = killMonster01(N, M, K);
            double res2 = killMonster02(N, M, K);
            double res3 = killMonster03(N, M, K);
            if (res1 != res2 || res1 != res3) {
                System.out.println("error: " + " N=" + N + " M=" + M + " K=" + K);
                System.out.println("res1=" + res1 + " res2=" + res2 + " res3=" + res3);
                break;
            }
        }
        System.out.println("finished");
    }

}
