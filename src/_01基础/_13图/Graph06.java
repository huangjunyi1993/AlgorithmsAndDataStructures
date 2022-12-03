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

        // 记录访问过的节点
        Set<Node> visitedNodes = new HashSet<>();
        // 记录访问过的边
        Set<Edge> visitedEdges = new HashSet<>();
        // 最小生成树的边集
        Set<Edge> result = new HashSet<>();

        // 解锁了的边会放到小根堆中，按权重从小到大排序
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

        //图有可能是森林（多个互相不连通的图），所以通过for循环遍历
        for (Node node : graph.nodes.values()) {
            if (!visitedNodes.contains(node)) {
                // 如果一个节点没有访问过，访问，并看看边是否没有访问过，如果没有访问过，解锁
                visitedNodes.add(node);
                for (Edge edge : node.edges) {
                    if (!visitedEdges.contains(edge)) {
                        visitedEdges.add(edge);
                        queue.offer(edge);
                    }
                }
                // 遍历，直到小根堆为空，这一个子图就遍历完了
                while (!queue.isEmpty()) {
                    // 小根堆弹出已解锁的权值最小的边
                    Edge currEdge = queue.poll();
                    // 访问to节点
                    Node toNode = currEdge.to;
                    // 如果to节点没有访问过，访问，并看看边是否没有访问过，如果没有访问过，解锁
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
