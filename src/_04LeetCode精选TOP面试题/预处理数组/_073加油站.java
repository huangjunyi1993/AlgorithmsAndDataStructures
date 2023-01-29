package _04LeetCode精选TOP面试题.预处理数组;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/gas-station/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/31.
 */
public class _073加油站 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        /*
        netEnergyArr是纯能值数组
        netEnergyArr中的每一个，就是gas中对应的位减去cost对应的位的值
        验证从一个位置出发，能否转一圈回来
        只要看netEnergyArr从该位置出发，一致累加，看中途是否会累加出负数即可
        如果没有累加出负数，则表示可以转一圈回来的
        累加出负数了，则表示不可以转一圈回来

        但是这种解法是O(N^2)的
        所以需要优化

        搞一个两倍长的累加和数组accumulation
        后半部分的累加是转圈回来的累加
        如netEnergyArr = [6,-1,-2,4,-2,-4]
        则accumulation = [6,5,3,7,5,1,7,6,4,8,6,2]
        验证0号位置是否可转圈回来
        只要验证accumulation中0~5的最小值是否小于0即可
        验证1号位置是否可转圈回来
        只要验证accumulation中1~6的最小值，减去accumulation[0]，是否小于0即可
        验证2号位置是否可转圈回来
        只要验证accumulation中2~7的最小值，减去accumulation[1]，是否小于0即可
        依次类推...
        直到验完0~5所谓位置

        时间复杂度O(N)
         */
        int [] netEnergyArr = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            netEnergyArr[i] = gas[i] - cost[i];
        }
        int[] accumulation = new int[netEnergyArr.length * 2];
        accumulation[0] = netEnergyArr[0];
        for (int i = 1; i < netEnergyArr.length; i++) {
            accumulation[i] = accumulation[i - 1] + netEnergyArr[i];
        }
        for (int i = 0; i < netEnergyArr.length; i++) {
            accumulation[i + netEnergyArr.length] = accumulation[i + netEnergyArr.length - 1] + netEnergyArr[i];
        }
        Deque<Integer> minQ = new LinkedList<>();
        for (int i = 0; i < netEnergyArr.length; i++) {
            if (minQ.isEmpty()) {
                minQ.addLast(i);
            } else {
                while (!minQ.isEmpty() && accumulation[minQ.peekLast()] >= accumulation[i]) {
                    minQ.pollLast();
                }
                minQ.addLast(i);
            }
        }
        int L = 0;
        int R = netEnergyArr.length - 1;
        while (L < netEnergyArr.length) {
            if (accumulation[minQ.peekFirst()] - (L > 0 ? accumulation[L - 1] : 0) >= 0) {
                return L;
            }
            if (L == minQ.peekFirst()) {
                minQ.pollFirst();
            }
            L++;
            R++;
            while (!minQ.isEmpty() && accumulation[minQ.peekLast()] >= accumulation[R]) {
                minQ.pollLast();
            }
            minQ.addLast(R);
        }
        return -1;
    }
}
