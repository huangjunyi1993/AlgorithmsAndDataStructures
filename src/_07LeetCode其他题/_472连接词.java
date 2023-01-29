package _07LeetCode其他题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.cn/problems/concatenated-words/
 * Created by huangjunyi on 2023/1/22.
 */
public class _472连接词 {
    class Solution {
        private class TrieNode {
            public boolean end;
            public TrieNode[] nexts;

            public TrieNode() {
                end = false;
                nexts = new TrieNode[26];
            }
        }
        private void insert(TrieNode root, char[] chs) {
            TrieNode cur = root;
            for (int i = 0; i < chs.length; i++) {
                int path = chs[i] - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new TrieNode();
                }
                cur = cur.nexts[path];
            }
            cur.end = true;
        }
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            List<String> res = new ArrayList<>();
            if (words == null || words.length < 3) return res;
            // 单词按长度从小到大排序
            Arrays.sort(words, Comparator.comparingInt(String::length));
            TrieNode root = new TrieNode();
            // 遍历每一个单词，看是否能被拆分，能则加入答案，否则加入前缀树
            for (String word : words) {
                // 看是否能被拆分的过程，通过动态规划优化
                char[] chs = word.toCharArray();
                int[] dp = new int[chs.length + 1];
                if (chs.length > 0 && split(chs, root, 0, dp)) {
                    res.add(word);
                } else {
                    insert(root, chs);
                }
            }
            return res;
        }

        private boolean split(char[] chs, TrieNode root, int i, int[] dp) {
            if (dp[i] != 0) return dp[i] == 1;
            boolean res = false;
            if (i == chs.length) return true;
            TrieNode cur = root;
            for (int end = i; end < chs.length; end++) {
                int path = chs[end] - 'a';
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                // 当前位置能切成一个单词，并且后续能被切分
                if (cur.end && split(chs, root, end + 1, dp)){
                    res = true;
                    break;
                }
            }
            dp[i] = res ? 1 : -1;
            return res;
        }
    }
}
