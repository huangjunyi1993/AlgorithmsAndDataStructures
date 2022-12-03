package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/house-robber-iii/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _337打家劫舍III {

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
        class Info {
            int yes; // 子树，抢头，获得的最大收益
            int no; // 子树，不抢头，获得的最大收益

            public Info(int yes, int no) {
                this.yes = yes;
                this.no = no;
            }
        }
        public int rob(TreeNode root) {
            Info info = process(root);
            return Math.max(info.yes, info.no);
        }

        private Info process(TreeNode node) {
            if (node == null) return new Info(0, 0);
            Info leftInof = process(node.left);
            Info rightInfo = process(node.right);
            // 枪头的最大收益 = 自己的收益 + 左右孩子不抢头的最大收益
            int yes = node.val + leftInof.no + rightInfo.no;
            // 不抢头的最大收益 = 左孩子抢头/不抢头较大收益 + 右孩子抢头/不抢头较大收益
            int no = Math.max(leftInof.yes, leftInof.no) + Math.max(rightInfo.yes, rightInfo.no);
            return new Info(yes, no);
        }
    }
}
