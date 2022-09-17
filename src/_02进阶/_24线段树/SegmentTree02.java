package _02进阶._24线段树;

import java.util.*;

/**
 * 线段树应用：掉落的方块
 * https://leetcode.cn/problems/falling-squares/
 * Created by huangjunyi on 2022/9/11.
 */
public class SegmentTree02 {

    private static class SegmentTree {

        private int[] change;
        private boolean[] update;
        private int[] max;

        public SegmentTree(int size) {
            int n = size + 1;
            change = new int[n << 2];
            update = new boolean[n << 2];
            max = new int[n << 2];
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) return max[rt];
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int max = Integer.MIN_VALUE;
            if (L <= mid) {
                max = Math.max(max, query(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                max = Math.max(max, query(L, R, mid + 1, r, (rt << 1) | 1));
            }
            return max;
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[(rt << 1) | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                max[rt << 1] = max[rt];
                max[(rt << 1) | 1] = max[rt];
                change[rt << 1] = change[rt];
                change[(rt << 1) | 1] = change[rt];
                update[rt << 1] = true;
                update[(rt << 1) | 1] = true;
                update[rt] = false;
            }
        }

    }

    private Map<Integer, Integer> index(int[][] positions) {
        Set<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < positions.length; i++) {
            int[] position = positions[i];
            sortedSet.add(position[0]);
            sortedSet.add(position[0] + position[1] - 1);
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        int count = 1;
        for (Integer index : sortedSet) {
            indexMap.put(index, count++);
        }
        return indexMap;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        //positions转换为indexMap，x轴下标 -> 数组下标
        Map<Integer, Integer> indexMap = index(positions);
        SegmentTree segmentTree = new SegmentTree(indexMap.size());
        int max = Integer.MIN_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int[] position = positions[i];
            int L = indexMap.get(position[0]);
            int R = indexMap.get(position[0] + position[1] - 1);
            int height = segmentTree.query(L, R, 1, indexMap.size(), 1) + position[1];
            max = Math.max(height, max);
            res.add(max);
            segmentTree.update(L, R, height, 1, indexMap.size(), 1);
        }
        return res;
    }

}
