package _04LeetCode精选TOP面试题.二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _052二叉树的中序遍历 {

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
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
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
