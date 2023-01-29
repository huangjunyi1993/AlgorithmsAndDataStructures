package _04LeetCode精选TOP面试题.堆;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _019合并K个升序链表 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparing(listNode -> listNode.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        ListNode head = null;
        ListNode pre = null;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            if (head == null) {
                head = node;
            }
            if (pre != null) {
                pre.next = node;
            }
            pre = node;
            if (node.next != null) {
                heap.add(node.next);
            }
        }
        return head;
    }
}
