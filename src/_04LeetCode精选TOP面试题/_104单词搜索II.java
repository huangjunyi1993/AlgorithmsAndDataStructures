package _04LeetCode精选TOP面试题;

import _03经典面试题目.前缀树._03WordSearch;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-search-ii/?favorite=2ckc81c
 * 经典面试题13
 * Created by huangjunyi on 2022/11/5.
 */
public class _104单词搜索II {
    class Solution {

        private class Node {
            public int pass; // 是否还需要往下收集，0代表已经收集过，不需要往下收集
            public int end;  // 是否某个单词结尾
            public Node[] nexts;

            public Node() {
                nexts = new Node[26];
                pass = 0;
                end = 0;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            if (board == null ||
                    board.length == 0 ||
                    board[0] == null ||
                    board[0].length == 0 ||
                    words == null ||
                    words.length == 0) {
                return null;
            }

            // 建前缀树
            Node head = new Node();
            Set<String> set = new HashSet<>();
            for (String word : words) {
                if (set.contains(word)) continue;
                fillTree(head, word);
                set.add(word);
            }

            // 遍历每一个格子作为起点
            LinkedList<Character> path = new LinkedList<>();
            ArrayList<String> res = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    process(board, i, j, head, path, res);
                }
            }

            return res;
        }
        private int process(char[][] board, int i, int j, Node cur, LinkedList<Character> path, ArrayList<String> res) {
            // 碰到0，回头路
            if (board[i][j] == 0) return 0;

            // 记录当前格子原来的值，深度优先遍历返回时还原
            char ch = board[i][j];

            // 没有路了
            if (cur.nexts[ch - 'a'] == null || cur.nexts[ch - 'a'].pass <= 0) return 0;

            int fix = 0; // 收集到几个答案
            cur = cur.nexts[ch - 'a'];
            path.addLast(ch);

            // 找到一个答案，收集
            if (cur.end > 0) {
                fix++;
                cur.end--;
                res.add(generateWord(path));
            }

            // 递归上下左右，四个方向
            board[i][j] = 0;
            if (i - 1 >= 0) {
                fix += process(board, i - 1, j, cur, path, res);
            }
            if (i + 1 < board.length) {
                fix += process(board, i + 1, j, cur, path, res);
            }
            if (j - 1 >= 0) {
                fix += process(board, i, j - 1, cur, path, res);
            }
            if (j + 1 < board[0].length) {
                fix += process(board, i, j + 1, cur, path, res);
            }

            // 深度优先遍历返回，清除现场
            path.pollLast();
            cur.pass -= fix;
            board[i][j] = ch;
            return fix;
        }

        private String generateWord(LinkedList<Character> path) {
            StringBuilder sb = new StringBuilder(path.size());
            for (Character character : path) {
                sb.append(character);
            }
            return sb.toString();
        }

        private void fillTree(Node head, String word) {
            head.pass++;
            Node node = head;
            char[] chs = word.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                if (node.nexts[chs[i] - 'a'] == null) {
                    node.nexts[chs[i] - 'a'] = new Node();
                }
                node = node.nexts[chs[i] - 'a'];
                node.pass++;
            }
            node.end++;
        }
    }
}
