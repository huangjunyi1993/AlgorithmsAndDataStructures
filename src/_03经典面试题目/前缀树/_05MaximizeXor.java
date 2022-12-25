package _03经典面试题目.前缀树;

/**
 * https://leetcode.cn/problems/maximum-xor-with-an-element-from-array/description/
 *
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。
 * 换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 * 
 * Created by huangjunyi on 2022/12/25.
 */
public class _05MaximizeXor {
    class Solution {
        private class Node {
            private int min = Integer.MAX_VALUE;
            private Node[] nexts = new Node[2];
        }
        private class Trie {
            private Node head;

            public Trie() {
                head = new Node();
            }

            public void add(int num) {
                Node cur = head;
                // 更新当前子树的最小值
                cur.min = Math.min(cur.min, num);
                for (int i = 30; i >= 0; i--) {
                    int path = (num >> i) & 1;
                    if (cur.nexts[path] == null) {
                        cur.nexts[path] = new Node();
                    }
                    cur = cur.nexts[path];
                    // 更新当前子树的最小值
                    cur.min = Math.min(cur.min, num);
                }
            }

            public int maxXOR(int xi, int mi) {
                // 整棵树中的最小值，都超出num，返回-1
                if (head.min > mi) return -1;
                int res = 0;
                Node cur = head;
                for (int i = 30; i >= 0; i--) {
                    int bit = (xi >> i) & 1;
                    int best = bit ^ 1;
                    // 没路，或者超了，走另外一条
                    if (cur.nexts[best] == null || cur.nexts[best].min > mi) {
                        best = best ^ 1;
                    }
                    cur = cur.nexts[best];
                    res |= (bit ^ best) << i;
                }
                return res;
            }
        }
        public int[] maximizeXor(int[] nums, int[][] queries) {
            // 一开始先把所有数放入前缀树
            Trie trie = new Trie();
            for (int i = 0; i < nums.length; i++) {
                trie.add(nums[i]);
            }
            // 一个个查
            int[] res = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                res[i] = trie.maxXOR(queries[i][0], queries[i][1]);
            }
            return res;
        }
    }
}
