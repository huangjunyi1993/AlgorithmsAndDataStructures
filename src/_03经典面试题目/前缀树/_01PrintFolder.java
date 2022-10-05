package _03经典面试题目.前缀树;

import java.util.TreeMap;

/**
 * 给你一个字符串类型的数组arr，譬如:
 * String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" };
 * 把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:
 * a
 *  b
 *   c
 *  d
 *   e
 * b
 *  st
 * d
 * 同一级的需要按字母顺序排列不能乱。
 * Created by huangjunyi on 2022/9/25.
 */
public class _01PrintFolder {

    private static class Node {
        private String path;
        private TreeMap<String, Node> nexts;

        public Node(String path) {
            this.path = path;
            nexts = new TreeMap<>();
        }
    }

    public static void process(String[] arr) {
        if (arr == null || arr.length == 0) return;
        Node head = buildTree(arr);
        printTree(head, 0);
    }

    private static void printTree(Node cur, int level) {
        if (level != 0) {
            System.out.println(getPreBlank(level) + cur.path);
        }
        for (Node node : cur.nexts.values()) {
            printTree(node, level + 1);
        }
    }

    private static String getPreBlank(int level) {
        String blank = "";
        for (int i = 0; i < level; i++) {
            blank += "  ";
        }
        return blank;
    }

    private static Node buildTree(String[] arr) {
        Node head = new Node("");
        for (String str : arr) {
            String[] strs = str.split("\\\\");
            Node cur = head;
            for (String s : strs) {
                if (cur.nexts.containsKey(s)) {
                    cur = cur.nexts.get(s);
                } else {
                    cur.nexts.put(s, new Node(s));
                    cur = cur.nexts.get(s);
                }
            }
        }
        return head;
    }

    public static void main(String[] args) {
        String[] arr = { "b\\st", "d\\", "a\\d\\e", "a\\b\\c" };
        process(arr);
    }

}
