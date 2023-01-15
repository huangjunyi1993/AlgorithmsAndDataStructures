package _03经典面试题目.动态规划;

/**
 * Created by huangjunyi on 2022/10/16.
 */
public class _038DistinctSubseq {

    /**
     * https://leetcode.cn/problems/21dk04/
     */
    public static int numDistinct(String s, String t) {
        char[] chs1 = s.toCharArray();
        char[] chs2 = t.toCharArray();
        int N = s.length();
        int M = t.length();
        /*
        dp[i][j]
        s从0~i有多少个子序列，是t从0~j的值
        1、i位置字符不要 dp[i-1][j]
        2、i位置字符要 并且s[i] == t[j] dp[i-1][j-1]
         */
        int[][] dp = new int[N][M];
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = chs1[i] == chs2[0] ? (dp[i - 1][0] + 1) : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i, M - 1); j++) {
                dp[i][j] = dp[i - 1][j];
                if (chs1[i] == chs2[j]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    /**
     * 给定一个字符串s，求s中有多少个字面值不同的子序列
     */
    public static int distinctSubseqII(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        char[] chs = s.toCharArray();

        /*
        用一个dp表记录结尾字符和不同子序列的个数
        用一个all变量记录所有不同子序列的个数
        每次遍历一个字符，就以该字符结尾，与all中记录的所有子序列拼出新的子序列，然后减去重复的 => all - dp[ch - 'a']
         */

        long[] dp = new long[26];
        long all = 1;
        for (char ch : chs) {
            // 取模时，有减的地方，都+个模数
            long add = (all - dp[ch - 'a'] + 1000000007) % 1000000007;
            dp[ch - 'a'] = (dp[ch - 'a'] + add) % 1000000007;
            all = (all + add) % 1000000007;
        }
        // 取模时，有减的地方，都+个模数
        return (int) ((all - 1 + 1000000007) % 1000000007);
    }

}
