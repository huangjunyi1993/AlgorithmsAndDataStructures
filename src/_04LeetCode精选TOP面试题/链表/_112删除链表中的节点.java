package _04LeetCode精选TOP面试题.链表;

/**
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _112删除链表中的节点 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }

}
