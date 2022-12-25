package _03经典面试题目.贪心;

import java.util.Arrays;

/**
 *
 * 给定两个数组x和hp，长度都是N。
 * x数组一定是有序的，x[i]表示i号怪兽在x轴上的位置
 * hp数组不要求有序，hp[i]表示i号怪兽的血量
 * 为了方便起见，可以认为x数组和hp数组中没有负数。
 * 再给定一个正数range，表示如果法师释放技能的范围长度(直径！)
 * 被打到的每只怪兽损失1点血量。
 * 返回要把所有怪兽血量清空，至少需要释放多少次aoe技能？
 * 三个参数：int[] x, int[] hp, int range
 * 返回：int 次数
 *
 * Created by huangjunyi on 2022/12/20.
 */
public class _05AOE {

    public static int minAoe(int[] x, int[] hp, int range) {

        /*
        贪心思想：
        每次用aoe范围的最左侧，刮死最左侧存活的怪兽，是最省的
         */

        // 边沿数组，以每个怪兽作为当前aoe范围的最左侧，右侧到哪刮不到
        int[] edge = new int[x.length];
        int r = 0;
        for (int i = 0; i < x.length; i++) {
            while (r < x.length && x[r] - x[i] <= range) r++;
            edge[i] = r;
        }
        int res = 0;
        for (int i = 0; i < hp.length; i++) {
            if (hp[i] > 0) {
                // 如果当前怪兽存活，以它为最左侧，往右aoe范围内的怪兽，都扣减血量（刚好整死最左侧怪兽），
                int minus = hp[i];
                for (int j = i; j < edge[i]; j ++) hp[j] -= minus;
                res += minus;
            }
        }
        return res;
    }

}
