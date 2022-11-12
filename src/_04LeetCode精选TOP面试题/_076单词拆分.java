package _04LeetCode精选TOP面试题;

import java.util.List;

/**
 * https://leetcode.cn/problems/word-break/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/2.
 */
public class _076单词拆分 {
    /**
     * 前缀树 + 动态规划（从左往右尝试模型）
     */
    static class Solution {
        private static class Node {
            boolean end;
            Node[] nexts;

            public Node(boolean end) {
                this.end = end;
                nexts = new Node[26];
            }
        }
        public boolean wordBreak(String s, List<String> wordDict) {
            // 构建前缀树
            Node root = buildTree(wordDict);
            char[] chs = s.toCharArray();
            int N = chs.length;
            /*
            dp[i]
            表示从i开始，往后，是否内匹配成功
             */
            boolean[] dp = new boolean[N + 1];
            dp[N] = true;
            // 从后面往前面填
            for (int i = N - 1; i >= 0; i--) {
                int index = i;
                Node cur = root;
                // 枚举从i开始往后的所有可能性
                while (index < chs.length) {
                    cur = cur.nexts[chs[index] - 'a'];
                    // 如果前缀树没路了，可以提前break，填前一个格子
                    if (cur == null) break;
                    index++;
                    if (cur.end && dp[index]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[0];
        }

        private Node buildTree(List<String> wordDict) {
            Node root = new Node(false);
            for (String s : wordDict) {
                char[] chs = s.toCharArray();
                Node cur = root;
                for (int i = 0; i < chs.length; i++) {
                    if (cur.nexts[chs[i] - 'a'] == null) {
                        cur.nexts[chs[i] - 'a'] = new Node(false);
                    }
                    cur = cur.nexts[chs[i] - 'a'];
                }
                cur.end = true;
            }
            return root;
        }
    }

    /**
     * 前缀树 + 暴露递归（从左往右尝试）
     */
    static class Solution01 {
        private static class Node {
            boolean end;
            Node[] nexts;

            public Node(boolean end) {
                this.end = end;
                nexts = new Node[26];
            }
        }
        public boolean wordBreak(String s, List<String> wordDict) {
            Node root = buildTree(wordDict);
            char[] chs = s.toCharArray();
            // 递归（从左往右尝试模型）
            return process(chs, root, 0);
        }

        private boolean process(char[] chs, Node cur, int index) {
            if (index == chs.length) return true;
            Node copy = cur;
            while (index < chs.length) {
                cur = cur.nexts[chs[index] - 'a'];
                // 如果前缀树已经没有路，不用再枚举，直接跳出循环，向上返回
                if (cur == null) break;
                index++;
                if (cur.end && process(chs, copy, index)) return true;
            }
            return false;
        }

        /**
         * 构建前缀树
         * @param wordDict
         * @return
         */
        private Node buildTree(List<String> wordDict) {
            Node root = new Node(false);
            for (String s : wordDict) {
                char[] chs = s.toCharArray();
                Node cur = root;
                for (int i = 0; i < chs.length; i++) {
                    if (cur.nexts[chs[i] - 'a'] == null) {
                        cur.nexts[chs[i] - 'a'] = new Node(false);
                    }
                    cur = cur.nexts[chs[i] - 'a'];
                }
                cur.end = true;
            }
            return root;
        }
    }
}
