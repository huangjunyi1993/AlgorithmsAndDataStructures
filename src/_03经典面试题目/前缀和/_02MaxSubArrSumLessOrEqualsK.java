package _03经典面试题目.前缀和;

import java.util.TreeSet;

/**
 * 给定一个数组arr，再给定一个k值,返回累加和小于等于k，但是离k最近的子数组累加和
 *
 * Created by huangjunyi on 2022/10/4.
 */
public class _02MaxSubArrSumLessOrEqualsK {

    public static int getMaxSubArrSumLessOrEqualsK(int[] arr, int k) {
        /*
        数组前缀和 + 有序表
        转化为求大于等于sum - k的前缀和
        然后与当前前缀和相减
        得到以当前i位结尾的小于等于k的累加和

        求大于等于sum - k的前缀和
        通过有序表解决
         */
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer ceiling = set.ceiling(sum - k);
            if (ceiling != null) {
                res = Math.max(res, sum - ceiling);
            }
            set.add(sum);
        }
        return res;
    }

}
