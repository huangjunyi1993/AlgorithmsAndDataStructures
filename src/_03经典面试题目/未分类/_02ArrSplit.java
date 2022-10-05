package _03经典面试题目.未分类;

/**
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的作为右部分。
 * 但是每种划分下都有左部分的最大值和右部分的最大值
 * 请返回最大的，左部分最大值减去右部分最大值的绝对值。
 * Created by huangjunyi on 2022/9/18.
 */
public class _02ArrSplit {

    public static int getMaxABSDiff(int[] arr) {
        int max = Integer.MIN_VALUE;
        //求出数组中的最大值
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        /*
        因为不管怎么分，左边一定包含arr[0]，所以左边的最大值中的最小只能到arr[0]
        右边一定包含arr[arr.length - 1]，所以右边的最大值中的最小只能到arr[1]
        那么分法就两种：
        0|......
        ......|arr.length-1
        所以返回max减arr[0]或者max减arr[arr.length - 1]中的最大值就对了
         */
        return Math.max(max - arr[0], max - arr[arr.length - 1]);
    }

}
