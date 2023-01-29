package _07LeetCode其他题;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/erect-the-fence/
 * 给定一个数组 trees，其中 trees[i] = [xi, yi] 表示树在花园中的位置。
 * 你被要求用最短长度的绳子把整个花园围起来，因为绳子很贵。只有把 所有的树都围起来，花园才围得很好。
 * 返回恰好位于围栏周边的树木的坐标。
 * Created by huangjunyi on 2023/1/24.
 */
public class _587安装栅栏 {
    class Solution {
        public int[][] outerTrees(int[][] points) {
            int n = points.length;
            int s = 0;
            int[][] stack = new int[n << 1][];
            // x小的排前面，x一样的，y小的排前面
            Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            /*
            凸包问题：
            如果倒数第二个点，到当前点的向量
            在倒数第二个点，到倒数第一个点的向量的右侧，
            倒数第一个点从栈中弹出弹出

            从第一个点到最后一个点搞一波
            然后再从最后一个点到第一个点搞一波
            收集在栈中留下的点，

            排序，去重，返回
             */
            for (int i = 0; i < n; i++) {
                while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                    s--;
                }
                stack[s++] = points[i];
            }
            for (int i = n - 2; i >= 0; i--) {
                while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                    s--;
                }
                stack[s++] = points[i];
            }
            // 去重返回
            Arrays.sort(stack, 0, s, (a, b) -> b[0] == a[0] ? b[1] - a[1] : b[0] - a[0]);
            n = 1;
            for (int i = 1; i < s; i++) {
                // 如果i点，x和y，与i-1点，x和y都一样
                // i点与i-1点，在同一个位置，此时，i点不保留
                if (stack[i][0] != stack[i - 1][0] || stack[i][1] != stack[i - 1][1]) {
                    stack[n++] = stack[i];
                }
            }
            return Arrays.copyOf(stack, n);
        }

        // 叉乘的实现
        // 假设有a、b、c三个点，并且给出每个点的(x,y)位置
        // 从a到c的向量，在从a到b的向量的哪一侧？
        // 如果a到c的向量，在从a到b的向量右侧，返回正数
        // 如果a到c的向量，在从a到b的向量左侧，返回负数
        // 如果a到c的向量，和从a到b的向量重合，返回0
        public int cross(int[] a, int[] b, int[] c) {
            return (b[1] - a[1]) * (c[0] - b[0]) - (b[0] - a[0]) * (c[1] - b[1]);
        }
    }
}
