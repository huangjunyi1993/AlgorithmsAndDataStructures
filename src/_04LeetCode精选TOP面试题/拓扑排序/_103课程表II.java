package _04LeetCode精选TOP面试题.拓扑排序;

import java.util.*;

/**
 * https://leetcode.cn/problems/course-schedule-ii/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _103课程表II {
    class Solution {
        private class Node {
            int no;
            int in;
            List<Node> nexts;

            public Node(int no) {
                this.no = no;
                nexts = new ArrayList<>();
            }
        }
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if (prerequisites == null || prerequisites.length == 0) {
                int[] res = new int[numCourses];
                for (int i = 0; i < res.length; i++) {
                    res[i] = --numCourses;
                }
                return res;
            }
            // 构建图
            Map<Integer, Node> map = new HashMap<>();
            for (int[] prerequisite : prerequisites) {
                int to = prerequisite[0];
                int from = prerequisite[1];
                if (!map.containsKey(to)) {
                    map.put(to, new Node(to));
                }
                if (!map.containsKey(from)) {
                    map.put(from, new Node(from));
                }
                map.get(from).nexts.add(map.get(to));
                map.get(to).in++;
            }

            // 先安排好每一依赖关系的课程
            int[] res = new int[numCourses];
            int index = 0;
            while (numCourses != 0) {
                numCourses--;
                if (map.containsKey(numCourses)) continue;
                res[index++] = numCourses;
            }

            // 需要消除的node个数
            int need = map.size();

            // 入队为零的点，入队列
            Queue<Node> zeroInQueue = new LinkedList<>();
            for (Node node : map.values()) {
                if (node.in == 0) zeroInQueue.offer(node);
            }

            // 已消除的node个数
            int count = 0;
            // 弹出入度为0的点，结算，直到队列为空
            while (!zeroInQueue.isEmpty()) {
                Node cur = zeroInQueue.poll();
                res[index++] = cur.no;
                count++;
                for (Node next : cur.nexts) {
                    // 邻居点入度减一，减到0了，入队
                    next.in--;
                    if (next.in == 0) {
                        zeroInQueue.offer(next);
                    }
                }
            }
            return count == need ? res : new int[0];
        }
    }
}
