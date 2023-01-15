package _03经典面试题目.堆;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 如果给你一个二维数组，每一个值表示这一块地形的高度，求整块地形能装下多少水。
 * https://leetcode.cn/problems/trapping-rain-water-ii/
 * <a>https://img-blog.csdnimg.cn/20210622222934916.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3lzaHVvbw==,size_16,color_FFFFFF,t_70<a/>
 * Created by huangjunyi on 2022/9/18.
 */
public class _01ZhuangShui {

    private static class Node {
        private int value;
        private int row;
        private int column;

        public Node(int value, int row, int column) {
            this.value = value;
            this.row = row;
            this.column = column;
        }
    }

    public static int trapRainWater(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length ==0) return 0;
        int R = arr.length;
        int C = arr[0].length;
        /*
        通过小跟堆求解，
        先把边界上的值都封装成Node放入堆中
        然后用一个boolean类型的二维数据记录该点是否访问过
         */
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < C - 1; i++) {
            heap.add(new Node(arr[0][i], 0, i));
            visited[0][i] = true;
        }
        for (int i = 0; i < R - 1; i++) {
            heap.add(new Node(arr[i][C - 1], i, C - 1));
            visited[i][C - 1] = true;
        }
        for (int i = C - 1; i > 0; i--) {
            heap.add(new Node(arr[R - 1][i], R - 1, i));
            visited[R - 1][i] = true;
        }
        for (int i = R - 1; i > 0; i--) {
            heap.add(new Node(arr[i][0], i, 0));
            visited[i][0] = true;
        }
        int max = Integer.MIN_VALUE;
        int water = 0;
        /*
        遍历直到堆为空
        每次弹出一个节点，尝试更新max
        然后看它的上下左右四个临近点是否访问过，
        如果没有访问过，则max-临近点value得出水量累加到结果中
        然后把临近店封装为Node放入堆中，记录该临近店访问过
         */
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            if (cur.row > 0 && !visited[cur.row - 1][cur.column]) {
                visited[cur.row - 1][cur.column] = true;
                water += Math.max(0, max - arr[cur.row - 1][cur.column]);
                heap.add(new Node(arr[cur.row - 1][cur.column], cur.row - 1, cur.column));
            }
            if (cur.row < R - 1 && !visited[cur.row + 1][cur.column]) {
                visited[cur.row + 1][cur.column] = true;
                water += Math.max(0, max - arr[cur.row + 1][cur.column]);
                heap.add(new Node(arr[cur.row + 1][cur.column], cur.row + 1, cur.column));
            }
            if (cur.column > 0 && !visited[cur.row][cur.column - 1]) {
                visited[cur.row][cur.column - 1] = true;
                water += Math.max(0, max - arr[cur.row][cur.column - 1]);
                heap.add(new Node(arr[cur.row][cur.column - 1], cur.row, cur.column - 1));
            }
            if (cur.column < C - 1 && !visited[cur.row][cur.column + 1]) {
                visited[cur.row][cur.column + 1] = true;
                water += Math.max(0, max - arr[cur.row][cur.column + 1]);
                heap.add(new Node(arr[cur.row][cur.column + 1], cur.row, cur.column + 1));
            }
        }
        return water;
    }

}
