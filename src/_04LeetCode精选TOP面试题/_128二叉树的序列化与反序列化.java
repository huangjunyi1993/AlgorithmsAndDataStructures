package _04LeetCode精选TOP面试题;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 *
 * Created by huangjunyi on 2022/11/7.
 */
public class _128二叉树的序列化与反序列化 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class Codec {

        /*
        宽度优先遍历
         */

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "[]";
            Queue<TreeNode> queue = new LinkedList<>();
            LinkedList<String> list = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                list.addLast(node == null ? null : String.valueOf(node.val));
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            while (!list.isEmpty() && list.peekLast() == null) list.pollLast();
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str == null ? "null," : str + ",");
            }
            String res = sb.substring(0, sb.length() - 1);
            return "[" + res.toString() + "]";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0 || Objects.equals(data, "[]")) return null;
            data = data.substring(1, data.length() - 1);
            String[] strs = data.split(",");
            TreeNode root = node(strs[0]);
            int index = 1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                node.left = index == strs.length ? null : node(strs[index++]);
                node.right = index == strs.length ? null : node(strs[index++]);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            return root;
        }

        private TreeNode node(String str) {
            if (Objects.equals(str, "null")) return null;
            return new TreeNode(Integer.parseInt(str));
        }

    }

}
