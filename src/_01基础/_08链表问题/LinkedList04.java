package _01基础._08链表问题;

import java.util.HashMap;
import java.util.Map;

/**
 * 链表结点带rand指针克隆问题
 */
public class LinkedList04 {

    public static Node clone01(Node head) {
        if (head == null) return null;

        //原结点 -> 克隆结点
        Map<Node, Node> map = new HashMap<>();

        //建立原结点与克隆结点的映射关系
        Node help = head;
        while (help != null) {
            Node copy = new Node();
            copy.value = help.value;
            map.put(help, copy);
            help = help.next;
        }

        //根据map中记录的原结点与克隆结点的映射关系，给克隆结点的next指针与rand指针赋值
        help = head;
        while (help != null) {
            Node copy = map.get(help);
            if (help.next != null) copy.next = map.get(help.next);
            if (help.rand != null) copy.rand = map.get(help.rand);
            help = help.next;
        }

        return map.get(head);
    }

    public static Node clone02(Node head) {

        //1、遍历列表，克隆结点，并把克隆结点连接到原结点后面
        //1->1`->2->2`->3->3`
        Node help = head;
        while (help != null) {
            Node next = help.next;
            Node copy = new Node();
            copy.value = help.value;
            help.next = copy;
            copy.next = next;
            help = next;
        }

        //2、遍历链表，处理克隆结点的rand指针
        help = head;
        while (help != null) {
            Node next = help.next.next;
            Node copy = help.next;
            if (help.rand != null) copy.rand = help.rand.next;
            help = next;
        }

        //3、链表分离
        Node res = help.next;
        help = head;
        while (help != null) {
            Node next = help.next.next;
            Node copy = help.next;
            help.next = next;
            if (next != null) copy.next = next.next;
            help = next;
        }

        return res;
    }

    private static class Node{
        private int value;
        private Node next;
        private Node rand;
    }

}
