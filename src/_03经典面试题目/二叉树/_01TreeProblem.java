package _03经典面试题目.二叉树;

/**
 * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
 * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
 * Created by huangjunyi on 2022/9/18.
 */
public class _01TreeProblem {

    private static class Node {
        private Node left;
        private Node right;
        private int value;
    }

    /**
     * 问题1
     */
    public static int maxPath1(Node head) {
        if (head == null) return 0;
        return process1(head);
    }

    /**
     * 问题1
     *
     * 二叉树递归套路：
     * 从左节点和右节点都收一个最大路径和
     * 从这两个路径和中调一个最大的
     * 加上当前节点的value返回
     * @param node
     * @return
     */
    private static int process1(Node node) {
        if (node.left == null && node.right == null) return node.value;
        int res = Integer.MIN_VALUE;
        if (node.left != null) res = process1(node.left);
        if (node.right != null) res = Math.max(res, process1(node.right));
        return res + node.value;
    }

    /**
     * 问题2
     * @param head
     * @return
     */
    public static int maxPath2(Node head) {
        if (head == null) return 0;
        return process2(head)[0];
    }

    /**
     * 问题2
     *
     * 二叉树递归套路：
     * 从左右节点收取信息
     * 信息用一个长度为2的数组装 [整个子树的最大路径和，子树从头节点出发到叶节点的最大路径和]
     *
     * 当前节点根据收集回来的信息计算返回结果
     * p1: 不包括当前节点时走左侧的最大路径和
     * p2: 不包括当前节点时走右侧的最大路径和
     * p3: 只包括当前节点，不往下走
     * p4: 从当前节点出发，走左侧的最大路径和
     * p5: 从当前节点出发，走右侧的最大路径和
     *
     * 当前递归返回结果中的res[0]（整棵子树的最大路径和）取p1~p5中的最大值
     * 当前递归返回结果中的res[1]（从当前节点出发的最大路径和）取p3~p5中的最大值
     * @param node
     * @return
     */
    private static int[] process2(Node node) {
        if (node == null) return null;

        int p1 = Integer.MIN_VALUE;
        int p2 = Integer.MIN_VALUE;
        int p3 = node.value;
        int p4 = Integer.MIN_VALUE;
        int p5 = Integer.MIN_VALUE;

        int[] leftInfo = null;
        int[] rightInfo = null;
        if (node.left != null) leftInfo = process2(node.left);
        if (node.right != null) rightInfo = process2(node.right);

        if (leftInfo != null) {
            p1 = leftInfo[0];
            p4 = leftInfo[1] + p3;
        }

        if (rightInfo != null) {
            p2 = rightInfo[0];
            p5 = rightInfo[1] + p3;
        }

        return new int[] {
                Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4), p5),
                Math.max(Math.max(p3, p4), p5)
        };
    }

    /**
     * 问题3
     * @param head
     * @return
     */
    public static int maxPath3(Node head) {
        if (head == null) return 0;
        return process3(head)[0];
    }

    /**
     * 问题3
     *
     * 二叉树递归套路：
     * 在问题2解的基础上添加一个p6，从左树经过当前节点走到右树的最大路径和，参与res[0]的计算
     * 但是p6不能参与res[1]的计算，因为p6不能算作从当前节点出发，只是经过当前节点
     * @param node
     * @return
     */
    private static int[] process3(Node node) {
        if (node == null) return null;

        int p1 = Integer.MIN_VALUE;
        int p2 = Integer.MIN_VALUE;
        int p3 = node.value;
        int p4 = Integer.MIN_VALUE;
        int p5 = Integer.MIN_VALUE;
        int p6 = Integer.MIN_VALUE;

        int[] leftInfo = null;
        int[] rightInfo = null;
        if (node.left != null) leftInfo = process2(node.left);
        if (node.right != null) rightInfo = process2(node.right);

        if (leftInfo != null) {
            p1 = leftInfo[0];
            p4 = leftInfo[1] + p3;
        }

        if (rightInfo != null) {
            p2 = rightInfo[0];
            p5 = rightInfo[1] + p3;
        }

        if (leftInfo != null && rightInfo != null) p6 = p3 + leftInfo[1] + rightInfo[1];

        return new int[] {
                Math.max(Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4), p5), p6),
                Math.max(Math.max(p3, p4), p5)
        };
    }

}
