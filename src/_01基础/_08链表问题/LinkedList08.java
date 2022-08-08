package _01基础._08链表问题;

/**
 * 给定两个链表，有可能有环，也有可能无环，返回两个链表的相交结点，没有相交则返回null
 */
public class LinkedList08 {

    public static Node find(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        //1、找出两个链表的入环结点
        Node loop1 = findLoopNode(head1);
        Node loop2 = findLoopNode(head2);

        //如果loop1和loop2都为null，则退化为寻找两个无环链表相交结点的问题
        if (loop1 == null && loop2 == null) {
            return findIntersectNodeNoLoop(head1, head2);
        }

        //如果loop1和loop2都不为空，则简化为寻找两个有环链表的相交结点问题
        if (loop1 != null && loop2 != null) {
            return findLoopIntersect(head1, loop1, head2, loop2);
        }

        //如果一个链表有环，一个链表无环，不可能相交，返回null
        return null;
    }

    /**
     * 找出两个有环链表的相交结点，没有则返回null
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
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

    /**
     * 找出两个无环链表的相交几点，没有则返回null
     * @param head1
     * @param head2
     * @return
     */
    public static Node findIntersectNodeNoLoop(Node head1, Node head2) {
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

    /**
     * 找出链表入环结点，无环则返回null
     * @param head
     * @return
     */
    public static Node findLoopNode(Node head) {
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
