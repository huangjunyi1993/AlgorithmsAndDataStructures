package _04LeetCode精选TOP面试题.动态规划;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/palindrome-partitioning/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/31.
 */
public class _072分割回文串 {
    public List<List<String>> partition(String s) {
        /*
        动态规划 + 深度优先遍历

        生成一张dp表
        dp[i][j]表示i~j范围是否是回文
        方便process方法直接查询
         */
        boolean[][] dp = initDp(s);
        List<List<String>> res = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        process(s, 0, path, res, dp);
        return res;
    }

    private void process(String s, int index, LinkedList<String> path, List<List<String>> res, boolean[][] dp) {
        if (index == s.length()) {
            res.add(copy(path));
        } else {
            // 深度优先遍历 + 清理现场
            for (int end = index; end < s.length(); end++) {
                if (dp[index][end]) {
                    path.addLast(s.substring(index, end + 1));
                    process(s, end + 1, path, res, dp);
                    path.pollLast();
                }
            }
        }
    }

    private List<String> copy(LinkedList<String> path) {
        List<String> list = new ArrayList<>();
        list.addAll(path);
        return list;
    }

    private boolean[][] initDp(String s) {
        char[] chs = s.toCharArray();
        int N = s.length();
        /*
        先填两对角线
        然后从下往上，从左往右填
        判断左右两侧字符是否相等，中间是否回文
        每个格子，依赖左下角格子
         */
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = true;
            if (chs[i] == chs[i + 1]) dp[i][i + 1] = true;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = (chs[i] == chs[j]) && dp[i + 1][j - 1];
            }
        }
        dp[N - 1][N - 1] = true;
        return dp;
    }
}
