package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/?favorite=2ckc81c
 * Created by huangjunyi on 2022/12/13.
 */
public class _067二叉树中的最大路径和 {

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
        private class Info {
            private int maxPathSumStartFromHead; // 以当前子树头节点为起始点的最大路径和
            private int maxPathSum; // 当前子树最大路径和

            public Info(int maxPathSumStartFromHead, int maxPathSum) {
                this.maxPathSumStartFromHead = maxPathSumStartFromHead;
                this.maxPathSum = maxPathSum;
            }
        }

        public int maxPathSum(TreeNode root) {
            return process(root).maxPathSum;
        }

        private Info process(TreeNode node) {
            // base case：兼容只有一个负数节点的情况，答案应该是负数，所以不能反回(0, 0)，否则答案不对
            if (node == null) return null;

            // 左右子树收一个答案
            Info leftInfo = process(node.left);
            Info rightInfo = process(node.right);

            // 计算高度和
            int maxPathSumStartFromHead = node.val;
            if (leftInfo != null) {
                maxPathSumStartFromHead = Math.max(maxPathSumStartFromHead, node.val + leftInfo.maxPathSumStartFromHead);
            }
            if (rightInfo != null) {
                maxPathSumStartFromHead = Math.max(maxPathSumStartFromHead, node.val + rightInfo.maxPathSumStartFromHead);
            }

            // 计算路径和
            int maxPathSum = node.val
                    + (leftInfo != null && leftInfo.maxPathSumStartFromHead > 0 ? leftInfo.maxPathSumStartFromHead : 0)
                    + (rightInfo != null && rightInfo.maxPathSumStartFromHead > 0 ? rightInfo.maxPathSumStartFromHead : 0);
            if (leftInfo != null) {
                maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
            }
            if (rightInfo != null) {
                maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
            }

            return new Info(maxPathSumStartFromHead, maxPathSum);
        }
    }
}
