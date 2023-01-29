package _04LeetCode精选TOP面试题.哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/copy-list-with-random-pointer/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/2.
 */
public class _075复制带随机指针的链表 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution {
        public Node copyRandomList(Node head) {
            Map<Node, Node> map = new HashMap<>();
            for (Node cur = head; cur != null; cur = cur.next) {
                Node node = new Node(cur.val);
                map.put(cur, node);
            }
            Node newHead = null;
            Node pre = null;
            for (Node cur = head; cur != null; cur = cur.next) {
                Node curCopy = map.get(cur);
                if (newHead == null) {
                    newHead = curCopy;
                    pre = newHead;
                } else {
                    pre.next = curCopy;
                    pre = curCopy;
                }
                if (cur.random != null) {
                    curCopy.random = map.get(cur.random);
                }
            }
            return newHead;
        }
    }
}
