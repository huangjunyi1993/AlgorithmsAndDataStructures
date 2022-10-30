package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/symmetric-tree/?favorite=2ckc81c
 * Created by huangjunyi on 2022/10/30.
 */
public class _054对称二叉树 {
    private class TreeNode {
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
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null) return true;
        if (head1 != null && head2 != null) {
            return head1.val == head2.val &&
                    isMirror(head1.left, head2.right) &&
                    isMirror(head1.right, head2.left);
        }
        return false;
    }
}
