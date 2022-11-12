package _04LeetCode精选TOP面试题;

/**
 * Created by huangjunyi on 2022/11/3.
 */
public class _080排序链表 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution {
        public ListNode sortList(ListNode head) {
            int N = 0;
            ListNode cur = head;
            while (cur != null) {
                N++;
                cur = cur.next;
            }
            ListNode h = head;
            ListNode pre = null;
            ListNode tempFist = head;
            for (int len = 1; len < N; len <<= 1) {
                while (tempFist != null) {
                    ListNode[] hthtn = hthtn(tempFist, len);
                    ListNode[] mhmt = merge(hthtn[0], hthtn[1], hthtn[2], hthtn[3]);
                    if (tempFist == h) {
                        h = mhmt[0];
                        pre = mhmt[1];
                    } else {
                        pre.next = mhmt[0];
                        pre = mhmt[1];
                    }
                    tempFist = hthtn[4];
                }
                tempFist = h;
                pre = null;
            }
            return h;
        }

        private ListNode[] merge(ListNode ls, ListNode le, ListNode rs, ListNode re) {
            if (rs == null) {
                return new ListNode[]{ls, le};
            }
            ListNode head = null;
            ListNode tail = null;
            ListNode pre = null;
            ListNode cur = null;
            while (ls != le.next && rs != re.next) {
                if (ls.val < rs.val) {
                    cur = ls;
                    ls = ls.next;
                } else {
                    cur = rs;
                    rs = rs.next;
                }
                if (head == null) {
                    head = cur;
                    pre = cur;
                } else {
                    pre.next = cur;
                    pre = cur;
                }
            }
            if (ls != le.next) {
                while (ls != le.next) {
                    pre.next = ls;
                    pre = ls;
                    tail = ls;
                    ls = ls.next;
                }
            } else {
                while (rs != re.next) {
                    pre.next = rs;
                    pre = rs;
                    tail = rs;
                    rs = rs.next;
                }
            }
            return new ListNode[]{head, tail};
        }

        private ListNode[] hthtn(ListNode tempFist, int len) {
            ListNode ls = tempFist;
            ListNode le = tempFist;
            ListNode rs = null;
            ListNode re = null;
            ListNode next = null;
            int pass = 0;
            while (tempFist != null) {
                pass++;
                if (pass <= len) {
                    le = tempFist;
                }
                if (pass == len + 1) {
                    rs = tempFist;
                }
                if (pass > len) {
                    re = tempFist;
                }
                if (pass >= (len << 1)) {
                    break;
                }
                tempFist = tempFist.next;
            }
            le.next = null;
            if (re != null) {
                next = re.next;
                re.next = null;
            }
            return new ListNode[] {ls, le, rs, re, next};
        }
    }
}
