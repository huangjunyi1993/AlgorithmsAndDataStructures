package _03经典面试题目.深度优先遍历宽度优先遍历;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/shortest-bridge/
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 * 岛是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 * 返回必须翻转的 0 的最小数目。
 * Created by huangjunyi on 2023/1/7.
 */
public class _05ShortestBridge {
    class Solution {
        class Point {
            int x;
            int y;
            int level;

            public Point(int x, int y, int level) {
                this.x = x;
                this.y = y;
                this.level = level;
            }
        }

        public int shortestBridge(int[][] grid) {
            int N = grid.length;
            int M = grid[0].length;
            int[][][] records = new int[2][N][M];

            // 第一片岛的广播
            for (int i = 0; i < N; i++) {
                boolean flag = false;
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1) {
                        LinkedList<Point> queue = new LinkedList<>();
                        // 把岛全遍历一遍，变成2，收一个队列
                        visite(grid, i, j, N, M, queue, records[0]);
                        // 开始根据队列逐层往外遍历，每一层收一个新队列
                        while (!queue.isEmpty()) {
                            LinkedList<Point> nextQueue = new LinkedList<>();
                            bfs(N, M, queue, nextQueue, records[0]);
                            queue = nextQueue;
                        }
                        flag = true;
                        break;
                    }
                }
                // 第一片岛广播完，收了一个record，就退出
                if (flag) break;
            }

            // 第二片岛的广播
            for (int i = 0; i < N; i++) {
                boolean flag = false;
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1) {
                        LinkedList<Point> queue = new LinkedList<>();
                        // 把岛全遍历一遍，变成2，收一个队列
                        visite(grid, i, j, N, M, queue, records[1]);
                        // 开始根据队列逐层往外遍历，每一层收一个新队列
                        while (!queue.isEmpty()) {
                            LinkedList<Point> nextQueue = new LinkedList<>();
                            bfs(N, M, queue, nextQueue, records[1]);
                            queue = nextQueue;
                        }
                        flag = true;
                        break;
                    }
                }
                // 第二片岛广播完，收了一个record，就退出
                if (flag) break;
            }

            // 两个record相同位置相加，中间距离，所有中间距离PK出min
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    min = Math.min(min, records[0][i][j] + records[1][i][j]);
                }
            }

            // 中间距离还要减3，因为两个点不算，两个record对应，要减1个
            return min - 3;
        }

        private void bfs(int N, int M,
                         LinkedList<Point> queue, LinkedList<Point> nextQueue,
                         int[][] record) {
            while (!queue.isEmpty()) {
                Point curPoint = queue.poll();
                if (curPoint.x - 1 >= 0 && record[curPoint.x - 1][curPoint.y] == 0) {
                    nextQueue.offer(new Point(curPoint.x - 1, curPoint.y, curPoint.level + 1));
                    record[curPoint.x - 1][curPoint.y] = curPoint.level + 1;
                }
                if (curPoint.x + 1 < N && record[curPoint.x + 1][curPoint.y] == 0) {
                    nextQueue.offer(new Point(curPoint.x + 1, curPoint.y, curPoint.level + 1));
                    record[curPoint.x + 1][curPoint.y] = curPoint.level + 1;
                }
                if (curPoint.y - 1 >= 0 && record[curPoint.x][curPoint.y - 1] == 0) {
                    nextQueue.offer(new Point(curPoint.x, curPoint.y - 1, curPoint.level + 1));
                    record[curPoint.x][curPoint.y - 1] = curPoint.level + 1;
                }
                if (curPoint.y + 1 < M && record[curPoint.x][curPoint.y + 1] == 0) {
                    nextQueue.offer(new Point(curPoint.x, curPoint.y + 1, curPoint.level + 1));
                    record[curPoint.x][curPoint.y + 1] = curPoint.level + 1;
                }
            }
        }

        private void visite(int[][] grid, int i, int j,
                            int N, int M,
                            LinkedList<Point> queue, int[][] record) {
            if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != 1) return;
            queue.offer(new Point(i, j, 1));
            grid[i][j] = 2;
            record[i][j] = 1;
            visite(grid, i + 1, j, N, M, queue, record);
            visite(grid, i - 1, j, N, M, queue, record);
            visite(grid, i, j + 1, N, M, queue, record);
            visite(grid, i, j - 1, N, M, queue, record);
        }
    }
}
