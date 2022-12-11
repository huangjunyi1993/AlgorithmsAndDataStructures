package _02进阶._34四边形不等式;

/**
 * https://leetcode.cn/problems/split-array-largest-sum/
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * Created by huangjunyi on 2022/12/10.
 */
public class _04PostOfficeProblem {
    class Solution1 {
        public int splitArray(int[] nums, int k) {
            int N = nums.length;
            int[][] dp = new int[N][k + 1];
            // 初始化dp表第0行，0~0范围，分i个子数组，最大的累加和
            for (int i = 1; i <= k; i++) {
                dp[0][i] = nums[0];
            }
            // 初始化dp表第0列，0~i范围，分0个子数组，最大的累加和，无效，不用管
            // 初始化dp表第1列，0~i范围，分1个子数组，最大的累加和，其实就是nums数组前缀和，可以同时把前缀和数组生成好，反正后面要用
            int[] preSum = new int[N];
            preSum[0] = nums[0];
            dp[0][1] = preSum[0];
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
                dp[i][1] = preSum[i];
            }
            /*
            dp[i][j]:
            从0~i，分j个子数组，子数组各自累加和中的最大值，中的最小值（最优划分方案下）
            0~i，切2部分，左部分前j-1个子数组分，后一部分给最后一个数组
            而左部分前j-1个子数组划分的答案，之前已经求过
            dp[i][j] = min(max(dp[0][j-1], rightSum), max(dp[1][j-1], rightSum), max(dp[2][j-1], rightSum), ......)
             */
            for (int i = 1; i < N; i++) {
                for (int j = 2; j <= k; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int split = 0; split < i; split++) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[split][j - 1], preSum[i] - preSum[split]));
                    }
                }
            }
            // 在0~N-1范围上分k个子数组，子数组各自累加和中的最大值，在最右划分方案下的最小值
            return dp[N - 1][k];
        }
    }

    class Solution {
        public int splitArray(int[] nums, int k) {
            int N = nums.length;
            int[][] dp = new int[N][k + 1];
            // split[i][j]存求dp[i][j]是的最优切分位置
            int[][] bestSplit = new int[N][k + 1];
            for (int i = 1; i <= k; i++) {
                dp[0][i] = nums[0];
                // 0~0范围，分i分，只有1个数可以分
                bestSplit[0][i] = 0;
            }
            int[] preSum = new int[N];
            preSum[0] = nums[0];
            dp[0][1] = preSum[0];
            // 0~0范围，分1分，只有1个数可以分
            bestSplit[0][1] = 0;
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
                dp[i][1] = preSum[i];
                // 0~i范围，分1分，那就是不用分，切分为在-1
                bestSplit[i][1] = -1;
            }
            // 整体一列一列从左往右填
            for (int j = 2; j <= k; j++) {
                // 单独一列从下往上填，用四边形不等式优化
                for (int i = N - 1; i >= 1; i--) {
                    dp[i][j] = Integer.MAX_VALUE;
                    // 最优切分位置，只在bestSplit[i][j - 1]和bestSplit[i +1][j]中间范围枚举
                    int best = bestSplit[i][j - 1] == -1 ? 0 :  bestSplit[i][j - 1];
                    for (int split = best; split <= (i < N - 1 ? bestSplit[i + 1][j] : N - 1); split++) {
                        int leftMin = split == -1 ? 0 : dp[split][j - 1];
                        int rightSum = split == i ? 0 : preSum[i] - preSum[split];
                        if (Math.max(leftMin, rightSum) < dp[i][j]) { // 这里 <? 还是 <=?，是一个边界条件，不同题目是不一样的，要都试一下
                            dp[i][j] = Math.max(leftMin, rightSum); // 切分位置往右挪，结果更好
                            best = split; // 更新最优切分位置
                        }
                    }
                    bestSplit[i][j] = best; // 保存最优切分位置
                }
            }
            return dp[N - 1][k];
        }
    }
}
