package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/decode-ways/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _051解码方法 {

    public int numDecodings(String s) {
        /*
        根据暴力递归改动态规划
         */
        if (s == null || s.length() == 0) return 0;
        char[] chs = s.toCharArray();
        int N = chs.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (chs[i] == '0') continue;
            int ways = dp[i + 1];
            if (i + 1 == chs.length) {
                dp[i] = ways;
                continue;
            }
            int num = (chs[i] - '0') * 10 + (chs[i + 1] - '0');
            if (num <= 26) {
                ways += dp[i + 2];
            }
            dp[i] = ways;
        }
        return dp[0];
    }

    /**
     * 从左往右的暴力递归尝试模型
     * @param s
     * @return
     */
    public int numDecodings1(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chs = s.toCharArray();
        return process(chs, 0);
    }

    private int process(char[] chs, int index) {
        if (index == chs.length) return 1;
        if (chs[index] == '0') return 0;
        int ways = process(chs, index + 1);
        if (index + 1 == chs.length) return ways;
        int num = (chs[index] - '0') * 10 + (chs[index + 1] - '0');
        if (num <= 26) {
            ways += process(chs, index + 2);
        }
        return ways;
    }
}
