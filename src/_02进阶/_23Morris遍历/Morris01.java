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
        Node cur = head; // cur指针，一开始在头节点
        Node mostRight = null; // 左子树的最右节点
        while (cur != null) {
            mostRight = cur.left; // 左树
            if (mostRight != null) { // 左树不为空
                // 一直往右
                while (mostRight.right != null && mostRight.right != cur) mostRight = mostRight.right;
                // 左子树的最右节点的右指针为空，第一次来
                if (mostRight.right == null) {
                    // 指向当前节点，然后cur往左移
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 左子树的最右节点的右指针不为空，第二次来，置空mosrtRight的right指针
                    mostRight.right = null;
                }
            }
            // cur指针往右
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
            // 不管是否有左树，都会来到这里，有左树的第二次会来到这里
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
                    // 有左树的节点，第一次来到的时候就打印
                    System.out.println(cur.value);
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                // 没有左树的节点，直接打印
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

    // 逆序打印树的右边界
    private static void printEdge(Node head) {
        // 树的右边界，链表反转
        Node tail = reverse(head);
        Node cur = tail;
        // 遍历链表打印
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.right;
        }
        // 把链表返回回去
        reverse(tail);
    }

    // 反转树的右边界链表
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
