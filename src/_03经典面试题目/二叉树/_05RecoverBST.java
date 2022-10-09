package _03经典面试题目.二叉树;

import java.util.LinkedList;

/**
 * 给定一棵搜索二叉树的头结点，二叉树中有两个节点位置错误，找出并将其恢复
 * Created by huangjunyi on 2022/10/9.
 */
public class _05RecoverBST {

    private static class Node {
        int value;
        private Node left;
        private Node right;
    }

    public static void recover(Node head) {
        if (head == null) return;
        Node[] errs = findErrorNode(head);
        int tmp = errs[0].value;
        errs[0].value = errs[1].value;
        errs[1].value = tmp;
    }

    private static Node[] findErrorNode(Node head) {
        Node[] errs = new Node[2];
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = head;
        Node pre = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else  {
                cur = stack.pop();
                if (pre != null && pre.value > cur.value) {
                    /*
                    二叉树中两个阶段位置错误，那么二叉树序列化后，会有两处下降（如果两个节点紧挨，那么只会有一处）
                    第一个节点在第一处下降的左侧，
                    第二个节点在第二处下降的右侧
                     */
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = cur;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return errs;
    }

}
