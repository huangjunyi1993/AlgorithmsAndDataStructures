package _07LeetCode其他题;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/perfect-rectangle/
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。
 * 这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 * Created by huangjunyi on 2023/1/21.
 */
public class _391完美矩形 {
    class Solution {
        public boolean isRectangleCover(int[][] rectangles) {
            if (rectangles == null || rectangles.length == 0 || rectangles[0] == null || rectangles[0].length == 0) return false;
            int mostLeft  = Integer.MAX_VALUE;
            int mostDown  = Integer.MAX_VALUE;
            int mostRight = Integer.MIN_VALUE;
            int mostUp    = Integer.MIN_VALUE;
            int area = 0;
            HashMap<Integer, HashMap<Integer, Integer>> pointCountMap = new HashMap<>();
            for (int i = 0; i < rectangles.length; i++) {
                int[] rectangle = rectangles[i];
                // 尝试把四个最外边往外拱
                mostLeft  = Math.min(mostLeft, rectangle[0]);
                mostDown  = Math.min(mostDown, rectangle[1]);
                mostRight = Math.max(mostRight, rectangle[2]);
                mostUp    = Math.max(mostUp, rectangle[3]);
                // 累加每个矩形的面积
                area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
                if (!pointCountMap.containsKey(rectangle[0])) pointCountMap.put(rectangle[0], new HashMap<>());
                if (!pointCountMap.containsKey(rectangle[2])) pointCountMap.put(rectangle[2], new HashMap<>());
                // 左下点
                if (pointCountMap.get(rectangle[0]).containsKey(rectangle[1]))
                    pointCountMap.get(rectangle[0]).put(rectangle[1], pointCountMap.get(rectangle[0]).get(rectangle[1]) + 1);
                else pointCountMap.get(rectangle[0]).put(rectangle[1], 1);
                // 左上点
                if (pointCountMap.get(rectangle[0]).containsKey(rectangle[3]))
                    pointCountMap.get(rectangle[0]).put(rectangle[3], pointCountMap.get(rectangle[0]).get(rectangle[3]) + 1);
                else pointCountMap.get(rectangle[0]).put(rectangle[3], 1);
                // 右下点
                if (pointCountMap.get(rectangle[2]).containsKey(rectangle[1]))
                    pointCountMap.get(rectangle[2]).put(rectangle[1], pointCountMap.get(rectangle[2]).get(rectangle[1]) + 1);
                else pointCountMap.get(rectangle[2]).put(rectangle[1], 1);
                // 右上点
                if (pointCountMap.get(rectangle[2]).containsKey(rectangle[3]))
                    pointCountMap.get(rectangle[2]).put(rectangle[3], pointCountMap.get(rectangle[2]).get(rectangle[3]) + 1);
                else pointCountMap.get(rectangle[2]).put(rectangle[3], 1);
            }
            return (mostRight - mostLeft) * (mostUp - mostDown) == area &&
                    checkPoint(pointCountMap, mostLeft, mostDown, mostRight, mostUp);
        }

        private boolean checkPoint(HashMap<Integer, HashMap<Integer, Integer>> pointCountMap,
                                   int mostLeft, int mostDown, int mostRight, int mostUp) {
            // 最外侧四个点只能出现一次
            if (pointCountMap.get(mostLeft).getOrDefault(mostUp, 0) != 1
                    || pointCountMap.get(mostLeft).getOrDefault(mostDown, 0) != 1
                    || pointCountMap.get(mostRight).getOrDefault(mostUp, 0) != 1
                    || pointCountMap.get(mostRight).getOrDefault(mostDown, 0) != 1) {
                return false;
            }
            pointCountMap.get(mostLeft).remove(mostUp);
            pointCountMap.get(mostLeft).remove(mostDown);
            pointCountMap.get(mostRight).remove(mostUp);
            pointCountMap.get(mostRight).remove(mostDown);
            // 剩下的每一个点出现偶数次
            for (Map.Entry<Integer, HashMap<Integer, Integer>> entry : pointCountMap.entrySet()) {
                for (Integer count : entry.getValue().values()) {
                    if ((count & 1) != 0) return false;
                }
            }
            return true;
        }
    }
}
