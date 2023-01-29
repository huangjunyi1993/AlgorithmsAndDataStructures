package _05面试真题;


import java.util.Arrays;

/**
 * 来自小红书
 * [0,4,7] ： 0表示这里石头没有颜色，如果变红代价是4，如果变蓝代价是7
 * [1,X,X] ： 1表示这里石头已经是红，而且不能改颜色，所以后两个数X无意义
 * [2,X,X] ： 2表示这里石头已经是蓝，而且不能改颜色，所以后两个数X无意义
 * 颜色只可能是0、1、2，代价一定>=0
 * 给你一批这样的小数组，要求最后必须所有石头都有颜色，且红色和蓝色一样多，返回最小代价
 * 如果怎么都无法做到所有石头都有颜色、且红色和蓝色一样多，返回-1
 * Created by huangjunyi on 2023/1/18.
 */
public class Code07_MagicStone {
    public static int minCost(int[][] stones) {
        if (stones == null || stones.length == 0) return 0;
        // 奇数个石头，无法达标
        if ((stones.length & 1) != 0) return -1;
        /*
        贪心：（司机分配模型）
        假设把无色石头染成红色需要的花费记为a，把无色石头染成蓝色需要的花费记为b
        先把所有无色石头染成红色，记录花费cost
        然后计算出需要染成多少个蓝石头才达标
        然后在挑出这么多个，a-b这个差值最大的石头，变为蓝色，调整cost
         */
        int zero = 0; // 没有颜色的石头数量
        int red = 0; // 红石头数量
        int blue = 0; // 蓝石头数量
        int cost = 0; // 花费
        for (int i = 0; i < stones.length; i++) {
            if (stones[i][0] == 0) {
                zero++;
                // 先把所有无色石头染成红色，记录花费cost
                cost += stones[i][1];
            } else if (stones[i][0] == 1) {
                red++;
            } else if (stones[i][0] == 2) {
                blue++;
            }
        }
        // 红石头或者蓝石头超过一半，无法达标
        if ((red > (stones.length >> 1) || (blue > (stones.length >> 1)))) return -1;

        // 排序，无色石头排前面，并且无色石头中，红花费-蓝花费 差值大的排前面
        Arrays.sort(stones, (a, b) -> a[0] == 0 && b[0] == 0 ? ((b[1] - b[2]) - (a[1] - a[2])) : a[0] - b[0]);
        // 需要染成多少个蓝石头才达标
        blue = (zero - ((stones.length >> 1) - red));
        // 挑出这么多个，a-b这个差值最大的石头，变为蓝色，调整cost
        for (int i = 0; i < blue; i++) {
            cost = cost - stones[i][1] + stones[i][2];
        }
        return cost;
    }

    public static int minCostZuo(int[][] stones) {
        int n = stones.length;
        if ((n & 1) != 0) {
            return -1;
        }
        Arrays.sort(stones, (a, b) -> a[0] == 0 && b[0] == 0 ? (b[1] - b[2] - a[1] + a[2]) : (a[0] - b[0]));
        int zero = 0;
        int red = 0;
        int blue = 0;
        int cost = 0;
        for (int i = 0; i < n; i++) {
            if (stones[i][0] == 0) {
                zero++;
                cost += stones[i][1];
            } else if (stones[i][0] == 1) {
                red++;
            } else {
                blue++;
            }
        }
        if (red > (n >> 1) || blue > (n >> 1)) {
            return -1;
        }
        blue = zero - ((n >> 1) - red);
        for (int i = 0; i < blue; i++) {
            cost += stones[i][2] - stones[i][1];
        }
        return cost;
    }

    public static void main(String[] args) {
        int[][] stones = { { 1, 5, 3 }, { 2, 7, 9 }, { 0, 6, 4 }, { 0, 7, 9 }, { 0, 2, 1 }, { 0, 5, 9 } };
        System.out.println(minCost(stones));
        System.out.println(minCostZuo(stones));
    }
}
