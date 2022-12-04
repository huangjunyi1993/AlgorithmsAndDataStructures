package _02进阶._32AC自动机;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 * Created by huangjunyi on 2022/9/17.
 */
public class ACAutomation {

    private static class Node {
        private String end; // 如果这个结点是某个字符串的结尾，则end不为null
        private boolean visited; // 访问到end不为null，收集答案后，下次再到这个结点，visited就为true，就不重复收集了
        private Node fail; // fail指针
        private Node[] nexts; // 路径数组

        public Node() {
            end = null;
            visited = false;
            fail = null;
            nexts = new Node[26];
        }
    }

    private Node root;

    public ACAutomation() {
        root = new Node();
    }

    public void insert(String str) {
        //以构建前缀树的方式插入字符串
        char[] chars = str.toCharArray();
        Node cur = this.root;
        for (int i = 0; i < chars.length; i++) {
            // 没路就新建，有路就复用
            int index = chars[i] - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new Node();
            }
            cur = cur.nexts[index];
        }
        //当前节点时该字符串的结尾字符，设置当前节点的end属性为当前字符串
        cur.end = str;
    }

    public void build() {
        //以宽度优先遍历的方式为每个节点设置fail指针
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        Node cur = null;
        Node curFail = null;
        while (!queue.isEmpty()) {
            //弹出当前节点，为当前节点的每个子结点设置fail指针
            cur = queue.poll();
            //遍历每个子节点，为它们设置fail指针
            for (int i = 0; i < cur.nexts.length; i++) {
                Node next = cur.nexts[i];
                if (next == null) continue;
                //当前父节点的fail指针
                curFail = cur.fail;
                //如果当前fail指针没有走向i的路，继续找，要么找到，要么一直到null
                while (curFail != null && curFail.nexts[i] == null) {
                    curFail = curFail.fail;
                }
                //curFail为null，代表没找到，设置子结点的fail指针指向头结点，不为null则表示找到了
                if (curFail == null) {
                    next.fail = this.root;
                } else {
                    // 子节点的fail指针，指向curFail指向节点i路上的节点
                    next.fail = curFail.nexts[i];
                }
                queue.add(next);
            }
        }
    }

    public List<String> getContainsWords(String content) {
        char[] chars = content.toCharArray();
        List<String> res = new ArrayList<>();
        Node cur = this.root;
        for (int i = 0; i < chars.length; i++) {
            int path = chars[i] - 'a';
            // 没有路，沿着fail指针找路
            while (cur.nexts[path] == null && cur != this.root) cur = cur.fail;
            //找到路了，cur要往前推进，没有则回到root节点
            cur = cur.nexts[path] != null ? cur.nexts[path] : this.root;
            //用follow指针遍历从cur开始沿着fail指针走到root的路径，收集答案
            Node follow = cur;
            while (follow != this.root) {
                // 看过了，不再看了
                if (follow.visited) break;
                if (follow.end != null) {
                    // 标记看过了
                    res.add(follow.end);
                    follow.visited = true;
                }
                follow = follow.fail;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ACAutomation acAutomation = new ACAutomation();
        acAutomation.insert("abc");
        acAutomation.insert("abcd");
        acAutomation.insert("bcdx");
        acAutomation.build();
        List<String> res = acAutomation.getContainsWords("abcdda");
        System.out.println(res);
    }

}
