package _01基础._15动态规划;

import com.sun.org.apache.regexp.internal.RE;

/**
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * Created by huangjunyi on 2022/11/27.
 */
public class DynamicProgramming10 {

    /**
     * 动态规划
     */
    class Solution {
        public int longestPalindromeSubseq(String s) {
            char[] chs = s.toCharArray();
            int N = chs.length;
            // l和r两个可变参数，l和r的变化返回都是0~N-1
            int[][] dp = new int[N][N];
            /*
            初始化dp表的两种情况
            剩一个字符
            剩两个字符
            if (l == r) return 1;
            if (l == r - 1) return chs[l] == chs[r] ? 2 : 0;
            */
            dp[N-1][N-1] = 1;
            for (int i = 0; i < N - 1; i++) {
                dp[i][i] = 1;
                dp[i][i + 1] = chs[i] == chs[i + 1] ? 2 : 1;
            }
            for (int l = N - 2; l >= 0; l--) {
                for (int r = l + 2; r < N; r++) {
                    // 不要l字符和r字符
                    int p1 = dp[l + 1][r - 1];
                    // 不要l字符
                    int p2 = dp[l + 1][r];
                    // 不要r字符
                    int p3 = dp[l][r - 1];
                    // l字符和r字符相等，要
                    int p4 = chs[l] == chs[r] ? 2 + dp[l + 1][r - 1] : 0;
                    // 4种情况PK一下
                    dp[l][r] =  Math.max(Math.max(p1, p2), Math.max(p3, p4));
                }
            }
            // return process(chs, 0, chs.length - 1);
            return dp[0][N - 1];
        }
    }


    /**
     * 暴力递归版本
     */
    /*class Solution {
        public int longestPalindromeSubseq(String s) {
            char[] chs = s.toCharArray();
            return process(chs, 0, chs.length - 1);
        }

        private int process(char[] chs, int l, int r) {
            // 剩一个字符
            if (l == r) return 1;
            // 剩两个字符
            if (l == r - 1) return chs[l] == chs[r] ? 2 : 0;
            // 不要l字符和r字符
            int p1 = process(chs, l + 1, r - 1);
            // 不要l字符
            int p2 = process(chs, l + 1, r);
            // 不要r字符
            int p3 = process(chs, l, r - 1);
            // l字符和r字符相等，要
            int p4 = chs[l] == chs[r] ? 2 + process(chs, l + 1, r - 1) : 0;
            // 4种情况PK一下
            return Math.max(Math.max(p1, p2), Math.max(p3, p4));
        }
    }*/
}
