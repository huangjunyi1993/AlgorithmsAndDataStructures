package _04LeetCode精选TOP面试题.动态规划;

/**
 * https://leetcode.cn/problems/regular-expression-matching/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _008正则表达式匹配 {
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        int[][] dp = new int[str.length + 1][pat.length + 1];
        return process(str, 0, pat, 0, dp);
    }
    private boolean process(char[] str, int si, char[] pat, int pi, int[][] dp) {
        if (dp[si][pi] != 0) return dp[si][pi] == 1;
        // 如果s串已到结尾，判断是否为true并返回
        if (si == str.length) {
            // p串也到结尾，返回true
            if (pi == pat.length) {
                dp[si][pi] = 1;
                return true;
            }
            // 如果p串没到结尾，看是否跟的都是x*，比如a*b*c*，那也为true
            if (pi + 1 < pat.length && pat[pi + 1] == '*' && process(str, si, pat, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            // 否则为false
            dp[si][pi] = 2;
            return false;
        }
        // s串未到结尾，p串先到结尾，返回false
        if (pi == pat.length) {
            dp[si][pi] = 2;
            return false;
        }
        // pi+1 不是 * 的情况
        if (pi + 1 == pat.length || pat[pi + 1] != '*') {
            if ((str[si] == pat[pi] || pat[pi] == '.') && process(str, si + 1, pat, pi + 1, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            dp[si][pi] = 2;
            return false;
        }

        // 下面处理 pi+1 是 * 的情况
        if (process(str, si, pat, pi + 2, dp)) {
            dp[si][pi] = 1;
            return true;
        }
        while (si != str.length && (str[si] == pat[pi] || pat[pi] == '.')) {
            if (process(str, si + 1, pat, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            si++;
        }
        dp[si][pi] = 2;
        return false;
    }
}
