package _03经典面试题目.动态规划;

import java.text.DecimalFormat;

/**
 * 谷歌面试题
 * 面值为1~10的牌组成一组，
 * 每次你从组里等概率的抽出1~10中的一张
 * 下次抽会换一个新的组，有无限组
 * 当累加和<17时，你将一直抽牌
 * 当累加和>=17且<21时，你将获胜
 * 当累加和>=21时，你将失败
 * 返回获胜的概率
 *
 * 谷歌面试题扩展版
 * 面值为1~N的牌组成一组，
 * 每次你从组里等概率的抽出1~N中的一张
 * 下次抽会换一个新的组，有无限组
 * 当累加和<a时，你将一直抽牌
 * 当累加和>=a且<b时，你将获胜
 * 当累加和>=b时，你将失败
 * 返回获胜的概率，给定的参数为N，a，b
 *
 * Created by huangjunyi on 2023/1/1.
 */
public class _047NCardsABWin {

    /**
     * 谷歌面试题
     * 面值为1~10的牌组成一组，
     * 每次你从组里等概率的抽出1~10中的一张
     * 下次抽会换一个新的组，有无限组
     * 当累加和<17时，你将一直抽牌
     * 当累加和>=17且<21时，你将获胜
     * 当累加和>=21时，你将失败
     * 返回获胜的概率
     */
    public static double f1() {
        return p1(0);
    }

    /**
     * 现在累加到cur，再来抽一组牌，返回这一轮的获胜概率
     * @param cur 当前累加
     * @return
     */
    private static double p1(int cur) {
        // >=17且<21，获胜
        if (cur >= 17 && cur < 21) return 1.0;
        // >=21，失败
        if (cur >= 21) return 0.0;
        // 再来抽一组牌，累加的胜率除以10，就是当前这一轮的胜率
        double res = 0.0;
        for (int i = 1; i <= 10; i++) {
            res += p1(cur + i);
        }
        return res / 10;
    }


    /**
     * 谷歌面试题扩展版
     * 面值为1~N的牌组成一组，
     * 每次你从组里等概率的抽出1~N中的一张
     * 下次抽会换一个新的组，有无限组
     * 当累加和<a时，你将一直抽牌
     * 当累加和>=a且<b时，你将获胜
     * 当累加和>=b时，你将失败
     * 返回获胜的概率，给定的参数为N，a，b
     */
    public static double f2(int N, int a, int b) {
        if (N <= 0 || a >= b || a < 0 || b < 0) return 0.0;
        if (b - a >= N) return 1.0;
        return p2(N, a, b, 0);
    }

    private static double p2(int N, int a, int b, int cur) {
        // 累加和>=b，失败
        if (cur >= b) return 0.0;
        // 累加和>=a且<b，获胜
        if (cur >= a && cur < b) return 1.0;
        // 还不能决定，再试一轮看看
        double res = 0.0;
        for (int i = 1; i <= N; i++) {
            res += p2(N, a, b, cur + i);
        }
        // 累加的胜率，除以N
        return res / N;
    }

    /**
     * 谷歌面试题扩展版 斜率优化版本
     */
    public static double f3(int N, int a, int b) {
        if (N <= 0 || a >= b || a < 0 || b < 0) return 0.0;
        if (b - a >= N) return 1.0;
        return p3(N, a, b, 0);
    }

    private static double p3(int N, int a, int b, int cur) {
        if (cur >= b) return 0.0;
        if (cur >= a && cur < b) return 1.0;
        /*
        公式：

        当 cur == a - 1
        res = （b - a) / N

        当 cur + N + 1 >= b
        res = (p3(cur + 1) + p3(cur + 1) * N) / N

        否则
        res = (p3(cur + 1) + p3(cur + 1) * N - p3(cur + N + 1)) / N

         */
        double res = 0.0;
        if (cur == a - 1) res = 1.0 * (b - a);
        else if (cur + N + 1 >= b) res = p3(N, a, b, cur + 1) + p3(N, a, b, cur + 1) * N;
        else res = p3(N, a, b, cur + 1) + p3(N, a, b, cur + 1) * N - p3(N, a, b, cur + N + 1);
        return res / N;
    }

    /**
     * 谷歌面试题扩展版 动态规划版本
     */
    public static double f4(int N, int a, int b) {
        if (N <= 0 || a >= b || a < 0 || b < 0) return 0.0;
        if (b - a >= N) return 1.0;

        // dp[i]：cur为i时，胜率
        double[] dp = new double[b + 1];

        /*
        根据这两个 base case 初始化dp表
        if (cur >= b) return 0.0;
        if (cur >= a && cur < b) return 1.0;
         */
        dp[b] = 0.0;
        for (int i = a; i < b; i++) {
            dp[i] = 1.0;
        }

        // dp[cur] 依赖 dp[cur + 1]，从后往前填
        for (int cur = a - 1; cur >= 0; cur--) {
            double res = 0.0;
            if (cur == a - 1) res = 1.0 * (b - a);
            else if (cur + N + 1 >= b) res = dp[cur + 1] + dp[cur + 1] * N;
            else res = dp[cur + 1] + dp[cur + 1] * N - dp[cur + N + 1];
            dp[cur] = res / N;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int N = 10;
        int a = 17;
        int b = 21;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b);
        System.out.println(f1());
        System.out.println(f2(N, a, b));
        System.out.println(f3(N, a, b));
        System.out.println(f4(N, a, b));

        int maxN = 15;
        int maxM = 20;
        int testTime = 100000;
        System.out.println("测试开始");
        System.out.println("比对double类型答案可能会有精度对不准的问题");
        System.out.println("所以答案一律只保留小数点后四位进行比对");
        System.out.println("如果没有错误提示, 说明验证通过");
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < testTime; i++) {
            N = (int) (Math.random() * maxN);
            a = (int) (Math.random() * maxM);
            b = (int) (Math.random() * maxM);
            double ans2 = Double.valueOf(df.format(f2(N, a, b)));
            double ans3 = Double.valueOf(df.format(f3(N, a, b)));
            double ans4 = Double.valueOf(df.format(f4(N, a, b)));
            if (ans2 != ans3 || ans2 != ans4) {
                System.out.println("Oops!");
                System.out.println(N + " , " + a + " , " + b);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
            }
        }
        System.out.println("测试结束");
    }

}
