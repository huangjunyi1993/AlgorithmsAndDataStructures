package _03经典面试题目.动态规划;

import java.util.HashMap;

/**
 * 给定一个数组，对该数组进行划分，返回异或和为0的最多部分数
 * Created by huangjunyi on 2022/10/1.
 */
public class _012MaxSubArrayEorZeroSum {

    public static int mostEor(int[] arr) {
         /*
         dp[i] 为 arr中0到i的最大异或和为0的部分数
         dp[i] =
         1、dp[i-1]，代表最后一部分异或和非0，取dp[i-1]的划分值
         2、0~i的异或前缀和为sum，看0~i-1是否也有前缀和为sum的，假设0~k也是sum，那么k+1~i就是最后一个异或和为0的部分，dp[k] + 1
         上面两种情况求max
         最后结果值为dp[len-1]
          */
        int len = arr.length;
        int[] dp = new int[len];
        HashMap<Integer, Integer> map = new HashMap<>(); //key前缀和，value该前值和结尾的数的位置
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum ^= arr[i];
            if (map.containsKey(sum)) dp[i] = map.get(sum) == -1 ? 1 : dp[map.get(sum)] + 1;
            if (i > 0) dp[i] = Math.min(dp[i], dp[i - 1]);
            map.put(sum, i);
        }
        return dp[len - 1];
    }

}
