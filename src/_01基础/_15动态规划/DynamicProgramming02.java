package _01基础._15动态规划;

/**
 * 背包问题（动态规划求解）
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming02 {

    public static int processByRecursive(int[] weights, int[] values, int bag) {
        return byRecursive(weights, values, 0, bag);
    }

    /**
     * 暴力递归
     * @param weights 物品总量
     * @param values 物品价值
     * @param index 当前物品下标
     * @param space 背包剩余空间
     * @return
     */
    private static int byRecursive(int[] weights, int[] values, int index, int space) {
        if (space < 0 || index == weights.length) return 0;
        // 当前位置物品不要，直接下一轮递归
        int p1 = byRecursive(weights, values, index + 1, space);
        int p2 = Integer.MIN_VALUE;
        if (space >= weights[index]) {
            //如果背包还有空间放得下当前物品，参数装入当前物品，进行下一轮递归
            p2 = values[index] + byRecursive(weights, values, index + 1, space - weights[index]);
        }
        //递归返回，总p1,p2中取最大值
        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     * @param weights 物品总量
     * @param values 物品价值
     * @param bag 背包能承载的重量
     * @return
     */
    public static int processByDynamicProgramming(int[] weights, int[] values, int bag) {
        int[][] dp = new int[values.length + 1][bag + 1];
        for (int index = values.length - 1; index >= 0; index--) {
            for (int remain = 0; remain <= bag; remain--) {
                int p1 = dp[index + 1][remain];
                int p2 = Integer.MIN_VALUE;
                if (remain >= weights[index]) {
                    //如果背包还有空间放得下当前物品，参数装入当前物品，进行下一轮递归
                    p2 = values[index] + dp[index + 1][remain - weights[index]];
                }
                dp[index][remain] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

}
