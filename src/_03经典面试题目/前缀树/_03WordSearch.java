package _03经典面试题目.前缀树;

import java.util.*;

/**
 * 给定一个字符类型的二维数组board，和一个字符串组成的列表words。
 * 可以从board任何位置出发，每一步可以走向上、下、左、右，四个方向，
 * 但是一条路径已经走过的位置，不能重复走。
 * 返回words哪些单词可以被走出来。
 * 例子
 * board = [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 *
 * Created by huangjunyi on 2022/10/5.
 */
public class _03WordSearch {

    private static class Node {
        public int pass;
        public int end;
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

        Node head = new Node();
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (set.contains(word)) continue;
            fillTree(head, word);
            set.add(word);
        }

        LinkedList<Character> path = new LinkedList<>();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                process(board, i, j, head, path, res);
            }
        }

        return res;
    }

    private void process(char[][] board, int i, int j, Node cur, LinkedList<Character> path, ArrayList<String> res) {
        if (board[i][j] == 0) return;
        char ch = board[i][j];
        if (cur.nexts[ch - 'a'] == null || cur.nexts[ch - 'a'].pass <= 0) return;
        int fix = 0;
        cur = cur.nexts[ch - 'a'];
        path.addLast(ch);
        if (cur.end > 0) {
            fix++;
            cur.end--;
            res.add(generateWord(path));
        }

        board[i][j] = 0;
        if (i - 1 >= 0) {
            process(board, i - 1, j, cur, path, res);
        }
        if (i + 1 < board.length) {
            process(board, i + 1, j, cur, path, res);
        }
        if (j - 1 >= 0) {
            process(board, i, j - 1, cur, path, res);
        }
        if (j + 1 < board[0].length) {
            process(board, i, j + 1, cur, path, res);
        }


        path.pollLast();
        cur.pass -= fix;
        board[i][j] = ch;
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
