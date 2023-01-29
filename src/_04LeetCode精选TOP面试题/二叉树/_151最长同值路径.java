package _04LeetCode精选TOP面试题.二叉树;

/**
 * https://leetcode.cn/problems/longest-univalue-path/
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _151最长同值路径 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        return process(root)[1] - 1;
    }

    /**
     * 二叉树递归套路，返回的数组，
     *
     * 0位置代表以当前node节点为头，一直往下相连的路径上，值相同的节点数最多几个
     * 比如node的val为5，然后它的左节点也为5，然后左左节点又为5，左左左节点为4，左左左左节点为5，右节点为4，
     * 那么以node为头结点，往下相连的路径上，这3个5是值相同又是相连的，并且又是以node为头的，最长的路径
     * 返回的数组0位置值是3
     *
     * 1位置则表示以当前node节点为头的子树，相连路径值相同的节点数最多几个
     * 这个也是要返回即时相连，并且相连路径的每个节点val相同，但是不要求以node为头，只要是在node为头的子树内就行
     * 返回这个路径的节点数
     *
     * @param node
     * @return
     */
    private int[] process(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] leftInfo = process(node.left);
        int[] rightInfo = process(node.right);
        int len = 1;
        if (node.left != null && node.val == node.left.val) {
            len = len + leftInfo[0];
        }
        if (node.right != null && node.val == node.right.val) {
            len = Math.max(len, 1 + rightInfo[0]);
        }
        int max = Math.max(Math.max(leftInfo[1], rightInfo[1]), len);
        if (node.left != null && node.right != null && node.val == node.left.val && node.val == node.right.val) {
            max = Math.max(max, leftInfo[0] + rightInfo[0] + 1);
        }
        return new int[] {len, max};
    }
}
