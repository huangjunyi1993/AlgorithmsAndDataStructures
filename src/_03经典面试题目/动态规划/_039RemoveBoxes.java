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
public class _039RemoveBoxes {
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
        // base case：L > R，返回0
        if (L > R) return 0;
        if (dp[L][R][K] != 0) return dp[L][R][K];
        // 优化，前面相同的的都不用试，连成一片
        int last = L;
        int pre = K; // K要用于记录缓存，所以不能修改，另起一个变量记录
        while (last + 1 <= R && boxes[L] == boxes[last + 1]) {
            last++;
            pre++;
        }
        // 前pre个跟last一起消掉了，pre清零， 剩下的后面调递归
        int res = (pre + 1) * (pre + 1) + process(boxes, last + 1, R, 0, dp);
        // 枚举可以跟着前的pre+1个boxes[L]连成一片一起消掉的情况，last + 1是不符合的，跳过
        for (int M = last + 2; M <= R; M++) {
            // 第一个第一个符合的情况就可以了，后面的会在后续递归连上
            if (boxes[L] == boxes[M] && boxes[M - 1] != boxes[L]) {
                res = Math.max(res, process(boxes, last + 1, M - 1, 0, dp) + process(boxes, M, R, pre + 1, dp));
            }
        }
        dp[L][R][K] = res;
        return res;
    }
}
