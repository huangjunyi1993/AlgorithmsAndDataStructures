package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/invert-binary-tree/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _226翻转二叉树 {

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

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return root;
            // 递归
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }
    }

}
