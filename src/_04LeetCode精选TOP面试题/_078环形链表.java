package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/linked-list-cycle/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/2.
 */
public class _078环形链表 {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public boolean hasCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }
    }
}
