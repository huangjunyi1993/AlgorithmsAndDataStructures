package _03经典面试题目.动态规划;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 数组中所有数都异或起来的结果，叫做异或和
 * 给定一个数组arr，可以任意切分成若干个不相交的子数组
 * 其中一定存在一种最优方案，使得切出异或和为0的子数组最多
 * 返回这个最多数量
 *
 * Created by huangjunyi on 2022/12/25.
 */
public class _043MostXorZero {

    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        // 前缀异或和 => 最晚出现位置
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        // dp[i] 从0~i，最多能分出多少个异或和为0的部分
        int[] dp = new int[arr.length];
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            // xor：0~i，异或和
            xor ^= arr[i];
            // 前面也有异或和为xor的，最后一部分可以划分出异或和为0的，pre+1~i异或和为0
            if (map.containsKey(xor)) {
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : dp[pre] + 1;
            }
            // 假设最后一部分异或和不为0，arr[i]不要了，取dp[i-1]
            if (i != 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
        }
        return dp[arr.length - 1];
    }

}
