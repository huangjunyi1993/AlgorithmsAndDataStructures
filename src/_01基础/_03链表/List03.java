package _01基础._03链表;

/**
 * 删除链表中指定的数的节点
 */
public class List03 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static Node reverse(Node head, int num) {
        //如果头节点的值为给定的值，则一直删除，直到与给定值不等
        while (head != null && head.value == num) {
            head = head.next;
        }

        //此时的head的value必然与num不等
        Node pre = null;
        Node cur = null;

        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

}
