package _03经典面试题目.二叉树;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/description/
 * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
 * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
 * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , Node.right 的任何后代的值 严格大于 Node.val。
 * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
 * Created by huangjunyi on 2022/12/25.
 */
public class _07ConstructBinarySearchTreeFromPreorderTraversal {

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
        public TreeNode bstFromPreorder(int[] preorder) {
            // 单调栈建立一个辅助数组，加速寻找右子树头节点，nearBig[i]：preorder[i]右边离它最近比它大的数的下标
            int[] nearBig = new int[preorder.length];
            for (int i = 0; i < nearBig.length; i++) {
                nearBig[i] = -1;
            }
            LinkedList<Integer> stack = new LinkedList<>();
            for (int i = 0; i < preorder.length; i++) {
                while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) nearBig[stack.pop()] = i;
                stack.push(i);
            }
            // 递归构建二叉树
            return build(preorder, 0, preorder.length - 1, nearBig);
        }

        private TreeNode build(int[] preorder, int L, int R, int[] nearBig) {
            if (L > R) return null;
            // 头节点
            TreeNode node = new TreeNode(preorder[L]);
            // 递归构建左子树
            node.left  = build(preorder, L + 1, nearBig[L] == -1 ? R : nearBig[L] - 1, nearBig);
            // 递归构建右子树
            node.right = build(preorder, nearBig[L] == -1 ? R + 1 : nearBig[L], R, nearBig);
            return node;
        }
    }

}
