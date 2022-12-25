package _03经典面试题目.动态规划;

/**
 * https://leetcode.cn/problems/interleaving-string/
 *
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 * Created by huangjunyi on 2022/12/24.
 */
public class _042InterleavingString {
    class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            /*
            动态规划：样本对应模型
            dp[i][j]:
            s1中取前i个字符，s2中取前j个字符，能否凑出s3取前i+j个字符时的交错组成

            假设：
            s1中取前i个字符 记为 a
            s2中取前j个字符 记为 b
            s3取前i+j个字符 记为 c

            三种情况：
            1、c的最后一个字符与a的最后一个字符相同
            2、c的最后一个字符与b的最后一个字符相同
            3、c的最后一个字符与a和b的最后一个字符都不相同

            情况3不可能是交错组成
            情况1在dp[i-1][j]为true时是交错组成
            情况2在dp[i][j-1]为true时是交错组成
             */
            if (s1.length() + s2.length() != s3.length()) return false;
            boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
            dp[0][0] = true;
            char[] chs1 = s1.toCharArray();
            char[] chs2 = s2.toCharArray();
            char[] chs3 = s3.toCharArray();
            for (int i = 1; i <= s1.length(); i++) {
                if (chs1[i - 1] == chs3[i - 1]) {
                    dp[i][0] = true;
                } else {
                    break;
                }
            }
            for (int j = 1; j <= s2.length(); j++) {
                if (chs2[j - 1] == chs3[j - 1]) {
                    dp[0][j] = true;
                } else {
                    break;
                }
            }
            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {
                    dp[i][j] = (chs1[i - 1] == chs3[i + j - 1] && dp[i - 1][j])
                            || (chs2[j - 1] == chs3[i + j - 1] && dp[i][j - 1]);
                }
            }
            return dp[s1.length()][s2.length()];
        }
    }
}
