package _03经典面试题目.动态规划;

/**
 * 判定一个由[a-z]字符构成的字符串和一个包含'.'和''通配符的字符串是否匹配。
 * 通配符'.'匹配任意单一字符,''匹配任意多个字符包括0个字符。 字符串长度不会超过100，字符串不为空。
 *
 * 输入描述:
 * 字符串 str 和包含通配符的字符串 pattern。
 * 1 <= 字符串长度 <= 100
 *
 * 输出描述: true 表示匹配，false 表示不匹配
 *
 * Created by huangjunyi on 2022/10/9.
 */
public class _34RegularExpressionMatch {

    public static boolean match01(String str, String pattern) {
        if (str == null || pattern == null) return false;
        char[] chs = str.toCharArray();
        char[] exp = pattern.toCharArray();
        int N = chs.length;
        int M = exp.length;
        return isValid(chs, exp) && process(chs, exp, N, M, 0, 0);
    }

    private static boolean process(char[] chs, char[] exp, int N, int M, int si, int ei) {
        // 如果exp已经耗尽，只有在chs也耗尽时，返回true，其余返回false
        if (ei == M) {
            return si == N ? true : false;
        }

        // exp下一个位置不是*（包括下一个位置是尽头），正常的匹配流程
        if (ei + 1 == M || exp[ei + 1] != '*') {
            return si != N &&
                    (chs[si] == exp[ei] || exp[ei] == '.') &&
                    process(chs, exp, N, M, si + 1, ei + 1);
        }

        // 如果exp下一个位置是星，要一直尝试，直到两边字符不能匹配，或者chs耗尽
        while (si != N && (chs[si] == exp[ei] || exp[ei] == '.')) {
            if (process(chs, exp, N, M, si, ei + 2)) {
                return true;
            }
            si++;
        }

        // 走到这里，表示si已经走到了chs中与exp的ei不匹配的字符位置，或者chs耗尽
        return process(chs, exp, N, M, si, ei + 2);
    }

    /**
     * 有效性检查，chs中不能有.和*，exp中不能有连续的*
     * @param chs
     * @param exp
     * @return
     */
    private static boolean isValid(char[] chs, char[] exp) {
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '.' || chs[i] == '*') return false;
        }
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '*' && (i == 0 || exp[i - 1] == '*')) return false;
        }
        return true;
    }

    /**
     * 递归改成动态规划
     * @param str
     * @param pattern
     * @return
     */
    public static boolean match02(String str, String pattern) {
        if (str == null || pattern == null) return false;
        char[] chs = str.toCharArray();
        char[] exp = pattern.toCharArray();
        int N = chs.length;
        int M = exp.length;
        if (!isValid(chs, exp)) return false;
        boolean[][] dp = new boolean[N + 1][M + 1];

        /*
        dp初始化
        因为dp[i][j] 依赖到 dp[i + 1][j + 1]，以及dp[i...][j + 2]
        所有要把倒数两列和倒数第一行填好
         */
        dp[N][M] = true;
        if (N > 0 && M > 0) dp[N - 1][M - 1] = (chs[N - 1] == exp[M - 1] || exp[M - 1] == '.') ? true : false;
        for (int i = M - 2; i >= 0; i -= 2) {
            if (exp[i] != '*' && exp[i + 1] == '*') {
                dp[N][i] = true;
            } else {
                break;
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                if (exp[j + 1] != '*') {
                    dp[i][j] = dp[i + 1][j + 1] && (chs[i] == exp[j] || exp[j] == '.');
                } else {
                    int si = i;
                    int ei = j;
                    while (si != N && (chs[si] == exp[ei] || exp[ei] == '.')) {
                        if (dp[i][ei + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (!dp[i][j]) dp[i][j] = dp[i][j + 2];
                }
            }
        }

        return dp[0][0];
    }


}
