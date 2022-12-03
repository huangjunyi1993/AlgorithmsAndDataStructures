package _01基础._06堆与堆排序;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 线段最大重合问题
 * 给定很多线段，每个线段都有两个数[start, end]
 * 表示线段的开始位置和结束位置，左闭右闭区间
 * 规定：
 * 1)线段的开始位置和结束位置都是整数值
 * 2)线段的重合区域必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 * Created by huangjunyi on 2022/11/20.
 */
public class Heap04 {

    private static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int maxCover(int[][] m) {
        if (m == null || m.length < 0) return 0;
        int N = m.length;
        // 线段数组
        Line[] lines = new Line[N];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // 按线段的开始位置从小到大排序
        Arrays.sort(lines, (line1, line2) -> line1.start - line2.start);
        // 小根堆，存放线段的结束位置
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int res = 0;
        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            // 把小于等于当前线段开始位置的结束位置弹出
            while (!heap.isEmpty() && heap.peek() <= line.start) heap.poll();
            heap.add(line.end);
            // 抓住最大值
            res = Math.max(res, heap.size());
        }
        return res;
    }

}
