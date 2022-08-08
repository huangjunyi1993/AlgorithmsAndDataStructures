package _01基础._10二叉树递归套路;

/**
 * 给定一个二叉树，返回二叉树任意节点中最大的距离
 */
public class TreeRecursion02 {

    public static int findMaxDistance(Node head) {
        Info info = process(head);
        return info.maxDistance;
    }

    private static Info process(Node node) {
        if (node == null) {
            Info info = new Info();
            info.height = 0;
            info.maxDistance = 0;
            return info;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        Info info = new Info();
        //当前子树的最大距离 = 左子树的最大距离、右子树的最大距离、左子树高度+1+右子树高度，三者的最大值
        info.maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + 1 + rightInfo.height);
        //当前子树的高度 = 左子树的高度、右子树的高度，二者中的最大值+1
        info.height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return info;
    }

    private static class Info {
        private int height; //高度
        private int maxDistance; //最大距离
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
