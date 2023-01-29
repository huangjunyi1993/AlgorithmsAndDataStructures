package _04LeetCode精选TOP面试题.链表;

/**
 * https://leetcode.cn/problems/add-two-numbers/
 *
 * Created by huangjunyi on 2022/10/22.
 */
public class _002两数相加 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curr = null;
        int cal = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int curVal = cal + v1 + v2;
            ListNode listNode = new ListNode(curVal % 10);
            if (head == null) head = listNode;
            if (curr == null) curr = listNode;
            else {
                curr.next = listNode;
                curr = listNode;
            }
            cal = curVal / 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (cal != 0) {
            ListNode listNode = new ListNode(cal);
            curr.next = listNode;
        }
        return head;
    }
}
