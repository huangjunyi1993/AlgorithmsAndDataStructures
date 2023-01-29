package _04LeetCode精选TOP面试题.二叉树;

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
        if (head1 == null && head2 == null) return true; // 空树对空树，是镜像
        if (head1 != null && head2 != null) {
            // 都不空，值相等，1左和2右互为镜像，1右和2左互为镜像
            return head1.val == head2.val &&
                    isMirror(head1.left, head2.right) &&
                    isMirror(head1.right, head2.left);
        }
        // 一空一不空，不是镜像
        return false;
    }
}
