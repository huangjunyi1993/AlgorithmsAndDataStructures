package _01基础._13图;

import _01基础._13图.graph.Graph;
import _01基础._13图.graph.Node;

import java.util.*;

/**
 * 拓扑排序
 * 拓扑排序的图是有向无环图图
 * Created by huangjunyi on 2022/8/30.
 */
public class Graph04 {

    public static List<Node> sort(Graph graph) {
        // 入度表 节点=>剩余入度
        Map<Node, Integer> inMap = new HashMap<>();
        // 剩余入度为0的节点，会入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 初始化zeroInQueue，入度为0的点入队列
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.offer(node);
            }
        }

        List<Node> result = new ArrayList<>();

        while (!zeroInQueue.isEmpty()) {
            // 弹出zeroInQueue的一个节点，放入结果
            Node curr = zeroInQueue.poll();
            result.add(curr);
            // 把当前节点的邻居节点的入度-1
            for (Node next : curr.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                // 如果节点入度减到0，入队列
                if (inMap.get(next) == 0) {
                    zeroInQueue.offer(next);
                }
            }
        }

        return result;
    }

}
