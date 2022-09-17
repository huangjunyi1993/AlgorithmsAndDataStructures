package _01基础._14暴力递归;

/**
 * 背包问题（暴力递归求解）
 * Created by huangjunyi on 2022/9/2.
 */
public class Recursive06 {

    public static int process(int[] weights, int[] values, int bag) {
        return process(weights, values, 0, bag);
    }

    private static int process(int[] weights, int[] values, int index, int space) {
        if (space < 0 || index == weights.length) return 0;
        // 当前位置物品不要，之间下一轮递归
        int p1 = process(weights, values, index + 1, space);
        int p2 = Integer.MIN_VALUE;
        if (space >= weights[index]) {
            //如果背包还有空间放得下当前物品，参数装入当前物品，进行下一轮递归
            p2 = values[index] + process(weights, values, index + 1, space - weights[index]);
        }
        //递归返回，总p1,p2中取最大值
        return Math.max(p1, p2);
    }

}
