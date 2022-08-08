package _01基础._09二叉数;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按先序遍历方式，序列化和返序列化二叉树
 */
public class Tree03 {

    public static Queue<Integer> serialize(Node head) {
        Queue<Integer> queue = new LinkedList<>();
        serialize(head, queue);
        return queue;
    }

    private static void serialize(Node head, Queue<Integer> queue) {
        if (head == null) queue.offer(null);
        else {
            //先序：先入序列化队列，再递归处理左右子结点
            queue.offer(head.value);
            serialize(head.left, queue);
            serialize(head.right, queue);
        }
    }

    public static Node deserialize(Queue<Integer> queue) {
        if (queue == null || queue.isEmpty()) return null;
        if (queue.peek() == null) {
            queue.poll();
            return null;
        }
        //先序：弹出的结点作为头结点，然后递归处理左右子结点
        Node head = createNode(queue.poll());
        head.left = deserialize(queue);
        head.right = deserialize(queue);
        return head;
    }

    private static Node createNode(int value) {
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
