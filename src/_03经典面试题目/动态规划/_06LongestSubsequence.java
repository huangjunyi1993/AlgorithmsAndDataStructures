package _03经典面试题目.动态规划;

/**
 * 求最长递增子序列的长度，O(logn * n)解法
 * Created by huangjunyi on 2022/9/25.
 */
public class _06LongestSubsequence {

    public static int getSubSeqLen(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        /*
        常规解法：
        申请一个一维数组dp
        填dp[i]时，往i左边遍历dp表，看那个下标对应的字符，比arr[i]小，
        找到则dp表中该下标的数加1填到dp[i]
        时间复杂度O(n^2)

        更快的解法：
        一个ends数组
        ends[i]表示长度为i+1的递增子序列中结尾数字最小的数字
        遍历arr
        每次找到ends中大于arr[i]的最左侧的数，更新ends
        如果ends中的所有数都比arr[i]小
        则ends往右括一位（用一个变量right记录当前ends数组的有效范围，0~right）
        最后返回ends[right - 1]
         */
        int right = 0;
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (ends[mid] < arr[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            ends[l] = arr[i];
            if (l > right) right = l;
        }
        return right + 1;
    }

    public static void main(String[] args) {
        System.out.println(getSubSeqLen(new int[] {2,1,7,2,3}));
    }

}
