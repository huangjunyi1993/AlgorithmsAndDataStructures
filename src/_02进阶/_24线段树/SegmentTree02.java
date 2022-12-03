package _02进阶._24线段树;

import java.util.*;

/**
 * 线段树应用：掉落的方块
 * https://leetcode.cn/problems/falling-squares/
 *
 * 在二维平面上的 x 轴上，放置着一些方块。
 * 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi] 表示：第 i 个方块边长为 sideLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。
 * 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。方块沿 y 轴负方向下落，直到着陆到 另一个正方形的顶边 或者是 x 轴上 。一个方块仅仅是擦过另一个方块的左侧边或右侧边不算着陆。一旦着陆，它就会固定在原地，无法移动。
 * 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。
 * 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。
 * Created by huangjunyi on 2022/9/11.
 */
public class SegmentTree02 {

    private static class SegmentTree {

        private int[] change;
        private boolean[] update;
        private int[] max; // 改写线段树，变成max数组，求范围最大值

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

        /**
         * pushUp改写为求左右两边的最大值
         * @param rt 当前节点下标
         */
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
        // 所有方块的左右边界，先排个序
        Set<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < positions.length; i++) {
            int[] position = positions[i];
            sortedSet.add(position[0]);
            // 防止边界重合，左闭右开
            sortedSet.add(position[0] + position[1] - 1);
        }
        // 建立方块边界在数组中下标的映射
        Map<Integer, Integer> indexMap = new HashMap<>();
        int count = 1;
        for (Integer index : sortedSet) {
            indexMap.put(index, count++);
        }
        return indexMap;
    }

    // 入口方法，positions数组[i, j] 表示从i位置落下一个长度为j的方块
    public List<Integer> fallingSquares(int[][] positions) {
        //positions转换为indexMap，x轴下标 -> 数组下标
        Map<Integer, Integer> indexMap = index(positions);
        SegmentTree segmentTree = new SegmentTree(indexMap.size());
        int max = Integer.MIN_VALUE;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int[] position = positions[i]; // 当前任务
            int L = indexMap.get(position[0]); // 任务范围左边界对应的下标
            int R = indexMap.get(position[0] + position[1] - 1); // 任务范围右边界对应的下标
            // 查询在任务范围内的原高度，然后累加上当前任务（方块）要增加的高度
            int height = segmentTree.query(L, R, 1, indexMap.size(), 1) + position[1];
            // PK 最大高度
            max = Math.max(height, max);
            // 收集答案
            res.add(max);
            // 下发更新任务
            segmentTree.update(L, R, height, 1, indexMap.size(), 1);
        }
        return res;
    }

}
