package _04LeetCode精选TOP面试题.二叉树;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _057二叉树的最大深度 {

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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        // 深度优先遍历
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
