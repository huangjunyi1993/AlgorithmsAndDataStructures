package _03经典面试题目.动态规划;

/**
 * int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * 返回通过所有的怪兽，需要花的最小钱数。
 * Created by huangjunyi on 2022/10/3.
 */
public class _020MinMoneyThroughAllMonster {

    public static int minMoney(int[] d,int[] p) {
        if (d == null || p == null || d.length == 0 || p.length == 0 || d.length != p.length) return 0;
        return process(d, p, 0, 0);
    }

    /**
     * 第一种解法 递归
     * @param d 怪兽的能力
     * @param p 怪兽要求的钱
     * @param ability 当前能力值
     * @param index 怪兽下标
     * @return
     */
    public static int process(int[] d, int[] p, int ability, int index) {
        if (index == d.length) return 0;
        if (ability < d[index]) {
            return p[index] + process(d, p, ability + d[index], index + 1);
        } else {
            return Math.min(p[index] + process(d, p, ability + d[index], index + 1),
                    process(d, p, ability, index + 1));
        }
    }

    public static int dp(int[] d,int[] p) {
        if (d == null || p == null || d.length == 0 || p.length == 0 || d.length != p.length) return 0;
        int money = 0;
        for (int i = 0; i < p.length; i++) {
            money += p[i];
        }

        /*
        dp表
        dp[i][j] 表示通过从0~i号怪兽，是否内严格花掉j块钱，
        能的话填上获得的最大能力值
        不能则填-1
         */
        int[][] dp = new int[d.length][money + 1];
        for (int i = 0; i < d.length; i++) {
            dp[i][0] = -1;
        }
        for (int i = 0; i < money; i++) {
            dp[0][i] = -1;
        }
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 1; j <= money; j++) {
                /*
                1、如果能严格花掉j块钱通关0~i-1号怪兽，并且获得的能力值大于i号怪兽的能力值，则直接通过，能力值为dp[i - 1][j]
                2、如果能严格花掉j-p[i]块钱，通过0~i-1号怪兽，选择贿赂i号怪兽，能力值dp[i - 1][j - p[i]] + d[i]
                上面两种情况的值，选最大的填到当前格子
                 */
                int ability1 = dp[i - 1][j] >= d[j] ? dp[i - 1][j] : -1;
                int ability2 = j - p[i] >= 0 && dp[i - 1][j - p[i]] != -1 ? dp[i - 1][j - p[i]] + d[i] : -1;
                dp[i][j] = Math.max(ability1, ability2);
            }
        }

        //遍历dp表的最后一行，有不为-1的，就是通关所有怪兽花掉最少的钱
        for (int i = 0; i <= money; i++) {
            if (dp[d.length - 1][i] != -1) return i;
        }
        return money;
    }

}
