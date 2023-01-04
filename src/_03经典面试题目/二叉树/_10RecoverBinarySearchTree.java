package _03经典面试题目.二叉树;

/**
 * https://leetcode.cn/problems/recover-binary-search-tree/
 *
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
 *
 * Created by huangjunyi on 2023/1/2.
 */
public class _10RecoverBinarySearchTree {

    public static class TreeNode {
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
        public void recoverTree(TreeNode root) {
            TreeNode first  = null; // 第一个错误节点，第一对降序对的第一个节点
            TreeNode second = null; // 第二个错误节点，最后一对降序对的第二个节点
            TreeNode cur = root; // 当前节点
            TreeNode pre = null; // 前一个节点
            // morris中序遍历
            while (cur != null) {
                TreeNode mostRight = cur.left;
                if (mostRight != null) {
                    while (mostRight.right != null && mostRight.right != cur) mostRight = mostRight.right;
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        cur = cur.left;
                        continue;
                    } else {
                        mostRight.right = null;
                    }
                }
                // 中序遍历第二次来到cur，结算
                if (pre == null) {
                    pre = cur;
                } else if (pre.val > cur.val) {
                    if (first == null) {
                        first = pre;
                        second = cur;
                    } else {
                        second = cur;
                    }
                } else {
                    pre = cur;
                }
                cur = cur.right;
            }
            // 交换值
            int firstVal = first.val;
            int secondVal = second.val;
            first.val = secondVal;
            second.val = firstVal;
        }
    }
}
