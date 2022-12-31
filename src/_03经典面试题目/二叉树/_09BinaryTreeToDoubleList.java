package _03经典面试题目.二叉树;

/**
 * 二叉树原地转双向链表
 * Created by huangjunyi on 2022/12/29.
 */
public class _09BinaryTreeToDoubleList {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    private static class Info {
        Node head;
        Node end;

        public Info(Node head, Node end) {
            this.head = head;
            this.end = end;
        }
    }

    public static Node treeToDoublyList(Node head) {
        if (head == null) return null;
        Info info = process(head);
        // 整体头尾相接
        info.head.left = info.end;
        info.end.right = info.head;
        return info.head;
    }

    private static Info process(Node node) {
        if (node == null) return new Info(null, null);
        // 左右子树，各收一个信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        // 当前子树，串联成双向链表
        if (leftInfo.end != null) leftInfo.end.right = node;
        node.left = leftInfo.end;
        node.right = rightInfo.head;
        if (rightInfo.head != null) rightInfo.head.left = node;
        return new Info(leftInfo.head != null ? leftInfo.head : node, rightInfo.end != null ? rightInfo.end : node);
    }

}
