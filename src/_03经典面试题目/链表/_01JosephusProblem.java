package _03经典面试题目.链表;

/**
 * 约瑟夫环
 * Created by huangjunyi on 2022/10/7.
 */
public class _01JosephusProblem {

    private static class Node {
        private int value;
        private Node next;
    }

    public static Node josephus(Node head, int m){
        if (head == null || head.next == head || m < 1) return head;
        Node cur = head.next;
        int size = 1;
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        // 生还者编号
        int live = getLive(size, m);
        while (--live != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    /**
     * 当前有i个人，数到m则杀掉，最后生还者的编号
     *
     * y = x % i
     * => 左加右减、上加下减
     * 编号 = (数 - 1) % i + 1
     * => 杀掉编号S后的编号 左加右减、上加下减
     * 旧编号 = (新编号 + S - 1) % i + 1
     * S = 数到m的编号 = (数 - 1) % i + 1 = (m - 1) % i + 1
     * =>
     * 旧编号 = (新编号 + (m - 1) % i + 1 - 1) % i + 1
     * 旧编号 = (新编号 + (m - 1) % i) % i + 1
     * => m - 1 = k * i + r
     * 旧编号 = (新编号 + (k * i + r) % i) % i + 1
     * 旧编号 = (新编号 + r % i) % i + 1 = (新编号 + r) % i + 1
     *
     * 旧编号 = (新编号 + m - 1) % i + 1
     * 旧编号 = (新编号 + k * i + r) % i + 1 = (新编号  + r) % i + 1
     *
     * 旧编号 = (新编号 + m - 1) % i + 1
     *
     * @param i 人数
     * @param m 数到m杀掉
     * @return
     */
    private static int getLive(int i, int m) {
        if (i == 1) return 1;
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

}
