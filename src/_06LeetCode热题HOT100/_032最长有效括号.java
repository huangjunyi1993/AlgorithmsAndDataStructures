package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/longest-valid-parentheses/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _032最长有效括号 {
    class Solution {
        public int longestValidParentheses(String s) {
            char[] chs = s.toCharArray();
            int N = chs.length;
            int res = 0;
            // dp[i] 以i括号结尾，最长的有效长度
            int[] dp = new int[N];
            for (int i = 1; i < N; i++) {
                // 只有是右括号才结算
                if (chs[i] == ')') {
                    // pre 越过i-1上记录的长度，看pre位置是否是左括号
                    int pre = i - dp[i - 1] - 1;
                    if (pre >= 0 && chs[pre] == '(') {
                        // 记录长度 i-1的长度 + 2，然后看能否再和前面续上
                        dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                        res = Math.max(res, dp[i]);
                    }
                }
            }
            return res;
        }
    }
}
