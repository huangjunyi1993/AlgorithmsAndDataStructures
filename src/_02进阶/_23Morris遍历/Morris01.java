package _02进阶._23Morris遍历;

/**
 * Morris遍历
 * Created by huangjunyi on 2022/9/10.
 */
public class Morris01 {

    private static class Node {
        Node left;
        Node right;
        int value;
    }

    /**
     * Morris序列
     * @param head
     */
    public static void process(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
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
            cur = cur.right;
        }
    }

    /**
     * Morris中序遍历
     * @param head
     */
    public static void processIn(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
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
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    /**
     * Morris先序遍历
     * @param head
     */
    public static void processPre(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) mostRight = mostRight.right;
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.println(cur.value);
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    /**
     * Morris后续遍历
     * @param head
     */
    public static void processPost(Node head) {
        if (head == null) return;
        Node cur = head;
        Node mostRight = null;
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
                    //每次第二次返回当该节点时，打印它的左树的右边界，以逆序的方式
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        //最后逆序打印整棵树的右边界
        printEdge(head);
    }

    private static void printEdge(Node head) {
        Node tail = reverse(head);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.right;
        }
        reverse(tail);
    }

    private static Node reverse(Node head) {
        Node pre = null;
        Node cur = head;
        Node right = null;
        while (cur != null) {
            right = cur.right;
            cur.right = pre;
            pre = cur;
            cur = right;
        }
        return pre;
    }

}
