package _03经典面试题目.动态规划;

import java.util.Arrays;

/**
 * 每个信封都有长和宽两个维度的数据，A信封如果想套在B信封里面，A信封必须在长和宽上都小于B信封才行。
 * 如果给你一批信封，返回最大的嵌套层数。
 * Created by huangjunyi on 2022/9/25.
 */
public class _07Envelope {

    public static int getMaxLevel(int[][] matrix) {
        /*
        先把数组进行排序:
        首先根据长度从小打到排序 o[0]
        长度相等，则根据宽度从大到小排序

        然后把排好序的数组，取出宽度值，组成一维数组
        然后用求最长递增子序列长度的方法
        就能求出结果

        因为已经根据长度从小到大进行排序，而长度相等时，根据宽从大到小排序
        所以前面宽比当前宽小的，长必然也小
         */
        Arrays.sort(matrix, (o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            else return o2[1] - o1[1];
        });
        int[] seq = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            seq[i] = matrix[i][1];
        }
        //求最长递增子序列长度
        return getMaxSubSeqLen(seq);
    }

    private static int getMaxSubSeqLen(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[] ends = new int[arr.length];
        int right = 0;
        ends[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m +1;
                } else {
                    r = m - 1;
                }
            }
            ends[l] = arr[i];
            if (right < l) right = l;
        }
        return right + 1;
    }

}
