package _03经典面试题目.未分类;

import java.util.HashSet;
import java.util.Set;

/**
 * 先给出可整合数组的定义:如果一个数组在排序之后，每相邻两个数差的绝对值 都为 1， 则该数组为可整合数组。
 * 例如，[5,3,4,6,2]排序之后为[2,3,4,5,6]， 符合每相邻两个数差的绝对值 都为 1，所以这个数组为可整合数组。
 *
 * 给定一个整型数组 arr，请返回其中最大可整合子数组的长度。
 * 例如， [5,5,3,2,6,4,3]的最大 可整合子数组为[5,3,2,6,4]，
 * 所以返回 5。
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _07KeZhengHeShuZu {

    public static int process(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            set.clear();
            set.add(arr[i]);
            int max = arr[i];
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                /*
                可整合数组，必须没有重复值
                如果有，则换头部，继续尝试
                 */
                if (set.contains(arr[j])) break;
                /*
                记录到set中，一方面用于判重
                另一方面
                如果set中的最大值，减去set中的最小值
                等于set的大小减一
                代表set中的数可组成 可整合数组
                尝试更新结果值res
                 */
                set.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if (max - min == set.size() - 1) res = Math.max(res, set.size());
            }
        }
        return res;
    }

}
