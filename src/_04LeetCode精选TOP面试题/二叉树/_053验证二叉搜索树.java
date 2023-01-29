package _04LeetCode精选TOP面试题.二叉树;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * Created by huangjunyi on 2022/10/30.
 */
public class _053验证二叉搜索树 {
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
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        /*
        摩尔斯遍历，
        遍历到每个节点时
        都把左子树的最右结点的右指针，指向当前节点
        然后在往左遍历
        回到当前节点时，则清除
        省掉了压栈弹栈的过程
         */
        TreeNode cur = root;
        TreeNode mostRight = null;
        TreeNode pre = null;
        while (cur != null) {
            mostRight  = cur.left;
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
            if (pre != null && pre.val >= cur.val) return false;
            pre = cur;
            cur = cur.right;
        }
        return true;
    }
}
