package _03经典面试题目.预处理数组;

/**
 * 【题目】
 * 一群孩子做游戏，现在请你根据游戏得分来发糖果，要求如下：
 * 1、每个孩子不管得分多少，起码分到1个糖果
 * 2、任意两个相邻的孩子之间，得分较多的孩子必须多拿一些糖果
 * 给定一个数组arr代表得分数组，请返回最少需要多少糖果。
 * 例如：arr = [1,2,2]，糖果分配[1,2,1]，即可满足要求且数量最少，所以返回4.
 *
 * 【进阶题目】
 * 原题目中的两个规则不变，再加一条规则：
 * 3、任意两个相邻的孩子之间如果得分相同，糖果数必须相同
 * 给定一个数组arr代表得分数组，返回最少需要多少糖果。
 * 例如：arr = [1,2,2]，糖果分配[1,2,2]，即可满足要求且数量最少，所以返回5.
 *
 * 【要求】
 * 原文题和进阶问题的时间复杂度都为O(N)，空间复杂度O(1)。
 *
 * Created by huangjunyi on 2022/10/15.
 */
public class _06CandyProblem {
}

class Code02_CandyProblem {

    public static int candy(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;

        /*
        通过两个预处理数组求解
        从左往右遍历arr，生成预处理数组left
        当前孩子比前一个孩子分数高，则糖果数+1，分数相等则糖果数相等，分数比前一个孩子小，则糖果数回到1
        从右往左遍历arr，生成预处理数据right
        当前孩子比后一个孩子分数高，则糖果数+1，分数相等则糖果数相等，分数比后一个孩子小，则糖果数回到1
        然后每个位置求left和right中的最大值，放入返回结果res
         */
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else if (arr[i] == arr[i - 1]) {
                left[i] = left[i - 1];
            } else {
                left[i] = 1;
            }
        }
        right[N - 1] = 1;
        for (int i  = N - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;

            } else if (arr[i] == arr[i + 1]) {
                right[i] = right[i + 1];
            } else {
                right[i] = 1;
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res += Math.max(left[i], right[i]);
        }
        return res;
    }

    public static int candy1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex1(arr, 0);
        int res = rightCands(arr, 0, index++);
        int lbase = 1;
        int next = 0;
        int rcands = 0;
        int rbase = 0;
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinIndex1(arr, index - 1);
                rcands = rightCands(arr, index - 1, next++);
                rbase = next - index + 1;
                res += rcands + (rbase > lbase ? -lbase : -rbase);
                lbase = 1;
                index = next;
            } else {
                res += 1;
                lbase = 1;
                index++;
            }
        }
        return res;
    }

    public static int nextMinIndex1(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static int rightCands(int[] arr, int left, int right) {
        int n = right - left + 1;
        return n + n * (n - 1) / 2;
    }

    public static int candy2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int index = nextMinIndex2(arr, 0);
        int[] data = rightCandsAndBase(arr, 0, index++);
        int res = data[0];
        int lbase = 1;
        int same = 1;
        int next = 0;
        while (index != arr.length) {
            if (arr[index] > arr[index - 1]) {
                res += ++lbase;
                same = 1;
                index++;
            } else if (arr[index] < arr[index - 1]) {
                next = nextMinIndex2(arr, index - 1);
                data = rightCandsAndBase(arr, index - 1, next++);
                if (data[1] <= lbase) {
                    res += data[0] - data[1];
                } else {
                    res += -lbase * same + data[0] - data[1] + data[1] * same;
                }
                index = next;
                lbase = 1;
                same = 1;
            } else {
                res += lbase;
                same++;
                index++;
            }
        }
        return res;
    }

    public static int nextMinIndex2(int[] arr, int start) {
        for (int i = start; i != arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static int[] rightCandsAndBase(int[] arr, int left, int right) {
        int base = 1;
        int cands = 1;
        for (int i = right - 1; i >= left; i--) {
            if (arr[i] == arr[i + 1]) {
                cands += base;
            } else {
                cands += ++base;
            }
        }
        return new int[] { cands, base };
    }

    public static void main(String[] args) {
        int[] test1 = { 3, 0, 5, 5, 4, 4, 0 };
        System.out.println(candy1(test1));

        int[] test2 = { 3, 0, 5, 5, 4, 4, 0 };
        System.out.println(candy2(test2));
    }

}

