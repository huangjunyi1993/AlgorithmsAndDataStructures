package _01基础._12并查集;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/number-of-provinces/description/
 * Created by huangjunyi on 2022/11/26.
 */
public class UnionSet03 {
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            UnionSet unionSet = new UnionSet(isConnected.length);
            int N = isConnected.length;
            // 遍历矩阵，如果是1，则进行union合并
            // 但是只需要遍历上半区，下半区不需要遍历，因为是对等的
            for (int i = 0; i < N; i++) {
                for (int j = i; j < isConnected[i].length; j++) {
                    if (isConnected[i][j] == 1) unionSet.union(i, j);
                }
            }
            return unionSet.setNum;
        }
        private class UnionSet {
            int[] parent; // 相当于parentMap
            int[] size; // 相当于sizeMap
            int setNum; // 集合个数

            public UnionSet(int n) {
                // 初始化并查集
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    // 一开始父亲都是自己
                    parent[i] = i;
                    // 自己为一个集合
                    size[i] = 1;
                }
                // 初始集合数
                setNum = n;
            }

            private void union(int a, int b) {
                int head1 = findHead(a); // 寻找a所在集合的代表节点
                int head2 = findHead(b); // 寻找b所在集合的代表节点
                if (head1 == head2) return;
                int size1 = size[head1]; // a节点所在集合的大小
                int size2 = size[head2]; // b节点所在集合的大小
                // 小集合代表节点指向大节点代表节点，两个集合合并在一起
                if (size1 <= size2) {
                    parent[head1] = head2;
                    size[head2] = size1 + size2;
                } else {
                    parent[head2] = head1;
                    size[head1] = size1 + size2;
                }
                // 集合数--
                setNum--;
            }

            private int findHead(int a) {
                LinkedList<Integer> stack = new LinkedList<>();
                while (a != parent[a]) {
                    // 收集沿途经过的节点
                    stack.push(a);
                    a = parent[a];
                }
                // 修改沿途节点指向的父节点为代表节点（扁平化）
                while (!stack.isEmpty()) parent[stack.pop()] = a;
                return a;
            }
        }
    }
}
