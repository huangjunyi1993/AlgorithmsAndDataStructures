package _04LeetCode精选TOP面试题.动态规划;

/**
 * https://leetcode.cn/problems/wildcard-matching/
 *
 * Created by huangjunyi on 2022/10/28.
 */
public class _030通配符匹配 {

    /**
     * 斜率优化后的最终版本
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        int N = str.length;
        int M = pat.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int i = M - 1; i >= 0; i--) {
            if (pat[i] == '*' && dp[N][i + 1]) {
                dp[N][i] = pat[i] == '*' && dp[N][i + 1];
            }
        }
        for (int si = N - 1; si >= 0; si--) {
            for (int pi = M - 1; pi >= 0; pi--) {
                if (pat[pi] != '?' && pat[pi] != '*') {
                    dp[si][pi] = pat[pi] == str[si] && dp[si + 1][pi + 1];
                    continue;
                }
                if (pat[pi] == '?') {
                    dp[si][pi] = dp[si + 1][pi + 1];
                }
                /*
                比如pi=7位置是*，观察si=4和si=5
                dp[4][7]，dp[5][7]有这样的关系
                dp[5][7]表示str串从5位置开始与pat串从7位置开始匹配，而pat串7位置是*，
                所以当计算dp[5][7]时，已经计算了dp[5][8] + dp[6][8] + dp[7][8] + ...
                dp[4][7]表示str串从4位置开始与pat串从7位置开始匹配，而pat串7位置是*，
                所以当计算dp[4][7]是，则要计算dp[4][8] + dp[5][8] + dp[6][8] + dp[7][8] + ...
                而dp[5][8] + dp[6][8] + dp[7][8] + ...这一部分，就是dp[5][7]，在计算dp[5][7]已经算过
                所以计算dp[4][8]可以优化为dp[4][8] + dp[5][7]
                 */
                if (pat[pi] == '*') {
                    dp[si][pi] = dp[si + 1][pi] || dp[si][pi + 1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 看着暴力递归版本改成动态规划
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        int N = str.length;
        int M = pat.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int i = M - 1; i >= 0; i--) {
            if (pat[i] == '*' && dp[N][i + 1]) {
                dp[N][i] = pat[i] == '*' && dp[N][i + 1];
            }
        }
        for (int si = N - 1; si >= 0; si--) {
            for (int pi = M - 1; pi >= 0; pi--) {
                if (pat[pi] != '?' && pat[pi] != '*') {
                    dp[si][pi] = pat[pi] == str[si] && dp[si + 1][pi + 1];
                    continue;
                }
                if (pat[pi] == '?') {
                    dp[si][pi] = dp[si + 1][pi + 1];
                }
                if (pat[pi] == '*') {
                    for (int i = si; i <= str.length; i++) {
                        if (dp[i][pi + 1]) {
                            dp[si][pi] = true;
                            continue;
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 暴力递归版本
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p) {
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        return process1(str, pat, 0, 0);
    }

    /**
     * str串从si位置开始，与pat串从pi位置开始，往后进行匹配，是否匹配成功
     * @param str str串
     * @param pat pat串
     * @param si str开始匹配的位置
     * @param pi pat开始匹配的位置
     * @return
     */
    private boolean process1(char[] str, char[] pat, int si, int pi) {
        // base case
        if (si == str.length) {
            if (pi == str.length) {
                return true;
            }
            if (pat[pi] == '*' && process1(str, pat, si, pi +1)) {
                return true;
            }
            return false;
        }
        if (pi == str.length) {
            return false;
        }

        // 如果不是?也不是*，则字符必须相等且后续的匹配也成功，才返回true
        if (pat[pi] != '?' && pat[pi] != '*') {
            return pat[pi] == str[si] && process1(str, pat, si + 1, pi + 1);
        }
        // 是?，默认当前字符匹配，是否返回true看后续递归
        if (pat[pi] == '?') {
            return process1(str, pat, si + 1, pi + 1);
        }
        // 是*，因为*可以转变为一切字符串包括空串，所以要从当前si开始一直往后试
        if (pat[pi] == '*') {
            for (int i = si; i <= str.length; i++) {
                if (process1(str, pat, i, pi + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
