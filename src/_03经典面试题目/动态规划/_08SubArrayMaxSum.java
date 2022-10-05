package _03经典面试题目.动态规划;

/**
 * 给定一个数组arr，返回子数组的最大累加和。
 * Created by huangjunyi on 2022/9/25.
 */
public class _08SubArrayMaxSum {

    public static int getMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        /*
        用一个变量cur累加遍历到的arr[i]
        每次累加完后尝试更新max
        如果cur小于0，则把cur还原回0
        最后返回max，就是答案
         */
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

}
