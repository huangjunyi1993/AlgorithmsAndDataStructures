package _04LeetCode精选TOP面试题.二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _055二叉树的层序遍历 {

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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        // 宽度优先遍历，只是每次弹一个size结算
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.pollLast();
                subRes.add(treeNode.val);
                if (treeNode.left != null) queue.addFirst(treeNode.left);
                if (treeNode.right != null) queue.addFirst(treeNode.right);
            }
            res.add(subRes);
        }
        return res;
    }

}
