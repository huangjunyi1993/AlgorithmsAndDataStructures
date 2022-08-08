package _01基础._10二叉树递归套路;

/**
 * 给定一个二叉树，返回其二叉搜索子树中最大子树的结点数
 */
public class TreeRecursion03 {

    public static int findMaxSubAllBSTSize(Node head) {
        if (head == null) return 0;
        Info info = process(head);
        return info.maxSubAllBSTSize;
    }

    private static Info process(Node node) {
        if (node == null) return null;

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        boolean isAllBST; //是否是二叉搜索树
        int maxSubAllBSTSize; //最大二叉搜索子树的结点数
        int max; //当前子树最大值
        int min; //当前子树最小值

        //根据从左右子树收集回来的信息，计算当前节点的max，min
        max = node.value;
        min = node.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        //预设当前子树非二叉搜索数，最大二叉搜索子树节点数位左子树和右子树中maxSubAllBSTSize的最大值
        isAllBST = false;
        maxSubAllBSTSize = Math.max((leftInfo == null ? 0 : leftInfo.maxSubAllBSTSize), (rightInfo == null ? 0 : rightInfo.maxSubAllBSTSize));

        //判断当前子树是否是二叉搜索数，是的话，更新isAllBST，maxSubAllBSTSize
        if ((leftInfo == null || leftInfo.isAllBST) //左子树是二叉搜索树
                && ((rightInfo == null || rightInfo.isAllBST)) //右子树是二叉搜索树
                && (leftInfo == null || leftInfo.max < node.value) //左子树的最大值小于当前节点的值
                && (rightInfo == null || rightInfo.min > node.value) //右子树的最小值大于当前节点的值
                ) {
            isAllBST = true;
            maxSubAllBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubAllBSTSize) + 1 + (rightInfo == null ? 0 : rightInfo.maxSubAllBSTSize);
        }

        Info info = new Info();
        info.isAllBST = isAllBST;
        info.maxSubAllBSTSize = maxSubAllBSTSize;
        info.max = max;
        info.min = min;
        return info;
    }

    private static class Info {
        private boolean isAllBST; //是否是二叉搜索树
        private int maxSubAllBSTSize; //最大二叉搜索子树的结点数
        private int max; //当前子树最大值
        private int min; //当前子树最小值
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
