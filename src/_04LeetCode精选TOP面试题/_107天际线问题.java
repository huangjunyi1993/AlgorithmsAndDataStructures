package _04LeetCode精选TOP面试题;

import java.util.*;

/**
 * https://leetcode.cn/problems/the-skyline-problem/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _107天际线问题 {
    class Solution {
        private class Node {
            int x;
            boolean isAdd;
            int height;

            public Node(int x, boolean isAdd, int height) {
                this.x = x; // x轴位置
                this.isAdd = isAdd; // 生气还是落下
                this.height = height; // 高度
            }
        }
        public List<List<Integer>> getSkyline(int[][] buildings) {
            // 收集成一个个的Node
            Node[] nodeArr = new Node[buildings.length * 2];
            for (int i = 0; i < buildings.length; i++) {
                nodeArr[2 * i] = new Node(buildings[i][0], true, buildings[i][2]);
                nodeArr[2 * i + 1] = new Node(buildings[i][1], false, buildings[i][2]);
            }
            // 排序，先更加x轴位置排，相同的话，升起排前面，落下排后面
            Arrays.sort(nodeArr, (o1, o2) -> {
                if (o1.x != o2.x) return o1.x - o2.x;
                if (o1.isAdd != o2.isAdd) return o1.isAdd ? -1 : 1;
                return 0;
            });
            // 有序表，记录高度和出现的次数
            TreeMap<Integer, Integer> heightCountMap = new TreeMap<>();
            // 有序表，记录x轴位置和最大高度的对应关系
            TreeMap<Integer, Integer> xMaxHeightMap = new TreeMap<>();
            for (Node node : nodeArr) {
                // 如果是升起，楼高对应次数加1
                if (node.isAdd) {
                    if (heightCountMap.containsKey(node.height)) {
                        heightCountMap.put(node.height, heightCountMap.get(node.height) + 1);
                    } else {
                        heightCountMap.put(node.height, 1);
                    }
                }
                // 如果是落下，楼高对应次数减1
                else {
                    if (heightCountMap.get(node.height) <= 1) {
                        heightCountMap.remove(node.height);
                    } else {
                        heightCountMap.put(node.height, heightCountMap.get(node.height) - 1);
                    }
                }
                // 记录当前x轴位置的最大高度
                if (heightCountMap.isEmpty()) {
                    xMaxHeightMap.put(node.x, 0);
                } else {
                    xMaxHeightMap.put(node.x, heightCountMap.lastKey());
                }
            }
            // 结算轮廓线
            List<List<Integer>> res = new ArrayList<>();
            int preHeight = 0;
            for (Map.Entry<Integer, Integer> entry : xMaxHeightMap.entrySet()) {
                // 楼高发生变化，才记录
                if (res.isEmpty() || entry.getValue() != preHeight) {
                    res.add(new ArrayList<>(Arrays.asList(entry.getKey(), entry.getValue())));
                    preHeight = entry.getValue();
                }
            }
            return res;
        }
    }
}
