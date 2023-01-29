package _07LeetCode其他题;

import java.util.*;

/**
 * https://leetcode.cn/problems/avoid-flood-in-the-city/
 * Created by huangjunyi on 2023/1/27.
 */
public class _1488避免洪水泛滥 {
    class Solution {
        private class Work {
            private int wake; // 湖泊号
            private int day; // 下雨天

            public Work(int wake, int day) {
                this.wake = wake;
                this.day = day;
            }
        }
        public int[] avoidFlood(int[] rains) {
            /*
            1、建立一个湖泊下雨表，[湖泊: [下雨天]]（HashMap<Integer, LinkedList<Integer>>）
            2、建立一个湖满表（Set）
            3、建立一个工作表（小跟堆）

            1、先初始化湖泊下雨表
            2、遍历rains数组，从湖泊下雨表对应的湖泊弹出链表头，然后该湖泊加入湖满表
            3、如果湖满表已有该湖泊，然后空数组，表示无能为力
            4、否则加入湖满表后，从链表头peek出该湖泊下次下雨的天，加工作表
            5、遇到晴天，从小跟堆工作表，弹出堆顶，抽水
             */
            int[] res = new int[rains.length];
            int[] empty = {};

            // key:湖泊编号，value：下雨的天
            HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
            // 湖满表
            HashSet<Integer> set = new HashSet<>();
            // 工作表
            PriorityQueue<Work> works = new PriorityQueue<>(Comparator.comparingInt(o -> o.day));

            for (int i = 0; i < rains.length; i++) {
                if (!map.containsKey(rains[i])) map.put(rains[i], new LinkedList<>());
                map.get(rains[i]).addLast(i);;
            }

            for (int i = 0; i < rains.length; i++) {
                if (rains[i] != 0) {
                    if (set.contains(rains[i])) return empty;
                    map.get(rains[i]).pollFirst();
                    set.add(rains[i]);
                    res[i] = -1; // 题目规定
                    if (!map.get(rains[i]).isEmpty()) works.add(new Work(rains[i], map.get(rains[i]).peekFirst()));
                } else {
                    if (works.isEmpty()) res[i] = 1; // 题目规定
                    else res[i] = works.poll().wake;
                    set.remove(res[i]);
                }
            }
            return res;
        }
    }
}
