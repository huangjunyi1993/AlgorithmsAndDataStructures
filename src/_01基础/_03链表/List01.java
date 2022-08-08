package _01基础._03链表;

/**
 * 反转单链表
 */
public class List01 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static Node reverse(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next; // 保存当前head节点的后继节点
            head.next = pre; // head的后继指针指向pre节点
            pre = head; // 保存当前head节点的引用到pre
            head = next; // head指向下一个节点
        }

        // head指向null，跳出了循环，此时pre就是原来的尾结点，反转后的新头部节点
        return pre;
    }

}
