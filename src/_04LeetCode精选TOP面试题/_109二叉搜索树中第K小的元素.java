package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/?favorite=2ckc81c
 *
 * 思路：
 * 摩尔斯遍历中序遍历数到第K个
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _109二叉搜索树中第K小的元素 {

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
        public int kthSmallest(TreeNode root, int k) {
            // Morris中序遍历数到第k个
            int index = 0;
            TreeNode cur = root;
            while (cur != null) {
                TreeNode mostRight = cur.left;
                if (mostRight != null) {
                    while (mostRight.right != null && mostRight.right != cur) mostRight = mostRight.right;
                    if (mostRight.right == cur) {
                        mostRight.right = null;
                    } else {
                        mostRight.right = cur;
                        cur = cur.left;
                        continue;
                    }
                }
                index++;
                if (index == k) return cur.val;
                cur = cur.right;
            }
            return cur.val;
        }
    }
}
