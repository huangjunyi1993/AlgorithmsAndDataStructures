package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/4.
 */
public class _085相交链表 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int n = 0;
            ListNode curA = headA;
            while (curA.next != null) {
                n++;
                curA = curA.next;
            }
            ListNode curB = headB;
            while (curB.next != null) {
                n--;
                curB = curB.next;
            }
            if (curA != curB) return null;
            curA = n > 0 ? headA : headB;
            curB = curA == headA ? headB : headA;
            n = Math.abs(n);
            // 长链表指针先走完差值
            while (n > 0) {
                n--;
                curA = curA.next;
            }
            // 两指针共同走，直到相遇
            while (curA != curB) {
                curA = curA.next;
                curB = curB.next;
            }
            return curA;
        }
    }

}
