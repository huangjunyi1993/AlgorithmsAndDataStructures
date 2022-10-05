package _03经典面试题目.动态规划;

import java.util.Arrays;

/**
 * 给定一个正数数组arr，其中所有的值都为整数，以下是最小不可组成和的概念：
 * 把arr每个子集内的所有元素加起来会出现很多值，其中最小的记为min，最大的记为max
 * 在区间[min, max]上，如果有数不可以被arr某一个子集相加得到，那么其中最小的那个数是arr的最小不可组成和
 * 在区间[min, max]上，如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最小不可组成和
 * 请写函数返回正数数组arr的最小不可组成和
 * 保证1一定出现过！
 * 时间复杂度为O(n)，额外空间复杂度为O(1)
 * Created by huangjunyi on 2022/10/3.
 */
public class _018MinUnformedSum {

    public static int getMinUnFormedSum(int[] arr) {
        /*
        先对数组进行排序

        然后初始化range为1
        range表示从1~range范围内的数，都可以被数组从0~i-1范围内某个子集得到

        遍历数组

        如果arr[i] <= range + 1，那么range更新为range + arr[i]，
        假设range为10，arr[i]为5，那么range更新为15，表示从1~15的数，数组0~i范围内都有子集可以得到
        因为数组arr从0~i-1可以累加得到1~range，
        所以只要0~i-1范围先累加得到6，在加上arr[i]的5，即可得到11
        只要0~i-1范围先累加得到7，在加上arr[i]的5，即可得到12，
        .....以此类推，直到15
        所以可以直接更新range为range+arr[i]

        如果arr[i] > range + 1
        那么立刻返回最小不可累加和range + 1
        因为数组arr从0~i-1范围上，只能累加到1~range
        而arr[i]又大于range + 1
        所以如果不要arr[i]，则到不了range+1
        如果要了arr[i]，则一定超过range+1
         */
        if (arr == null || arr.length == 0) return 0;
        Arrays.sort(arr);
        int range = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > range + 1) return range + 1;
            else range += arr[i];
        }
        return range + 1;
    }

}
