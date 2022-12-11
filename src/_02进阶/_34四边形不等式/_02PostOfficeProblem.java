package _02进阶._34四边形不等式;

/**
 * 把题目一种提到的，
 * min{左部分累加和，有部分累加和}，定义为S(N-1)，也就是说：
 * S(N-1)：在arr[0...N-1]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * 现在要求返回一个长度为N的s数组，
 * s[i]=在arr[0...i]范围上，做最优划分所得到的min{左部分累加和，右部分累加和}的最大值
 * 得到整个s数组的过程，做到时间复杂度O(N)
 * Created by huangjunyi on 2022/12/10.
 */
public class _02PostOfficeProblem {

    public static int[] splitArray(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        int N = arr.length;
        int[] res = new int[N];
        res[0] = 0;
        int leftSum = 0; // 左部分累加和
        int rightSum = arr[0]; // 右部分累加和
        int bestSplit = -1; // 最优划分位置，一开始是0~0范围，肯定是左边一个数也没有，右边有一个数，所以切在-1位置
        for (int range = 1; range < N; range++) {
            rightSum += arr[range]; // 范围扩大了
            while (bestSplit + 1 < range) {
                int before = Math.min(leftSum, rightSum); // 切分位置不动，min{左部分累加和，右部分累加和}
                int after = Math.min(leftSum + arr[bestSplit + 1], rightSum - arr[bestSplit + 1]); // 切分位置往后挪一位, min{左部分累加和，右部分累加和}
                if (after >= before) { // 切分位置往后挪一位，结果更好
                    leftSum = leftSum + arr[bestSplit + 1];
                    rightSum = rightSum - arr[bestSplit + 1];
                    bestSplit++; // 切分位置往后挪一位，结果更好，就往右挪了
                } else {
                    break;
                }
            }
            res[range] = Math.min(leftSum, rightSum);
        }
        return res;
    }

}
