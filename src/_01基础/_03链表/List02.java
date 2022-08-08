package _01基础._03链表;

/**
 * 反转双向链表
 */
public class List02 {

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    public static DoubleNode reverse(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next; //保存head的下一个节点
            head.last = next; //修改head的last指针指向原next节点
            head.next = pre; //修改head的next指针指向原pre指针
            pre = head; //当前head节点赋值给pre
            head = next; //遍历下一个节点
        }

        return pre;
    }

}
