package _04LeetCode精选TOP面试题.二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _056二叉树的锯齿形层序遍历 {

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        /*
        宽度优先遍历 + 头过程、尾过程
        头过程：从双端队列头部弹出，子结点从尾部进，先放左节点，再放右节点
        尾过程：从双端队列尾部弹出，子结点从头部进，先放右节点，再放左节点
         */
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isHead = true;
        while (!queue.isEmpty()) {
            if (isHead) {
                List<Integer> subRes = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.pollFirst();
                    subRes.add(cur.val);
                    if (cur.left != null) {
                        queue.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        queue.addLast(cur.right);
                    }
                }
                res.add(subRes);
            } else {
                List<Integer> subRes = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.pollLast();
                    subRes.add(cur.val);
                    if (cur.right != null) {
                        queue.addFirst(cur.right);
                    }
                    if (cur.left != null) {
                        queue.addFirst(cur.left);
                    }
                }
                res.add(subRes);
            }
            isHead = !isHead;
        }
        return res;
    }

}
