package _03经典面试题目.动态规划;

import java.util.Map;
import java.util.TreeMap;

/**
 * 背包容量为w
 * 一共有n袋零食，零食体积为v[i]，v[i] > 0
 * 问总体积不超过背包容量的情况下，一共有多少种零食放法？（总体积为0也算一种方法）
 *
 * 假设v[i]的范围是0~10^9，w的范围是1~2*10^9，n的范围是1~30呢？
 * Created by huangjunyi on 2022/9/23.
 */
public class _02BeiBaoZhuangLingShi {

    public static int process(int[] v, int w) {
        int n = v.length;
        /*
        弄一个dp数组（二维）
        dp[i][j]表示零食从0~i自由选择，刚好凑齐j体积的放法数量
         */
        int[][] dp = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        if (v[0] <= w) {
            dp[0][v[0]] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                /*
                动态转移方程
                dp[i][j]等于i号零食不要时的放法数（dp[i - 1][j])加上i号零食要时的放法数（dp[i - 1][j - v[i]]）
                 */
                dp[i][j] = dp[i - 1][j] + (j - v[i] >= 0 ? dp[i - 1][j - v[i]] : 0);
            }
        }
        //把最后一行累加，得到结果
        int res = 0;
        for (int i = 0; i <= w; i++) {
            res += dp[n - 1][i];
        }
        return res;
    }

    public static int process2(int[] v, int w) {
        if (v == null || v.length == 0) return 0;
        if (v.length == 1) return v[0] <= w ? 2 : 1;
        int mid = (v.length - 1) >> 1;
        TreeMap<Integer, Integer> leftMap = new TreeMap<>();
        int ways = getWays(v, 0, 0, w, mid, leftMap);
        TreeMap<Integer, Integer> rightMap = new TreeMap<>();
        ways += getWays(v, 0, 0, w, v.length - 1, leftMap);
        int pre = 0;
        // 右map的前缀和map
        TreeMap<Integer, Integer> rightPreSumMap = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : rightMap.entrySet()) {
            pre += entry.getValue();
            rightPreSumMap.put(entry.getKey(), pre);
        }
        // 左map和右map前缀和map进行匹配
        for (Map.Entry<Integer, Integer> entry : leftMap.entrySet()) {
            // 包大小 - 左map当前遍历到的体积 = 剩余的体积
            Integer key = rightPreSumMap.floorKey(w - entry.getKey());
            if (key != null) {
                // 匹配成功，两边方法数相乘
                ways += entry.getValue() * rightPreSumMap.get(key);
            }
        }
        // +1是还有一种是任何都不选
        return ways + 1;
    }

    /**
     * 从index位置开始，往后自由选择，能凑出的体积不超w的方法数
     * @param v 零食数组
     * @param index 零食数组当前下标
     * @param weight 当前体积
     * @param w 背包容量
     * @param end 结束位置
     * @param map 结果map
     * @return
     */
    private static int getWays(int[] v, int index, int weight, int w, int end, TreeMap<Integer, Integer> map) {
        // 体积超了，无效
        if (weight > w) return 0;
        // 遍历完了
        if (index > end) {
            // 这是什么都不选的，也无效
            if (weight == 0) return 0;
            // 记录到map中，后面用于两边map匹配
            if (map.containsKey(weight)) {
                map.put(weight, map.get(weight) + 1);
            } else {
                map.put(weight, 1);
            }
            // 1中有效方法数
            return 1;
        }
        // 当前位置零食要
        int ways = getWays(v, index + 1, weight, w, end, map);
        // 当前位置零食不要
        ways += getWays(v, index + 1, weight + v[index], w, end, map);
        return ways;
    }

}
