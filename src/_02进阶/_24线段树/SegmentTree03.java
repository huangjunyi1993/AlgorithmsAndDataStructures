package _02进阶._24线段树;

import java.util.*;

/**
 * 给定一个二维数组lines，代表一组线段，有开始和接收两个位置
 * 求该组线段中最大重叠层数
 * Created by huangjunyi on 2022/9/11.
 */
public class SegmentTree03 {

    private static class Line {
        int start;
        int end;
    }

    public static int getMaxCover(int[][] lines) {
        Line[] lineArr = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = new Line();
            line.start = lines[0][0];
            line.end = lines[0][1];
            lineArr[i] = line;
        }
        //按照线段的开始位置从小到大进行排序
        Arrays.sort(lineArr, Comparator.comparingInt(o -> o.start));
        //按照线段的结束位置组织小顶堆
        PriorityQueue<Line> head = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
        int max = Integer.MIN_VALUE;
        for (Line line : lineArr) {
            //从堆中弹出结束位置覆盖不到当前线段的线段
            while (!head.isEmpty() && head.peek().end <= line.start) head.poll();
            //当前线段入堆
            head.offer(line);
            //堆中大小就是当前线段的重叠层数
            max = Math.max(max, head.size());
        }
        return max;
    }

}
