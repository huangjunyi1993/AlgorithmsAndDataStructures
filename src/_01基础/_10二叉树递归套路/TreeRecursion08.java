package _01基础._10二叉树递归套路;

/**
 * 二叉树中寻找两个结点的最低公共祖先
 */
public class TreeRecursion08 {

    public static Node findLowestAncestor(Node head, Node node1, Node node2) {
        Info info = process(head, node1, node2);
        return info == null ? null : info.result;
    }

    private static Info process(Node node, Node node1, Node node2) {
        if (node == null) return null;

        //分别往左右子树收集信息
        Info leftInfo = process(node, node1, node2);
        Info rightInfo = process(node, node1, node2);

        //根据左右子树的信息，计算findNode1和findNode2
        boolean findNode1 = node == node1 || leftInfo.findNode1 || rightInfo.findNode1;
        boolean findNode2 = node == node2 || leftInfo.findNode2 || rightInfo.findNode2;

        //如果左子树或右子树返回的信息中已经记录了最低公共祖先，则记录到当前节点的信息中，往上返回
        Node result = null;
        if (leftInfo.result != null) result = leftInfo.result;
        if (rightInfo.result != null) result = rightInfo.result;

        //到这里result依然为空，则根据findNode1和findNode2的值进行计算，如果都为true，代表当前这个结点就是最低公共祖先
        if (result == null) {
            if (findNode1 && findNode2) result = node;
        }

        Info info = new Info();
        info.result = result;
        info.findNode1 = findNode1;
        info.findNode2 = findNode2;
        return info;
    }

    private static class Info {
        private Node result; //node1和node2的最低公共祖先
        private boolean findNode1; //是否已经找到了node1
        private boolean findNode2; //是否已经找到了node2
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
