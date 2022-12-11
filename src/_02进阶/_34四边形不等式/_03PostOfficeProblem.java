package _02进阶._34四边形不等式;

/**
 * 摆放着n堆石子。现要将石子有次序地合并成一堆
 * 规定每次只能选相邻的2堆石子合并成新的一堆，
 * 并将新的一堆石子数记为该次合并的得分
 * 求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
 *
 * Created by huangjunyi on 2022/12/10.
 */
public class _03PostOfficeProblem {

    public static int mergeStone1(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int N = arr.length;
        // 初始化dp表的两条对角线，因为dp[i][i]对角线本来就是0，所以不用管
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = arr[i] + arr[i + 1];
        }
        // 求一个前缀和数组，后面要用到
        int[] preSum = new int[N];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        /*
        dp[L][R]表示从L~R范围上，最优合并方案的最小代价
        枚举最后一次合并的切分方案
        比如dp[0][3]，0~3范围最优合并方案的最小代价
        最后一次合并的切分方案就有：
        0~0和1~3合并，0~1和2~3合并，0~2和3~3合并
        所以dp[0][3] = min(dp[0][0]+dp[1][3], dp[0][1]+dp[2][3], dp[0][2]+dp[3][3]) + sum(arr, 0, 3)
         */
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Integer.MAX_VALUE;
                for (int split = L; split < R; split++) {
                    dp[L][R] = Math.min(dp[L][R], dp[L][split] + dp[split + 1][R]);
                }
                // + sum(arr, L, R)
                dp[L][R] = dp[L][R] + preSum[R] - (L == 0 ? 0 : preSum[L - 1]);
            }
        }
        // 从0~N-1范围上，最优合并方案的最小代价
        return dp[0][N - 1];
    }

    public static int mergeStone2(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int N = arr.length;
        // 初始化dp表和bestSplit表的两条对角线，因为dp[i][i]对角线本来就是0，bestSplit[i][i]只有一个数，也没得切，所以不用管
        int[][] dp = new int[N][N];
        int[][] bestSplit = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = arr[i] + arr[i + 1];
            bestSplit[i][i + 1] = i;
        }
        // 求一个前缀和数组，后面要用到
        int[] preSum = new int[N];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        /*
        dp[L][R]表示从L~R范围上，最优合并方案的最小代价
        枚举最后一次合并的切分方案
        比如dp[0][3]，0~3范围最优合并方案的最小代价
        最后一次合并的切分方案就有：
        0~0和1~3合并，0~1和2~3合并，0~2和3~3合并
        所以dp[0][3] = min(dp[0][0]+dp[1][3], dp[0][1]+dp[2][3], dp[0][2]+dp[3][3]) + sum(arr, 0, 3)

        bestSplit[L][R]表示的是在求dp[L][R]时的最优切分位置
        用于优化枚举行为，切分位置不用回退
         */
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Integer.MAX_VALUE;
                // 切分位置在上一轮的基础上往后尝试
                int best = bestSplit[L][R - 1];
                // 当前L~R的最优切分位置，肯定在getBestSplit(L, R-1)和getBestSplit(L+1, R)之间
                // 所以就在bestSplit[L][R - 1]~bestSplit[L + 1][R]之间
                for (int split = bestSplit[L][R - 1]; split <= bestSplit[L + 1][R]; split++) {
                    if (dp[L][split] + dp[split + 1][R] < dp[L][R]) {
                        dp[L][R] = Math.min(dp[L][R], dp[L][split] + dp[split + 1][R]);
                        best = split; // 更新切分位置
                    }
                }
                // + sum(arr, L, R)
                dp[L][R] = dp[L][R] + preSum[R] - (L == 0 ? 0 : preSum[L - 1]);
                // 记录最优切分位置
                bestSplit[L][R] = best;
            }
        }
        // 从0~N-1范围上，最优合并方案的最小代价
        return dp[0][N - 1];
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        int[][] dp = new int[N][N];
        int[][] best = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            best[i][i + 1] = i;
            dp[i][i + 1] = w(s, i, i + 1);
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                int choose = -1;
                for (int leftEnd = best[L][R - 1]; leftEnd <= best[L + 1][R]; leftEnd++) {
                    int cur = dp[L][leftEnd] + dp[leftEnd + 1][R];
                    if (cur <= next) {
                        next = cur;
                        choose = leftEnd;
                    }
                }
                best[L][R] = choose;
                dp[L][R] = next + w(s, L, R);
            }
        }
        return dp[0][N - 1];
    }

    public static int w(int[] s, int l, int r) {
        return s[r + 1] - s[l];
    }

    public static int[] sum(int[] arr) {
        int N = arr.length;
        int[] s = new int[N + 1];
        s[0] = 0;
        for (int i = 0; i < N; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = mergeStone1(arr);
            int ans2 = mergeStone2(arr);
            int ans3 = min3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
