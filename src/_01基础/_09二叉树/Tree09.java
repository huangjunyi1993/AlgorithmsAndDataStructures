package _01基础._09二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现多叉树和二叉树的互转
 * Created by huangjunyi on 2022/11/24.
 */
public class Tree09 {
    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    class Codec {

        /**
         * 多叉树转二叉树
         * @param root
         * @return
         */
        public TreeNode encode(Node root) {
            if (root == null) return null;
            // 二叉树头节点
            TreeNode head = new TreeNode(root.val);
            // 生成二叉树节点链表，挂到左节点上
            head.left = nodeListToTreeNode(root.children);
            return head;
        }

        /**
         * 多叉树的子节点List转成二叉树的节点链表返回
         * 多叉树的子节点的子节点List，会递归处理
         * @param children
         * @return
         */
        private TreeNode nodeListToTreeNode(List<Node> children) {
            TreeNode head = null;
            TreeNode pre = null;
            TreeNode cur = null;
            for (Node child : children) {
                // 封装成二叉树节点
                cur = new TreeNode(child.val);
                // 递归生成该二叉树节点的左节点上的节点链表
                cur.left = nodeListToTreeNode(child.children);
                if (head == null) head = cur;
                if (pre != null) pre.right = cur;
                pre = cur;
            }
            return head;
        }


        /**
         * 二叉树转多叉树
         * @param root
         * @return
         */
        public Node decode(TreeNode root) {
            if (root == null) return null;
            // 多叉树头节点，并且转换二叉树左节点上的节点链表为多叉树子节点List
            return new Node(root.val, treeNodeToNodeList(root.left));
        }

        /**
         * 二叉树的节点链表转成多叉树的子节点List
         * @param root
         * @return
         */
        public List<Node> treeNodeToNodeList(TreeNode root) {
            List<Node> children = new ArrayList<>();
            TreeNode cur = root;
            while (cur != null) {
                // 封装为多叉树节点，递归转换当前二叉树节点的左节点上的节点链表为该多叉树节点的子节点List
                Node node = new Node(cur.val, treeNodeToNodeList(cur.left));
                // 收集多叉树节点到List
                children.add(node);
                cur = cur.right;
            }
            return children;
        }

    }

}
