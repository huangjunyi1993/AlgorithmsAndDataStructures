package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/merge-two-binary-trees/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _617合并二叉树 {

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
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            // 有一个为空，返回另一个
            if (root1 == null) return root2;
            if (root2 == null) return root1;
            // merge当前子树头节点
            TreeNode newNode = new TreeNode(root1.val + root2.val);
            // 递归merge左树
            newNode.left = mergeTrees(root1.left, root2.left);
            // 递归merge右树
            newNode.right = mergeTrees(root1.right, root2.right);
            return newNode;
        }
    }

}
