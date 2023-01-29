package _04LeetCode精选TOP面试题.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.cn/problems/merge-intervals/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _039合并区间 {
    private static class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];
        Range[] arr = new Range[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            arr[i] = new Range(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o.start));
        List<Range> list = new ArrayList<>();
        int s = arr[0].start;
        int e = arr[0].end;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].start > e) {
                list.add(new Range(s, e));
                s = arr[i].start;
                e = arr[i].end;
            } else {
                e = Math.max(e, arr[i].end);
            }
        }
        list.add(new Range(s, e));
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = new int[]{list.get(i).start, list.get(i).end};
        }
        return res;
    }
}
