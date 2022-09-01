package _01基础._13图;

import _01基础._13图.graph.Edge;
import _01基础._13图.graph.Graph;
import _01基础._13图.graph.Node;

import java.util.*;

/**
 * Prim算法求最小生成树
 * 从一个点出发，取与该点相邻的权重最小的边，然后把边加到结果集中
 * 然后取该边对端的点，继续取权重最小的边，放入结果集
 * 访问过的点和边要记录下来，如果取出的权重最小的边已经被记录访问过，则丢弃
 * 如果取出的边对端的点已经被访问过，该边也丢弃
 * 周而复始，直到访问完所有的边
 * Created by huangjunyi on 2022/8/31.
 */
public class Graph06 {

    public static Set<Edge> primMST(Graph graph) {

        Set<Node> visitedNodes = new HashSet<>();
        Set<Edge> visitedEdges = new HashSet<>();
        Set<Edge> result = new HashSet<>();

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

        //图有可能是森林，所以通过for循环遍历
        for (Node node : graph.nodes.values()) {
            if (!visitedNodes.contains(node)) {
                visitedNodes.add(node);
                for (Edge edge : node.edges) {
                    if (!visitedEdges.contains(edge)) {
                        visitedEdges.add(edge);
                        queue.offer(edge);
                    }
                }
                while (!queue.isEmpty()) {
                    Edge currEdge = queue.poll();
                    Node toNode = currEdge.to;
                    if (!visitedNodes.contains(toNode)) {
                        visitedNodes.add(toNode);
                        result.add(currEdge);
                        for (Edge edge : toNode.edges) {
                            if (!visitedEdges.contains(edge)) {
                                visitedEdges.add(edge);
                                queue.offer(edge);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

}
