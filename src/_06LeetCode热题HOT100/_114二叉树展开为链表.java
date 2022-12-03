package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/submissions/210049340/
 *
 * 在Morris遍历上做修改，就可以得到最优解
 * Created by huangjunyi on 2022/11/12.
 */
public class _114二叉树展开为链表 {
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
        public void flatten(TreeNode root) {

        }
    }

}
