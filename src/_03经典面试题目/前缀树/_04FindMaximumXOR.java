package _03经典面试题目.前缀树;

/**
 * https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/description/
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * Created by huangjunyi on 2022/12/25.
 */
public class _04FindMaximumXOR {
    class Solution {
        private class Node {
            private Node[] nexts = new Node[2];
        }
        private class Trie {
            private Node head;

            public Trie() {
                head = new Node();
            }

            public void add(int num) {
                Node cur = head;
                for (int i = 31; i >= 0; i--) {
                    int path = (num >> i) & 1;
                    if (cur.nexts[path] == null) {
                        cur.nexts[path] = new Node();
                    }
                    cur = cur.nexts[path];
                }
            }

            public int maxXOR(int num) {
                int res = 0;
                Node cur = head;
                for (int i = 31; i >= 0; i--) {
                    int bit = (num >> i) & 1;
                    // 最高位符号为何自己异或的结果最大，其他的和自己的相反异或最大
                    int path = i == 31 ? bit : bit ^ 1;
                    int best = cur.nexts[path] == null ? path ^ 1 : path;
                    cur = cur.nexts[best];
                    res |= (bit ^ best) << i;
                }
                return res;
            }
        }

        public int findMaximumXOR(int[] nums) {
            /*
            前缀树
            每遍历一个数num
            调trie.maxXOR(num)方法，快速得到最大前缀和
            然后和答案max PK一下
            然num add到前缀树中
             */
            Trie trie = new Trie();
            trie.add(nums[0]);
            int max = 0;
            for (int i = 1; i < nums.length; i++) {
                int xor = trie.maxXOR(nums[i]);
                max = Math.max(max, xor);
                trie.add(nums[i]);
            }
            return max;
        }
    }
}
