package _01基础._08链表问题;

/**
 * 快慢指针
 */
public class LinkedList01 {

    /**
     * 输入链表头部，奇数长度返回中点，偶数长度返回上中点
     * @param head
     * @return
     */
    public static Node find01(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 输入链表头部，奇数长度返回中点，偶数长度返回下中点
     * @param head
     * @return
     */
    public static Node find02(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 输入链表头部，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * @param head
     * @return
     */
    public static Node find03(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 输入链表头部，奇数长度返回中点前一个，偶数长度返回下中点前一个
     * @param head
     * @return
     */
    public static Node find04(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    private static class Node {
        private Node next;
        private int value;
    }

}
