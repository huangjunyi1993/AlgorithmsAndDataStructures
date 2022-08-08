package _01基础._08链表问题;

/**
 * 给定一个链表，如果有环则返回环中的第一个节点，没有则返回null
 */
public class LinkedList05 {

    public static Node find(Node head) {
        if (head == null || head.next == null || head.next.next == null) return null;

        //快慢指针，如果有环，两指针会在环中相遇
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }

        //让fast指针回到头结点，每一次走一步，快慢指针相遇的结点，就是环中第一个节点
        fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;

    }

    private static class Node {
        private int value;
        private Node next;
    }

}
