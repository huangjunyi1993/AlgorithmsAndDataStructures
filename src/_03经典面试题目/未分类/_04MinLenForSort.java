package _03经典面试题目.未分类;

/**
 * 给定一个数组arr，只能对arr中的一个子数组排序， 但是想让arr整体都有序。返回满足这一设定的子数组中，最短的是多长？
 * Created by huangjunyi on 2022/10/2.
 */
public class _04MinLenForSort {

    public static int minLen(int[] arr) {
        if (arr == null || arr.length < 2) return 0;

        /*
        从右往左遍历
        用一个rightMin遍历左侧最小值
        如果arr[i]大于rightMin，代表是要调整位置的
        记录最左侧要调整的位置noMinIndex；
         */
        int rightMin = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > rightMin) {
                noMinIndex = i;
            } else {
                rightMin = Math.min(rightMin, arr[i]);
            }
        }
        if (noMinIndex == -1) return 0;

        /*
        从左往右遍历
        用一个leftMax左侧最大值
        如果arr[i]小于leftMax，代表是要调整位置的
        记录最右侧要调整的位置noMinIndex；
         */
        int leftMax = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < leftMax) {
                noMaxIndex = i;
            } else {
                leftMax = Math.max(leftMax, arr[i]);
            }
        }

        //算出要排序的区间长度
        return noMaxIndex - noMinIndex + 1;
    }

}
