package _03经典面试题目.二叉树;

/**
 * 求完全二叉树的节点个数
 *
 * 要求时间复杂度低于O(n)
 * Created by huangjunyi on 2022/10/1.
 */
public class _04CompleteTreeNodeNumber {

    private static class Node {
        private Node left;
        private Node right;
        private int value;
    }

    public static int nodeNum(Node head) {
        if (head == null) return 0;
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    private static int bs(Node node, int level, int h) {
        // base case：达到最底层，一个节点，就是自己
        if (level == h) {
            return 1;
        }
        /*
        观察当前树的右树的深度，
        如果深度等于当前树的最大深度，证明左树是满的，可以结算左树，然后往右树递归
        如果深度小于当前树的最大深度，证明左树不满，但是右树是满的，并且小一层，结算右树，然后往左树递归
        结算：（2^n - 1） + 1 -> 1是当前头结点，n是左子树或者右子树的高度
         */
        if (mostLeftLevel(node.right, level + 1) == h) {
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        } else {
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }

    /**
     * 求node为头的子树的最大深度
     * @param node
     * @param level
     * @return
     */
    private static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

}
