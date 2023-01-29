package _04LeetCode精选TOP面试题.二叉树;

/**
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _059将有序数组转换为二叉搜索树 {

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

    public TreeNode sortedArrayToBST(int[] nums) {
        // 递归 + 二分
        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int l, int r) {
        if (l > r) return null;
        if (l == r) return new TreeNode(nums[l]);
        int mid = (l + r) / 2;
        TreeNode head = new TreeNode(nums[mid]);
        head.left = process(nums, l, mid - 1);
        head.right = process(nums, mid + 1, r);
        return head;
    }

}
