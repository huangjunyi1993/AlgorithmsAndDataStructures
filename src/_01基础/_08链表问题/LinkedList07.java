package _01基础._08链表问题;

/**
 * 两个有环链表，给定链表头结点和环中第一个节点，找出两个链表相交的结点，没有相交则返回null
 */
public class LinkedList07 {

    public static Node findLoopIntersect(Node head1, Node loop1, Node head2, Node loop2) {
        Node curr1 = null;
        Node curr2 = null;
        // 第一种情况：两个链表在环外相交
        if (loop1 == loop2) {
            curr1 = head1;
            curr2 = head2;

            //遍历链表1，计算到入环结点的长度
            int len = 0;
            while (curr1 != loop1) {
                len++;
                curr1 = curr1.next;
            }

            //遍历链表二，也是到入环结点就停止，计算两个链表长度的差值
            while (curr2 != loop2) {
                len--;
                curr2 = curr2.next;
            }

            //调整curr1指向长度较长链表的头结点
            curr1 = len < 0 ? head2 : head1;
            curr2 = curr1 == head1 ? head2 : head1;

            //保证len为正数
            len = Math.abs(len);

            //curr1走len步，此时cuur1到入环结点与curr2到入环结点的长度相同
            while (len > 0) {
                len--;
                curr1 = curr1.next;
            }

            //curr1和curr2每次走1步，到入环结点或入环结点前必会相遇
            while (curr1 != curr2) {
                curr1 = curr1.next;
                curr2 = curr2.next;
            }

            return curr1;

        } else {
            //第二种情况，两个链表的入环结点不相同
            curr1 = loop1.next;
            //从loop1开始遍历链表1，看是否会与loop2相遇，是则两链表相交，返回loop1或loop2都可以，不会相遇则会回到loop1，返回null
            while (curr1 != loop1) {
                if (curr1 == loop2) return loop1;
                curr1 = curr1.next;
            }
            return null;
        }
    }

    private static class Node {
        private int value;
        private Node next;
    }

}
