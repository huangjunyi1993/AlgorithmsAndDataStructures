package _01基础._09二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层序列化和反序列化二叉树
 */
public class Tree04 {

    public static Queue<Integer> serialize(Node head) {
        Queue<Integer> res = new LinkedList<>();
        if (head == null) {
            res.offer(null);
            return res;
        }
        //先序列化，再入队列
        Queue<Node> queue = new LinkedList<>();
        res.offer(head.value);
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left == null) {
                res.offer(null);
            } else {
                //结点不为空，序列化后如队列
                res.offer(node.left.value);
                queue.offer(node.left);
            }
            if (node.right == null) {
                res.offer(null);
            } else {
                //结点不为空，序列化后如队列
                res.offer(node.right.value);
                queue.offer(node.right);
            }
        }
        return res;
    }

    public static Node deserialize(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) return null;
        Node head = createNode(queue.poll());

        if (head != null) {
            Queue<Node> queue1 = new LinkedList<>();
            queue1.offer(head);
            while (!queue1.isEmpty()) {
                //从队列出队的结点，处理其左右子结点，然后左右子结点不为空的，入队列
                Node node = queue1.poll();
                node.left = createNode(queue.poll());
                node.right = createNode(queue.poll());
                if (node.left != null) queue1.offer(node.left);
                if (node.right != null) queue1.offer(node.right);
            }
        }

        return head;
    }

    private static Node createNode(Integer value) {
        if (value == null) return null;
        Node node = new Node();
        node.value = value;
        return node;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
