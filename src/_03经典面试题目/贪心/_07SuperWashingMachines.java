package _03经典面试题目.贪心;

/**
 * https://leetcode.cn/problems/super-washing-machines/
 *
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。
 * 如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
 *
 * Created by huangjunyi on 2023/1/1.
 */
public class _07SuperWashingMachines {
    class Solution {
        public int findMinMoves(int[] machines) {
            if (machines == null || machines.length == 0) return 0;
            int sum = 0;
            for (int i = 0; i < machines.length; i++) {
                sum += machines[i];
            }
            if (sum % machines.length != 0) return -1;
            int avg = sum / machines.length;
            int leftSum = 0;
            int max = 0;
            /*
            贪心思想：枚举每个位置的挪动代价，最大代价位置的挪动代价，就是整体平均的最少挪动次数

            每个位置的挪动代价：
            1、左剩余 < 0，右剩余 < 0，|左剩余|+|右剩余| ，因当前位置洗衣机只能一次往一个方向挪
            2、max(|左剩余|, |右剩余|)，那边要挪的较多，哪边要挪的量，就是当前位置的代价

             */
            for (int i = 0; i < machines.length; i++) {
                int leftRest = leftSum - i * avg;
                int rightRest = (sum - leftSum - machines[i]) - (machines.length - 1 - i) * avg;
                if (leftRest < 0 && rightRest < 0) {
                    max = Math.max(max, Math.abs(leftRest) + Math.abs(rightRest));
                } else {
                    max = Math.max(max, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
                }
                leftSum += machines[i];
            }
            return max;
        }
    }
}
