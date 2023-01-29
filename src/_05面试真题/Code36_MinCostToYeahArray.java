package _05面试真题;

import java.util.Arrays;

/**
 * 来自360笔试
 * 给定一个正数数组arr，长度为n，下标0~n-1
 * arr中的0、n-1位置不需要达标，它们分别是最左、最右的位置
 * 中间位置i需要达标，达标的条件是 : arr[i-1] > arr[i] 或者 arr[i+1] > arr[i]哪个都可以
 * 你每一步可以进行如下操作：对任何位置的数让其-1
 * 你的目的是让arr[1~n-2]都达标，这时arr称之为yeah！数组
 * 返回至少要多少步可以让arr变成yeah！数组
 * 数据规模 : 数组长度 <= 10000，数组中的值<=500
 * Created by huangjunyi on 2023/1/20.
 */
public class Code36_MinCostToYeahArray {

    /**
     * 贪心（最优解）
     * 因为当满足题目要求是，数组中间是不可能有尖的
     * 所以也就是遍历每个位置，作为坡底，把两边的数调成合规，需要付出的代价，取最小值
     * 那么，只要求两个数组：
     * 1. leftCostSum左代价累加和数组，以arr[0]为山顶，一直往下降，代价累加和记录到leftCostSum，但不需要记录leftCostSum[arr.length - 1]
     * 2. rightCostSum右代价累加和数组，以arr[arr.length - 1]为山顶，一直往下降，代价累加和记录到rightCostSum，但不需要记录leftCostSum[0]
     *
     * 然后再遍历一次
     * min(leftCostSum[0]+rightCostSum[1], leftCostSum[1]+rightCostSum[2], leftCostSum[2]+rightCostSum[3], ...)
     */
    public static int yeah(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        int[] leftCostSum = new int[arr.length];
        int[] rightCostSum = new int[arr.length];

        int pre = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            if (pre <= arr[i]) {
               leftCostSum[i] = leftCostSum[i - 1] + (arr[i] - pre + 1);
               pre = pre - 1;
            } else {
                leftCostSum[i] = leftCostSum[i - 1];
                pre = arr[i];
            }
        }

        pre = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (pre <= arr[i]) {
                rightCostSum[i] = rightCostSum[i + 1] + (arr[i] - pre + 1);
                pre = pre - 1;
            } else {
                rightCostSum[i] = rightCostSum[i + 1];
                pre = arr[i];
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            minCost = Math.min(minCost, leftCostSum[i] + rightCostSum[i + 1]);
        }
        return minCost;
    }

    /**
     * 递归尝试
     */
    public static int minCost(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        // 1.数组归一化，统一加一个N-min，使得尝试的时候，每个pre的尝试，只要试到0就可以
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] += (arr.length - min);
        }
        return process(arr, 1, arr[0], true);
    }

    /**
     * arr的index位置，前一个数是pre，是否达标的状态记录在ok
     * 尝试当前位置每一个可以变化的值，使得arr[index...]达标
     * 返回最小代价
     * @param arr 数组
     * @param index 数组下标
     * @param pre 前一个数
     * @param ok 前一个数是否达标
     * @return
     */
    private static int process(int[] arr, int index, int pre, boolean ok) {
        // base case：来到末尾，前面ok了，当前把前面搞ok了，那么返回代价0，否则代价无限大
        if (index == arr.length - 1) return ok || arr[index] > pre ? 0 : Integer.MAX_VALUE;
        int cost = Integer.MAX_VALUE;
        if (ok) {
            // 前面被搞定了，下一步的pre，可从arr[index]试到0，是否ok则取决于与cur与pre的关系
            for (int cur = arr[index]; cur >= 0; cur--) {
                int next = process(arr, index + 1, cur, pre > cur);
                if (next != Integer.MAX_VALUE) {
                    cost = Math.min(cost, arr[index] - cur + next);
                }
            }
        } else {
            // 前面没搞定了，现在要搞定，cur必须大于pre
            for (int cur = arr[index]; cur > pre; cur--) {
                int next = process(arr, index + 1, cur, false);
                if (next != Integer.MAX_VALUE) {
                    cost = Math.min(cost, arr[index] - cur + next);
                }
            }
        }
        return cost;
    }

    /**
     * 动态规划
     */
    public static int minCostDP(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        // 1.数组归一化，统一加一个N-min，使得尝试的时候，每个pre的尝试，只要试到0就可以
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] += (arr.length - min);
        }
        // 根据数据量创建dp表
        // dp[index][false=0/true=1][pre] = 代价
        int[][][] dp = new int[arr.length][2][];
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = new int[arr[i - 1] + 1];
            dp[i][1] = new int[arr[i - 1] + 1];
            Arrays.fill(dp[i][0], Integer.MAX_VALUE);
            Arrays.fill(dp[i][1], Integer.MAX_VALUE);
        }
        // 根据 base case 初始化dp表
        // if (index == arr.length - 1) return ok || arr[index] > pre ? 0 : Integer.MAX_VALUE;
        for (int pre = 0; pre <= arr[arr.length - 2]; pre++) {
            dp[arr.length - 1][0][pre] = arr[arr.length - 1] > pre ? 0 : Integer.MAX_VALUE;
            dp[arr.length - 1][1][pre] = 0;
        }
        // 根据递归的依赖关系填表
        for (int index = arr.length - 2; index >= 1; index--) {
            for (int pre = 0; pre <= arr[index - 1]; pre++) {
                for (int cur = arr[index]; cur >= 0; cur--) {
                    int next = dp[index + 1][pre > cur ? 1 : 0][cur];
                    if (next != Integer.MAX_VALUE) {
                        dp[index][1][pre] = Math.min(dp[index][1][pre], arr[index] - cur + next);
                    }
                }
                for (int cur = arr[index]; cur > pre; cur--) {
                    int next = dp[index + 1][0][cur];
                    if (next != Integer.MAX_VALUE) {
                        dp[index][0][pre] = Math.min(dp[index][0][pre], arr[index] - cur + next);
                    }
                }
            }
        }
        return dp[1][1][arr[0]];
    }

    // 为了测试
    public static int[] randomArray(int len, int v) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int len = 7;
        int v = 10;
        int testTime = 100;
        System.out.println("==========");
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * len) + 1;
            int[] arr = randomArray(n, v);
            int[] arr0 = copyArray(arr);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int ans0 = yeah(arr0);
            int ans1 = minCost(arr1);
            int ans2 = minCostDP(arr2);
            if (ans0 != ans1 || ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("功能测试结束");
        System.out.println("==========");

    }


    /*// minCost2动态规划 + 枚举优化
	// 改出的这个版本，需要一些技巧，但很可惜不是最优解
	// 虽然不是最优解，也足以通过100%的case了，
	// 这种技法的练习，非常有意义
	public static int minCost3(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int min = INVALID;
		for (int num : arr) {
			min = Math.min(min, num);
		}
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			arr[i] += n - min;
		}
		int[][][] dp = new int[n][2][];
		for (int i = 1; i < n; i++) {
			dp[i][0] = new int[arr[i - 1] + 1];
			dp[i][1] = new int[arr[i - 1] + 1];
		}
		for (int p = 0; p <= arr[n - 2]; p++) {
			dp[n - 1][0][p] = p < arr[n - 1] ? 0 : INVALID;
		}
		int[][] best = best(dp, n - 1, arr[n - 2]);
		for (int i = n - 2; i >= 1; i--) {
			for (int p = 0; p <= arr[i - 1]; p++) {
				if (arr[i] < p) {
					dp[i][1][p] = best[1][arr[i]];
				} else {
					dp[i][1][p] = Math.min(best[0][p], p > 0 ? best[1][p - 1] : INVALID);
				}
				dp[i][0][p] = arr[i] <= p ? INVALID : best[0][p + 1];
			}
			best = best(dp, i, arr[i - 1]);
		}
		return dp[1][1][arr[0]];
	}

	public static int[][] best(int[][][] dp, int i, int v) {
		int[][] best = new int[2][v + 1];
		best[0][v] = dp[i][0][v];
		for (int p = v - 1; p >= 0; p--) {
			best[0][p] = dp[i][0][p] == INVALID ? INVALID : v - p + dp[i][0][p];
			best[0][p] = Math.min(best[0][p], best[0][p + 1]);
		}
		best[1][0] = dp[i][1][0] == INVALID ? INVALID : v + dp[i][1][0];
		for (int p = 1; p <= v; p++) {
			best[1][p] = dp[i][1][p] == INVALID ? INVALID : v - p + dp[i][1][p];
			best[1][p] = Math.min(best[1][p], best[1][p - 1]);
		}
		return best;
	}*/

}
