package _06LeetCode热题HOT100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/queue-reconstruction-by-height/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _406根据身高重建队列 {
    class Solution {
        class Node {
            int h; // 身高
            int k; // 前面要有几个比她高的人

            public Node(int h, int k) {
                this.h = h;
                this.k = k;
            }
        }
        public int[][] reconstructQueue(int[][] people) {
            Node[] nodes = new Node[people.length];
            for (int i = 0; i < people.length; i++) {
                nodes[i] = new Node(people[i][0], people[i][1]);
            }
            // 1、按身高降序排序，身高相同按指标（前面要有几个比它大的人）升序排序
            Arrays.sort(nodes, ((o1, o2) -> {
                if (o1.h != o2.h) return o2.h - o1.h;
                else return o1.k - o2.k;
            }));
            // 2、插入到链表中指标指定的位置
            LinkedList<Node> list = new LinkedList<>();
            for (Node node : nodes) {
                list.add(node.k, node);
            }
            int[][] res = new int[people.length][2];
            int index = 0;
            for (Node node : list) {
                res[index++] = new int[]{node.h, node.k};
            }
            return res;
        }
    }
}
