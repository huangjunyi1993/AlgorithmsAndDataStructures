package _03经典面试题目.动态规划;

/**
 * https://leetcode.cn/problems/boolean-evaluation-lcci/
 *
 * 给定一个布尔表达式和一个期望的布尔结果 result，
 * 布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 *
 * Created by huangjunyi on 2022/12/30.
 */
public class _044BooleanEvaluationLcci {

    class Solution {

        private class Info {
            int t; // 为true的方法数
            int f; // 为false的方法数

            public Info(int t, int f) {
                this.t = t;
                this.f = f;
            }
        }

        public int countEval(String s, int result) {
            char[] chs = s.toCharArray();
            Info[][] dp = new Info[chs.length][chs.length];
            Info info = process(chs, 0, chs.length - 1, dp);
            return result == 1 ? info.t : info.f;
        }

        private Info process(char[] chs, int L, int R, Info[][] dp) {
            if (dp[L][R] != null) {
                return dp[L][R];
            }
            if (L == R) {
                Info info = chs[L] == '1' ? new Info(1, 0) : new Info(0, 1);
                dp[L][R] = info;
                return info;
            }
            int t = 0;
            int f = 0;
            /*
            遍历区间范围内的每一个逻辑符号，
            作为当前区间范围内的最后一个逻辑符号
            左边递归收一个答案
            右边递归收一个答案
            然后根据当前这个逻辑符号，计算方法数，累加到t和f上
             */
            for (int i = L + 1; i <= R - 1; i += 2) {
                Info leftInfo = process(chs, L, i - 1, dp);
                Info rightInfo = process(chs, i + 1, R, dp);
                switch (chs[i]) {
                    case '&' :
                        t += leftInfo.t * rightInfo.t;
                        f += leftInfo.f * rightInfo.f + leftInfo.f * rightInfo.t + leftInfo.t * rightInfo.f;
                        break;
                    case '|':
                        t += leftInfo.t * rightInfo.t + leftInfo.f * rightInfo.t + leftInfo.t * rightInfo.f;
                        f += leftInfo.f * rightInfo.f;
                        break;
                    case '^':
                        t += leftInfo.f * rightInfo.t + leftInfo.t * rightInfo.f;
                        f += leftInfo.t * rightInfo.t + leftInfo.f * rightInfo.f;
                        break;
                }
            }
            Info info = new Info(t, f);
            dp[L][R] = info;
            return info;
        }
    }

}
