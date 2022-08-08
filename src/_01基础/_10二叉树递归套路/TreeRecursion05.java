package _01基础._10二叉树递归套路;

/**
 * 给定一颗二叉树，判断其是否是满二叉树
 */
public class TreeRecursion05 {

    public static boolean isFull(Node head) {
        Info info = process(head);
        return ((1 << info.height) + 1) == info.size;
    }

    private static Info process(Node node) {
        if (node == null) {
            Info info = new Info();
            info.size = 0;
            info.height = 0;
            return info;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        Info info = new Info();
        info.size = leftInfo.size + 1 + rightInfo.size;
        info.height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return info;
    }

    private static class Info {
        private int height;
        private int size;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
