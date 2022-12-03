package _01基础._10二叉树递归套路;

/**
 * 给定一个二叉树，判断是否是平衡二叉树
 */
public class TreeRecursion01 {

    public static boolean isBalance(Node head) {
        Info info = process(head);
        return info.isBalance;
    }

    private static Info process(Node node) {
        // 1、base case： 空树，高度为0，是平衡
        if (node == null) {
            Info info = new Info();
            info.isBalance = true;
            info.height = 0;
            return info;
        }

        // 2、左右递归收集信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        // 3、根据子信息，计算自己的信息
        Info info = new Info();
        //如果左子树是平衡二叉树，并且右子树是平衡二叉树，并且两子树高度差不大于1，则当前子树也是平衡二叉树
        if (leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) <= 1) {
            info.isBalance = true;
        }

        //取左右子树中高度较大的加一，作为当前子树的高度
        info.height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return info;
    }

    private static class Info {
        private boolean isBalance; //是否平衡
        private int height; //高度
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
