package _04LeetCode精选TOP面试题.归并;

import java.util.List;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _017合并两个有序链表 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /*
        * 类似于归并排序的做法
        * */
        ListNode head = null;
        ListNode pre = null;
        ListNode cur = null;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                cur = list2;
                list2 = list2.next;
            }
            else if (list2 == null) {
                cur = list1;
                list1 = list1.next;
            }
            else {
                if (list1.val < list2.val) {
                    cur = list1;
                    list1 = list1.next;
                } else {
                    cur = list2;
                    list2 = list2.next;
                }
            }
            if (head == null) {
                head = cur;
                pre = cur;
            } else {
                pre.next = cur;
                pre = cur;
            }
        }
        return head;
    }

}
