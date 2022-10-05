package _03经典面试题目.二叉树;

/**
 * 双向链表节点结构和二叉树结点结构是一样的，如果你把last认为是left，next认为是right的话。
 * 给定一个搜索二叉树的头节点head，请转换成一条有序的双向链表，并返回链表的头节点。
 * Created by huangjunyi on 2022/9/25.
 */
public class _03BSTChangeToListNode {

    private static class Node {
        private Node left;
        private Node right;
        private int value;
    }

    private static class Info {
        private Node start; // 子树转成链表后，链表头节点
        private Node end; // 子树转成链表后，链表尾结点

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Node changeToList(Node head) {
        if (head == null) return null;
        return process(head).start;
    }

    /**
     * 二叉树递归套路
     *
     * 左树收一个信息leftInfo
     * 右树收一个信息rightInfo
     * 如果左树返回的信息中的end不为空，则end与当前节点相连
     * 如果右树返回的信息中start不为空，则start与当前节点相连
     * 这样就串成一个更长的链表
     *
     * 然后返回当前递归的信息Info
     * 如果左树的start不为空，则Info的start也是左树的start，否则就是当前节点
     * 如果右树的end不为空，则Info的end也是右树的end，否则就是当前节点
     * @param node
     * @return
     */
    private static Info process(Node node) {
        if (node == null) return new Info(null, null);
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        if (leftInfo.end != null) {
            leftInfo.end.right = node;
            node.left = leftInfo.end;
        }
        if (rightInfo.start != null) {
            node.right = rightInfo.start;
            rightInfo.start.left = node;
        }

        return new Info(leftInfo.start != null ? leftInfo.start : node, rightInfo.end != null ? rightInfo.end : node);
    }

}
