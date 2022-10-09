package _03经典面试题目.贪心;

import java.util.Arrays;

/**
 * 给定一个数组arr，长度为N且每个值都是正数，代表N个人的体重。再给定一个正数 limit，代表一艘船的载重。
 * 以下是坐船规则，
 * 1)每艘船最多只能做两人;
 * 2)乘客 的体重和不能超过limit
 * 返回如果同时让这N个人过河最少需要几条船。
 * Created by huangjunyi on 2022/10/7.
 */
public class _03MinBoat {

    public static int getMinBoat(int[] arr, int limit) {
        if (arr == null || arr.length == 0 || limit <= 0) return -1;
        int N = arr.length;

        /*
        先对数组进行排序
        然后选出limit/2最右侧的数
        然后从中间开始，左右指针分别往左和往右移动
        L指针所指的数无法和R指针所指的数相加不大于limit，则记录一个x号
        如果L指针所指的数和R指针所指的数相加不大于limit，则R尝试往右划，记录配对数
        最后配对数，加上R指针没有滑到的数的个数，加上x号标记的数的二分之一（有可能为奇数，所以要加1）
         */

        Arrays.sort(arr);
        if (arr[N - 1] > limit) return -1;
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) return arr.length;
        int L = lessR;
        int R = lessR + 1;
        int xCount = 0;
        while (L >= 0) {
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                xCount++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int lessAll = lessR + 1;
        int lessUsed = lessAll - xCount;
        int moreUnSolved = arr.length - lessAll - lessUsed;
        return lessUsed + ((xCount + 1) >> 1) + moreUnSolved;
    }

}
