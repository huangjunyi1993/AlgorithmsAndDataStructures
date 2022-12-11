package _02进阶._27数组累加和问题;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组，有正有负有0，返回累加和等于k的所有子数组中的最大长度子数组的长度
 * Created by huangjunyi on 2022/9/11.
 */
public class ArrSum02 {

    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        // key 前缀和，value 最早出现该前缀和的位置
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        // 预先塞一条前缀和为0的记录，前缀和0最早出现在-1位置，
        // 如果遇到前缀和就是k的情况，就可以收集答案
        // 如果不垫这一条记录，遇到前缀和就是k的情况时，就会错过
        sumIndexMap.put(0, -1);
        int sum = 0; // 前缀和
        int len = 0; // 最长长度
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // 累加前缀和
            int diff = sum - k; // 计算得到k的差值前缀和
            if (sumIndexMap.containsKey(diff)) {
                // 如果map中存在该差值前缀和，则可以收集一个答案PK一下
                len = Math.max(len, i - sumIndexMap.get(diff));
            }
            // 如果map中存在该差值前缀和，不更新
            if (sumIndexMap.containsKey(sum)) continue;
            // 如果map中不存在该差值前缀和，记录
            sumIndexMap.put(sum, i);
        }
        return len;
    }

}
