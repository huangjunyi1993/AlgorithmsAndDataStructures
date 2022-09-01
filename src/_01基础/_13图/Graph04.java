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
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.offer(node);
            }
        }

        List<Node> result = new ArrayList<>();

        while (!zeroInQueue.isEmpty()) {
            Node curr = zeroInQueue.poll();
            result.add(curr);
            for (Node next : curr.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.offer(next);
                }
            }
        }

        return result;
    }

}
