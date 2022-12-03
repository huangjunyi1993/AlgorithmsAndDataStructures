package _01基础._12并查集;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整形m，和整形n，代表m行n列的二维矩阵
 * 起初矩阵中所有元素都是0
 * 再给定一个列数为2的二维数组positions，positions中的每个一维数组，表示矩阵的某个位置，要把该位置修改为1
 * 遍历positions数组，依次修改矩阵中对应位置为1
 * 然后返回每次修改后的岛数量
 * Created by huangjunyi on 2022/11/26.
 */
public class UnionSet04 {
    public static List<Integer> numIslands(int m, int n, int[][] positions) {
        UnionSet unionSet = new UnionSet(m, n);
        List<Integer> res = new ArrayList<>();
        // 遍历positions，添加每个元素到并查集中，并且每一步收集一个答案
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                unionSet.add(i, j);
                res.add(unionSet.sizeNum);
            }
        }
        return res;
    }
    private static class UnionSet {

        int[] parent; // parentMap
        int[] size; // sizeMap
        int[] help; // 代替扁平化时使用的栈
        int sizeNum; // 集合数，相当于岛数量
        int cols; // 列数
        int rows; // 行数

        public UnionSet(int m, int n) {
            // 初始化并查集，二维数组转一维数组
            int len = m * n;
            parent = new int[len];
            size = new int[n];
            help = new int[n];
            sizeNum = 0;
            cols = n;
            rows = m;
        }

        public int index(int r, int c) {
            // 二维数组到一位数组的下标换算
            return r * cols + c;
        }

        private int findHead(int a) {
            int top = 0;
            while (a != parent[a]) {
                // 用数组代替栈，收集沿途经过的节点
                help[top++] = a;
                a = parent[a];
            }
            // 扁平化处理
            for (int i = top-1; i >= 0; i--) {
                parent[help[i]] = a;
            }
            return a;
        }

        private void union(int r1, int c1, int r2, int c2) {
            // 判断是否越界
            if (r1 < 0 || r1 == rows || r2 < 0 || r2 == rows || c1 < 0 || c1 == cols || c2 < 0 || c2 == cols) return;
            int index1 = index(r1, c1);
            int index2 = index(r2, c2);
            // 判断两个节点是否都在并查集中
            if (size[index1] == 0 || size[index2] == 0) return;
            // 找到两个节点的代表节点
            int head1 = findHead(index1);
            int head2 = findHead(index2);
            if (head1 == head2) return;
            // 合并
            if (size[head1] <= size[head2]) {
                parent[head1] = head2;
                size[head2] += size[head1];
            } else {
                parent[head2] = head1;
                size[head1] += size[head2];
            }
            sizeNum--;
        }

        public void add(int r, int c) {
            int index = index(r, c);
            // 已经加入过，直接返回
            if (size[index] != 0) return;
            // 节点加入并查集
            parent[index] = index;
            size[index] = 1;
            sizeNum++;
            // 尝试跟它的上下左右合并
            union(r, c, r - 1, c);
            union(r, c, r + 1, c);
            union(r, c, r, c - 1);
            union(r, c, r, c + 1);
        }

    }
}
