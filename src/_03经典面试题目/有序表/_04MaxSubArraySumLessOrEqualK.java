package _03经典面试题目.有序表;

import java.util.TreeSet;

/**
 * 请返回arr中，求个子数组的累加和，是<=K的，并且是最大的。
 * 返回这个最大的累加和
 * Created by huangjunyi on 2023/1/1.
 */
public class _04MaxSubArraySumLessOrEqualK {
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        // 前缀和有序表
        TreeSet<Integer> preSumSet = new TreeSet<>();
        preSumSet.add(0);
        int preSum = 0; // 前缀和
        int max = Integer.MIN_VALUE;
        /*
        每一个位置求一个前缀和preSum
        从有序表查询大于presum-K，最接近presum-K的前和
        presum和这个前缀和相减，得到一个<=K的累加和，与答案PK一下
        前缀放有序表里，供后面的前缀和使用
         */
        for (int i = 0; i < arr.length; i++) {
            preSum += arr[i];
            if (preSumSet.ceiling(preSum - K) != null) {
                max = Math.max(max, preSum - preSumSet.ceiling(preSum - K));
            }
            preSumSet.add(preSum);
        }
        return max;
    }
}
