package _04LeetCode精选TOP面试题;

import java.util.List;
import java.util.TreeSet;

/**
 *
 * https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/description/
 *
 * Created by huangjunyi on 2023/1/14.
 */
public class _154最小区间 {
    class Solution {
        private class Node {
            int value;
            int arrid;
            int index;

            public Node(int value, int arrid, int index) {
                this.value = value;
                this.arrid = arrid;
                this.index = index;
            }
        }
        public int[] smallestRange(List<List<Integer>> nums) {
            int N = nums.size();
        /*
        通过有序表求解

        有序表节点记录：值、来自哪个数组、下标
        有序表根据节点中的数值value进行升序排序，然后value相同根据数组id排序

        一开始先从每个数组中选0位置数组成Node放入有序表
        然后：
        每次那有序出的表头表尾，相减，看区间范围，区间更小则更新
        每次弹出表头节点，然后把表头节点所在数组的下一个数，入有序表
         */
            TreeSet<Node> orderSet = new TreeSet<>((o1, o2) -> o1.value != o2.value ? o1.value - o2.value : o1.arrid - o2.arrid);
            for (int i = 0; i < N; i++) {
                orderSet.add(new Node(nums.get(i).get(0), i, 0));
            }
            boolean set = false;
            int a = Integer.MIN_VALUE;
            int b = Integer.MAX_VALUE;
            while (orderSet.size() == N) {
                int min = orderSet.first().value;
                int max = orderSet.last().value;
                if (!set || (max - min) < (b - a)) {
                    set = true;
                    a = min;
                    b = max;
                }
                Node node = orderSet.pollFirst();
                if (node.index + 1 < nums.get(node.arrid).size()) {
                    orderSet.add(new Node(nums.get(node.arrid).get(node.index + 1), node.arrid, node.index + 1));
                }
            }
            return new int[] {a, b};
        }
    }
}
