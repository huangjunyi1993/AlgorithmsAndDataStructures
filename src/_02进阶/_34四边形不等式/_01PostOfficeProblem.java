package _02进阶._34四边形不等式;

/**
 * 给定一个非负数组arr，长度为N，
 * 那么有N-1种方案可以把arr切成左右两部分
 * 每一种方案都有，min{左部分累加和，有部分累加和}
 * 求这么多方案中，min{左部分累加和，有部分累加和}的最大值是多少？
 * 整个过程要求时间复杂度O(N)
 * Created by huangjunyi on 2022/12/10.
 */
public class _01PostOfficeProblem {

    public static int splitArray(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        // 求出整体累加和sum
        int sum = 0;
        for (int num : arr) sum += num;
        int max = 0;
        // 左部分累加和
        int leftSum = 0;
        // 枚举每个切分位置
        for (int i = 0; i < arr.length; i++) {
            // 左部分增加进入左侧的数
            leftSum += arr[i];
            // 抓一个min(left,right)样本，PK一下
            max = Math.max(max, Math.min(leftSum, sum - leftSum));
        }
        return max;
    }

}
