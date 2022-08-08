package _01基础._08链表问题;

/**
 * 给定两个无环链表，返回两个链表相交的第一个节点，没有则返回null
 */
public class LinkedList06 {

    public static Node findIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        //先遍历head1，计算head1长度
        int len = 0;
        Node curr1 = head1;
        while (curr1.next != null) {
            len++;
            curr1 = curr1.next;
        }

        //遍历head2，每遍历一个结点，len减一，计算出两个链表长度的差值
        Node curr2 = head2;
        while (curr2.next != null) {
            len--;
            curr2 = curr2.next;
        }

        //此时curr1和curr2都指向各自链表的尾结点，如果不是同一个尾结点，代表两链表没有相交，返回null
        if (curr1 != curr2) return null;

        //调整curr1和curr2指针，让cuur1指针指向长度较长的链表头结点
        curr1 = len < 0 ? head2 : head1;
        curr2 = curr1 == head1 ? head2 : head1;

        //此时len为两链表长度的差值，保证len为正数
        len = Math.abs(len);

        //curr1先走len步，使得curr1和curr2指向的结点开始到尾节点长度一样
        while (len != 0) {
            len--;
            curr1 = curr1.next;
        }

        //curr1和curr2每次各自走一步，最后必会到达相交处
        while (curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return curr1;
    }

    private static class Node {
        private int value;
        private Node next;
    }

}
