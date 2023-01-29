package _04LeetCode精选TOP面试题.双指针;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _015删除链表的倒数第N个结点 {


    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode pre = null;
        /*
        每遍历一个节点之前，先把n减1，减到-1，让pre指针指向头指针，
        pre指针跟着cur指针一起往后遍历
        从循环出来后
        把pre指针后面的阶段删除
         */
        while (cur != null) {
            n--;
            if (n == -1) {
                pre = head;
            } else {
                if (pre != null) pre = pre.next;
            }
            cur = cur.next;
        }
        if (n > 0) {
            return head;
        }
        if (pre == null) {
            return head.next;
        }
        pre.next = pre.next.next;
        return head;
    }
}
