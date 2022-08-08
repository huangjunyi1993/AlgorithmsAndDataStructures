package _01基础._08链表问题;

import java.util.Stack;

/**
 * 输入一个链表头结点，判断链表是否是回文结构
 */
public class LinkedList02 {

    public static boolean isReverse01(Node head) {
        if (head == null && head.next == null)  return true;

        Stack<Node> stack = new Stack<>();
        Node help = head;
        //把链表中的所有结点压入栈中
        while (help != null) stack.push(help);
        boolean res = true;
        help = head;
        //此时从栈顶到栈底，结点顺序与链表相反，从链表头部开始遍历，每遍历一个节点，从栈中弹出一个进行比较
        while (!stack.isEmpty()) {
            res &= stack.pop().value == help.value;
            help = help.next;
        }
        return res;
    }

    public static boolean isReverse02(Node head) {
        if (head == null && head.next == null)  return true;

        //使用快慢指针，取到链表中点，只把链表后半部分压入栈中，与前半部分比较
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //把链表后半部分压入栈中
        Stack<Node> stack = new Stack<>();
        Node n2 = slow.next;
        boolean res = true;
        while (n2 != null) {
            stack.push(n2);
            n2 = n2.next;
        }

        //与前半部分比较
        n2 = head;
        while (!stack.isEmpty()) {
            res &= stack.pop().value == n2.value;
            n2 = n2.next;
        }

        return res;
    }

    public static boolean isReverse03(Node head) {
        if (head == null && head.next == null)  return true;

        //使用快慢指针，取到链表中点，把链表后半部分指针反转，然后与前半部分进行比较，笔记完毕后把指针还原
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //把链表后半部分指针反转
        Node n1 = slow;
        Node n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        //前后两半部分进行比较
        n3 = n1;
        n2 = head;
        boolean res = true;
        while (n1 != null && n2 != null) {
            res &= n1.value == n2.value;
            n1 = n1.next;
            n2 = n2.next;
        }

        //链表还原
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    private static class Node {
        private Node next;
        private int value;
    }

}
