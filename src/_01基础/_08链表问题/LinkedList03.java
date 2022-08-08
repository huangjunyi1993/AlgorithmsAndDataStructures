package _01基础._08链表问题;

/**
 * 将单向链表按某值划分为左边小，中间等于，右边大于的形式
 */
public class LinkedList03 {

    public static Node partition01(Node head, int pivot) {
        if (head == null || head.next == null) return head;

        //计算链表大小，并创建对应大小的数组，临时存放链表结点
        Node help = head;
        int size = 0;
        while (help != null) {
            size++;
            head = help.next;
        }
        Node[] arr = new Node[size];
        help = help;
        int index = 0;
        while (help != null) {
            arr[index++] = help;
            help = help.next;
        }

        //根据pivot基准值，对数组做partition操作
        int i1 = -1; //(...i1] 小于区
        int i2 = 0; // (i1...i2] 等于区
        int i3 = arr.length; // (i2...i3]大于区
        while (i2 != i3) {
            Node curr = arr[i2];
            if (curr.value < pivot) {
                swap(arr, ++i1, i2++);
            } else if (curr.value > pivot) {
                swap(arr, i2, --i3);
            } else {
                i2++;
            }
        }

        //遍历数组，把结点串起来
        Node prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prev.next = arr[i];
            prev = arr[i];
        }

        return arr[0];
    }

    public static Node partition02(Node head, int pivot) {
        if (head == null || head.next == null) return head;

        Node smallHead = null; //小于区头结点
        Node smallTail = null; //小于区尾结点
        Node equalsHead = null; //等于区头结点
        Node equalsTail = null; //等于区尾结点
        Node bigHead = null; //大于区头结点
        Node bigTail = null; //大于区尾结点

        //遍历链表，组装小于区、等于区、大于区的链表，并且每遍历一个结点，就把原来的next指针断掉
        Node next;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.value == pivot) {
                if (equalsHead == null) {
                    equalsHead = head;
                    equalsTail = head;
                } else {
                    equalsTail.next = head;
                    equalsTail = head;
                }
            } else {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }

        //小于区的尾部连接等于区的头部，等于区的头部连接大于区的头部，但是要判断边界情况，就是小于区、等于区有可能为空
        if (smallHead != null) {
            smallTail.next = equalsHead;
            equalsTail = equalsHead == null ? smallTail : equalsTail;
        }
        if (equalsHead != null) {
            equalsTail = bigHead;
        }
        return smallHead != null ? smallHead : (equalsHead != null ? equalsHead : bigHead);
    }

    private static class Node {
        private int value;
        private Node next;
    }

    private static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
