package _03经典面试题目.分治法;

import java.util.TreeSet;

/**
 * 给定一个非负数组arr，和一个正数m。 返回arr的所有子序列中累加和%m之后的最大值。
 * 数组中每个值都大于10^8，m为10^12，数组长度最多20个数
 * Created by huangjunyi on 2022/10/9.
 */
public class _02SubsquenceMaxModM {

    public static int getMax(int[] arr, int m) {
        if (arr.length == 1) return arr[0] % m;
        int mid = (arr.length - 1) / 2;
        /*
        因为value都很大，所以不能用背包解（row为arr的数，col为sum，dp为boolean类型）
        因为m很大，所以不能用以m为col的db（row为arr的数，col为m，dp为boolean类型）

        数组砍成两边
        分表求两边的所有子序列累计和
        然后在计算左边累计和最接近m的、右边的累加和最接近m的，两边组合最接近m的，返回最接近m的结果
         */
        TreeSet<Integer> treeSet1 = new TreeSet<>();
        process(arr, 0, 0, mid, m, treeSet1);
        TreeSet<Integer> treeSet2 = new TreeSet<>();
        process(arr, mid + 1, 0, arr.length - 1, m, treeSet2);
        int res = 0;
        for (Integer leftMode : treeSet1) {
            res = Math.max(res, treeSet2.floor(m - 1 - leftMode));
        }
        return res;
    }

    private static void process(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> treeSet) {
        if (index == end + 1) {
            treeSet.add(sum % m);
        }
        process(arr, index + 1, sum, end, m, treeSet);;
        process(arr, index + 1, sum + arr[index], end, m, treeSet);
    }

}
