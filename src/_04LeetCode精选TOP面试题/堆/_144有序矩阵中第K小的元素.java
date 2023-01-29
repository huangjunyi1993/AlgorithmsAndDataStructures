package _04LeetCode精选TOP面试题.堆;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _144有序矩阵中第K小的元素 {
    class Solution {
        class Node {
            int val;
            int row;
            int col;

            public Node(int val, int row, int col) {
                this.val = val;
                this.row = row;
                this.col = col;
            }
        }
        public int kthSmallest(int[][] matrix, int k) {
            int N = matrix.length;
            int M = matrix[0].length;
            /*
            小跟堆
            count计数
            每次重堆顶弹出一个Node
            count++
            记录Node的val
            然后取它下面和右边的数，放入Node到堆中
            count数到k，返回抓到的Node的val
            要用一个boolean[][]记录该数是否访问过
             */
            int count = 0;
            PriorityQueue<Node> head = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
            head.add(new Node(matrix[0][0], 0, 0));
            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;
            Node res = null;
            while (!head.isEmpty()) {
                res = head.poll();
                count++;
                if (count == k) return res.val;
                if (res.row < N - 1 && !visited[res.row + 1][res.col]) {
                    head.add(new Node(matrix[res.row + 1][res.col], res.row + 1, res.col));
                    visited[res.row + 1][res.col] = true;
                }
                if (res.col < M - 1 && !visited[res.row][res.col + 1]) {
                    head.add(new Node(matrix[res.row][res.col + 1], res.row, res.col + 1));
                    visited[res.row][res.col + 1] = true;
                }
            }
            return res.val;
        }
    }
}
