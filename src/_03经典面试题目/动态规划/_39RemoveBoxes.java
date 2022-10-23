package _03经典面试题目.动态规划;

/**
 * 给出一些不同颜色的盒子 boxes ，盒子的颜色由不同的正数表示。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。
 * 每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k * k 个积分。
 * 返回 你能获得的最大积分和 。
 *
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 *
 * Created by huangjunyi on 2022/10/20.
 */
public class _39RemoveBoxes {
    public int removeBoxes(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        return process(boxes, 0, N - 1, 0, dp);
    }

    /**
     * 前面跟着K个boxes[L]，消掉L~R的最高得分
     * @param boxes 盒子数组
     * @param L 左边界
     * @param R 右边界
     * @param K 左边跟的boxes[L] 的数量
     * @param dp 记忆数组
     * @return
     */
    private int process(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) return 0;
        if (dp[L][R][K] != 0) return dp[L][R][K];
        if (L == R) {
            dp[L][R][K] = (K + 1) * (K + 1);
            return dp[L][R][K];
        }
        while (L < R && boxes[L] == boxes[L + 1]) {
            L++;
            K++;
        }
        int res = (K + 1) * (K + 1) + process(boxes, L + 1, R, 0, dp);
        // 没去可以跟着前的K个boxes[L]连成一片一起消掉的情况
        for (int M = L + 1; M <= R; M++) {
            if (boxes[L] == boxes[M]) {
                res = Math.max(res, process(boxes, L + 1, M - 1, 0, dp) + process(boxes, M, R, K + 1, dp));
            }
        }
        dp[L][R][K] = res;
        return res;
    }
}
