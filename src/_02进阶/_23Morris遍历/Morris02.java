package _02进阶._23Morris遍历;

/**
 * Morris遍历判断一颗树是否是搜索二叉树
 * Created by huangjunyi on 2022/9/10.
 */
public class Morris02 {

    private static class Node {
        Node left;
        Node right;
        int value;
    }

    public static boolean isBST(Node head) {
        if (head == null) return true;
        Node cur = head;
        Node mostRight = null;
        Integer preValue = null; //拿一个变量记录前一个遍历到的结点的值
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) mostRight = mostRight.right;
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            //以Morris中序遍历的顺序，判果前一个节点的值是否比当前节点大，是则返回false，代表不是BST
            if (preValue != null && cur.value <= preValue) return false;
            preValue = cur.value;
            cur = cur.right;
        }
        return true;
    }

}
