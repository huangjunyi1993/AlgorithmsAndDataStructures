package _04LeetCode精选TOP面试题.链表;

import java.util.List;

/**
 * https://leetcode.cn/problems/odd-even-linked-list/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/11.
 */
public class _134奇偶链表 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null) return null;
            /*
            分两个链表，4个指针，2个头，2个尾
             */
            ListNode oneHead = null;
            ListNode oneTail = null;
            ListNode twoHead = null;
            ListNode twoTail = null;
            ListNode cur = head;
            ListNode next = null;
            int index = 0;
            while (cur != null) {
                next = cur.next;
                cur.next = null;
                if ((index & 1) == 0) {
                    if (oneHead == null) {
                        oneHead = cur;
                        oneTail = cur;
                    } else {
                        oneTail.next = cur;
                        oneTail = cur;
                    }
                } else {
                    if (twoHead == null) {
                        twoHead = cur;
                        twoTail = cur;
                    } else {
                        twoTail.next = cur;
                        twoTail = cur;
                    }
                }
                cur = next;
                index++;
            }
            oneTail.next = twoHead;
            return oneHead;
        }
    }

}
