package _03经典面试题目.动态规划;

/**
 * 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件：
 * 1. 0位置的要求：arr[0] <= arr[1]
 * 2. n-1位置的要求：arr[n-1] <= arr[n-2]
 * 3. 中间i位置的要求：arr[i] <= max(arr[i-1], arr[i+1])
 * 但是在arr有些数字丢失了，比如k位置的数字之前是正数，丢失之后k位置的数字为0。
 * 请你根据上述条件，计算可能有多少种不同的arr可以满足以上条件。
 * 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种。 [6,9,9] 达标
 * [ ...... 0 0]
 *
 * Created by huangjunyi on 2022/10/21.
 */
public class _041RestoreWays {

    public static int ways1(int[] arr) {
        int N = arr.length;
        if (arr[N - 1] != 0) {
            return process1(arr, N - 1, arr[N - 1], 2);
        } else {
            int ways = 0;
            for (int i = 1; i < 201; i++) {
                ways += process1(arr, N - 1, i, 2);
            }
            return ways;
        }
    }

    /**
     * i位置的数，把它变成V，S是i位置的数与右边数的关系，返回把数组（从0~i）变成达标数组，有多少种方法
     * @param arr 原数组
     * @param i 当前下标
     * @param V 把i位置的数变为V
     * @param S i与右侧（i+1）数的关系， 0表示小于右边的数，1表示等于右边的数，2表示大于右边的数
     * @return
     */
    private static int process1(int[] arr, int i, int V, int S) {
        /*
        从右往左递归
        记录与前一个数的关系
        直到i==0，判断i位置是否符合条件，符合则表示找到一种方法数
        每次递归，判断是否符合条件，不为0又不是目标数，提前返回0
         */

        // base case：i来到0位置，只有i小于或等于右侧的数，并且是0或者等于V，才算一种有效方法
        if (i == 0) return ((S == 0 || S == 1) && (arr[i] == 0 || arr[i] == V)) ? 1 : 0;

        // 如果i位置的数没丢掉，又不等于V这个期望值，返回0，无效
        if (arr[i] != 0 && arr[i] != V) return 0;

        // 遍历i-1位能填的数1~200，递归
        int ways = 0;
        if (S == 0 || S == 1) {
            // i位置的数小于等于i+1位置的数，此时i-1可以随便填
            for (int leftValue = 1; i < 201; i++) {
                ways += process1(arr, i - 1, leftValue, leftValue < V ? 0 : (leftValue == V ? 1 : 2));
            }
        } else {
            // i位置的数大于i+1位置的数，必须通过i-1填的数补救，否则i就违规了（arr[i] <= max(arr[i-1], arr[i+1])）
            for (int leftValue = V; i < 201; i++) {
                ways += process1(arr, i - 1, leftValue, leftValue == V ? 1 : 2);
            }
        }
        return ways;
    }

    /**
     * 从递归尝试改成动态规划
     * @param arr
     * @return
     */
    public static int ways2(int[] arr) {
        int N = arr.length;
        /*
        dp[i][V][S]
        i位置的数，把它变成V，S是i位置的数与右边数的关系，把数组（从0~i）变成达标数组，有多少种方法
         */
        int[][][] dp = new int[N][201][3];
        // 根据base case 初始化dp表
        // base case：i来到0位置，只有i小于或等于右侧的数，并且是0或者等于V，才算一种有效方法
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int V = 1; V < 201; V++) {
                dp[0][V][0] = 1;
                dp[0][V][1] = 1;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int V = 1; V < 201; V++) {
                for (int S = 0; S < 3; S++) {
                    if (arr[i] != 0 && arr[i] != V) {
                        // 如果i位置的数没丢掉，又不等于V这个期望值，填0，无效
                        dp[i][V][S] = 0;
                    } else {
                        int ways = 0;
                        if (S == 0 || S == 1) {
                            // i位置的数小于等于i+1位置的数，此时i-1随便
                            for (int leftValue = 1; i < 201; i++) {
                                ways += dp[i - 1][leftValue][leftValue < V ? 0 : (leftValue == V ? 1 : 2)];
                            }
                        } else {
                            // i位置的数大于i+1位置的数，必须通过i-1填的数补救，否则i就违规了（arr[i] <= max(arr[i-1], arr[i+1])）
                            for (int leftValue = V; i < 201; i++) {
                                ways += dp[i - 1][leftValue][leftValue == V ? 1 : 2];
                            }
                        }
                        dp[i][V][S] = ways;
                    }
                }
            }
        }

        // 根据顶层递归，确定返回值
        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            int ways = 0;
            for (int i = 1; i < 201; i++) {
                ways += dp[N - 1][i][2];
            }
            return ways;
        }
    }

    /**
     * 动态规划枚举行为优化
     * @param arr
     * @return
     */
    public static int ways3(int[] arr) {
        int N = arr.length;
        int[][][] dp = new int[N][201][3];
        if (arr[0] != 0) {
            dp[0][arr[0]][0] = 1;
            dp[0][arr[0]][1] = 1;
        } else {
            for (int V = 1; V < 201; V++) {
                dp[0][V][0] = 1;
                dp[0][V][1] = 1;
            }
        }

        /*
        preSum用于简化枚举行为
        preSum[V][s]
        表示原先递归从0~V位置，与右侧关系为s，的前缀和
        比如preSum[60][0]
        表示i为0~60，s为0，所有方法数的累加和
         */
        int[][] preSum = new int[201][3];
        for (int v = 1; v < 201; v++) {
            for (int s = 0; s < 3; s++) {
                preSum[v][s] = preSum[v - 1][s] + dp[0][v][s];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int V = 1; V < 201; V++) {
                for (int S = 0; S < 3; S++) {
                    if (arr[i] != 0 && arr[i] != V) {
                        dp[i][V][S] = 0;
                    } else {
                        // 通过preSum简化枚举
                        int ways = 0;
                        if (S == 0 || S == 1) {
                            ways += preSum[V - 1][0] + dp[i - 1][V][1] + preSum[200][0] - preSum[V][0];
                        } else {
                            ways += dp[i - 1][V][1] + preSum[200][2] - preSum[V][2];
                        }
                        dp[i][V][S] = ways;
                    }
                }
            }
            // 加工preSum
            for (int v = 1; v < 201; v++) {
                for (int s = 0; s < 3; s++) {
                    preSum[v][s] = preSum[v - 1][s] + dp[i][v][s];
                }
            }
        }
        if (arr[N - 1] != 0) {
            return dp[N - 1][arr[N - 1]][2];
        } else {
            int ways = 0;
            for (int i = 1; i < 201; i++) {
                ways += dp[N - 1][i][2];
            }
            return ways;
        }
    }

}
