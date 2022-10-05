package _03经典面试题目.动态规划;

/**
 * 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符，
 * 而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持 原来在str2中的顺序，
 * 那么称aim是str1和str2的交错组成。实现一个函数，判断aim是 否是str1和str2交错组成
 *
 * 【举例】 str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成
 * Created by huangjunyi on 2022/10/2.
 */
public class _016StringCross {

    public static boolean isCross(String str1, String str2, String aim) {
        if (str1 == null || str2  == null || aim == null) return false;
        if (aim.length() != str1.length() + str2.length()) return false;

        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] chsA = aim.toCharArray();

        boolean[][] dp = new boolean[chs1.length + 1][chs2.length + 1];
        dp[0][0]  = true;

        for (int i = 1; i <= chs1.length; i++) {
            if (chs1[i - 1] != chsA[i - 1]) break;
            dp[i][0] = true;
        }
        for (int i = 1; i <= chs2.length; i++) {
            if (chs2[i - 1] != chsA[i - 1]) break;
            dp[0][i] = true;
        }

        for (int i = 1; i <= chs1.length; i++) {
            for (int j = 1; j <= chs2.length; j++) {
                if ((chs1[i - 1] == chsA[i + j - 1] && dp[i-1][j]) ||
                        chs2[i - 1] == chsA[i + j - 1] && dp[i][j-1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[chs1.length][chs2.length];
    }

}
