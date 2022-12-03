package _01基础._10二叉树递归套路;

/**
 * 给定一个二叉树，返回其二叉搜索子树中最大子树的头结点
 */
public class TreeRecursion06 {

    public static Node findMaxSubBSTHead(Node head) {
        Info info = process(head);
        return info == null ? null : info.maxSubBSTHead;
    }

    private static Info process(Node node) {
        if (node == null) return null;

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        //预设当前子树不是二叉搜索树，从左右子树返回的信息中，计算max、min、maxSubBSTSize、maxSubBSTHead
        Info info = new Info();
        info.max = node.value;
        info.min = node.value;
        info.maxSubBSTSize = 0;
        if (leftInfo != null) {
            info.max = Math.max(leftInfo.max, info.max);
            info.min = Math.min(leftInfo.min, info.min);
            info.maxSubBSTSize = leftInfo.maxSubBSTSize;
            info.maxSubBSTHead = leftInfo.maxSubBSTHead;
        }
        if (rightInfo != null) {
            info.max = Math.max(rightInfo.max, info.max);
            info.min = Math.min(rightInfo.min, info.min);
            if (rightInfo.maxSubBSTSize > info.maxSubBSTSize) {
                info.maxSubBSTHead = rightInfo.maxSubBSTHead;
                info.maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }

        //判断当前子树是否是二叉搜索树，若是则maxSubBSTHead设置为当前结点，重新计算maxSubBSTSize
        if ((leftInfo == null || leftInfo.maxSubBSTHead == node.left)
                && (rightInfo == null || rightInfo.maxSubBSTHead == node.right)
                && (leftInfo == null || leftInfo.max < node.value)
                && (rightInfo == null || rightInfo.min > node.value)) {
            info.maxSubBSTHead = node;
            info.maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + 1 + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize);
        }

        return info;
    }

    private static class Info {
        private Node maxSubBSTHead; //最大二叉搜索子树头结点
        private int maxSubBSTSize; //最大二叉搜索子树大小
        private int max; //子树最大值
        private int min; //子树最小值
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
