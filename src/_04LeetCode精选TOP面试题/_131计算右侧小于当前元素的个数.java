package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/10.
 */
public class _131计算右侧小于当前元素的个数 {
    class Solution {
        private class Node {
            int value; // 数值
            int index; // 原数组下标

            public Node(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }
        public List<Integer> countSmaller(int[] nums) {
            // 创建存储答案的容器，并初始化
            List<Integer> res = new ArrayList<>();
            if (nums == null || nums.length == 0) return res;
            for (int i = 0; i < nums.length; i++) {
                res.add(0);
            }
            if (nums.length < 2) return res;

            // 原数组的数封装为node，组成node数组
            Node[] nodes = new Node[nums.length];
            for (int i = 0; i < nums.length; i++) {
                nodes[i] = new Node(nums[i], i);
            }

            // 进行归并排序
            process(nodes, 0, nums.length - 1, res);
            return res;
        }

        /**
         * 通过归并排序，计算每个位置的答案
         * @param nodes
         * @param l
         * @param r
         * @param res
         */
        private void process(Node[] nodes, int l, int r, List<Integer> res) {
            if (l == r) return;
            int m = l + ((r - l) >> 1);
            // 归并排序 拆分
            process(nodes, l , m, res);;
            process(nodes, m + 1, r, res);

            // 归并排序 合并
            merge(nodes, l, m, r, res);
        }

        /**
         * 从右往左归并，左边比右边大是，结算
         * 相等时先merge右边
         * @param nodes
         * @param l
         * @param m
         * @param r
         * @param res
         */
        private void merge(Node[] nodes, int l, int m, int r, List<Integer> res) {
            /*
            归并的方式，
            p1指针指向左组的最右侧
            p2指针指向右组的最右侧
            index指针是merge位置的指针，从右往左
            如果p1的node的值，比p2的大，则p2-m可得出右边比p1小的有多少个，然后p1的node放到index位置
            如果p2的node1的值大于等于p1，则p2的node放入index位置
            如此merge排序处理好一轮

            然后递归返回上一轮
            这一轮的所有元素就会作为一个整体，继续一轮merge
            如此所有的元素都会统计好右边比它小的数有多少个
             */

            Node[] help = new Node[r - l + 1];
            int index = help.length - 1;
            int p1 = m;
            int p2 = r;
            while (p1 >= l && p2 > m) {
                if (nodes[p1].value > nodes[p2].value) {
                    int size = p2 - m;
                    res.set(nodes[p1].index, res.get(nodes[p1].index) + size);
                }
                help[index--] = nodes[p1].value > nodes[p2].value ? nodes[p1--] : nodes[p2--];
            }
            while (p1 >= l) {
                help[index--] = nodes[p1--];
            }
            while (p2 > m) {
                help[index--] = nodes[p2--];
            }
            index = 0;
            for (int i = l; i <= r; i++) {
                nodes[i] = help[index++];
            }
        }
    }
}
