package _04LeetCode精选TOP面试题.二叉树;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _058从前序与中序遍历序列构造二叉树 {

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 用一个map记录中序遍历值与下标的对应关系
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        // 递归构建数
        return process(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode process(int[] preorder, int L1, int R1,
                             int[] inorder, int L2, int R2,
                             HashMap<Integer, Integer> map) {
        // base case
        if (L1 > R1) return null;
        if (L1 == R1) return new TreeNode(preorder[L1]);

        // 构建头结点 => 递归构建左右子树 => 返回头结点
        Integer F = map.get(preorder[L1]);
        TreeNode head = new TreeNode(preorder[L1]);
        head.left = process(preorder, L1 + 1, L1 + F - L2, inorder, L2, F - 1, map);
        head.right = process(preorder, L1 + F - L2 + 1, R1, inorder, F + 1, R2, map);
        return head;
    }

}
