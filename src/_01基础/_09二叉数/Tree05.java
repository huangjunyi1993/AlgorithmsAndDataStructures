package _01基础._09二叉数;

/**
 * 给定一个二叉树结点，有左右结点指针，也有父节点指针，找到它在中序遍历上的后继结点
 */
public class Tree05 {

    public static Node findSuccessorNode(Node node) {
        if (node == null) return node;


        if (node.right != null) {
            //有右结点，找到右结点的最左结点
            return findLeftMost(node.right);
        } else {
            //没有右结点，则往上找父节点，直到父节点的左结点引用指向的是当前结点所在的子树
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }

    }

    private static Node findLeftMost(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;
    }

}
