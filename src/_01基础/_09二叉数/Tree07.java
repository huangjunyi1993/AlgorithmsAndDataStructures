package _01基础._09二叉数;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一颗二叉树，判断其是否是完全二叉树
 *
 * 按层遍历二叉树，并进行判断：
 * 1、如果有某个结点有右节点但是没有左结点，则不是完全二叉树
 * 2、如果遍历到某个结点不是同时拥有左右子结点，则后续遍历到的结点必须都是叶子结点，否则不是完全二叉树
 */
public class Tree07 {

    public static boolean isCBT(Node head) {
        if (head == null) return true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Node left = node.left;
            Node right = node.right;

            if ((left == null && right != null) || (leaf && (left != null || right != null))) {
                return false;
            }

            if (left != null) queue.offer(left);
            if (right != null) queue.offer(right);
        }

        return true;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
