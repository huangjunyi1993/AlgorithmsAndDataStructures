package _04LeetCode精选TOP面试题.链表;

/**
 * https://leetcode.cn/problems/reverse-linked-list/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _100反转链表 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode next = null;
            ListNode cur = head;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

}
