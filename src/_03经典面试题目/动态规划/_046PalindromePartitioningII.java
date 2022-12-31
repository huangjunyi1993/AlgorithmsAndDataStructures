package _03经典面试题目.动态规划;

/**
 * https://leetcode.cn/problems/palindrome-partitioning-ii/
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数 。
 *
 * Created by huangjunyi on 2022/12/31.
 */
public class _046PalindromePartitioningII {
    class Solution {
        public int minCut(String s) {
            char[] chs = s.toCharArray();
            boolean[][] map = createMap(chs);
            // dp[i] 从i~N-1，且切几刀，能使得i~N-1都是回文串
            int[] dp = new int[chs.length + 1];
            dp[chs.length] = 0;
            for (int i = chs.length - 1; i >= 0; i--) {
                if (map[i][chs.length - 1]) {
                    dp[i] = 0;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < chs.length; j++) {
                        // i~j是回文串，j且1刀，然后看j+1~N-1，要切几刀
                        if (map[i][j]) {
                            next = Math.min(next, dp[j + 1] + 1);
                        }
                    }
                    dp[i] = next;
                }

            }
            return dp[0];
        }

        /**
         * 生成一张map表
         * map[i][j]表示i~j是不是回文串
         * @param chs
         * @return
         */
        private boolean[][] createMap(char[] chs) {
            boolean[][] map = new boolean[chs.length][chs.length];
            for (int i = 0; i < chs.length - 1; i++) {
                map[i][i] = true;
                map[i][i + 1] = chs[i] == chs[i + 1];
            }
            map[chs.length - 1][chs.length - 1] = true;
            for (int i = chs.length - 3; i >= 0; i--) {
                for (int j = i + 2; j < chs.length; j++) {
                    map[i][j] = chs[i] == chs[j] && map[i + 1][j - 1];
                }
            }
            return map;
        }
    }
}
