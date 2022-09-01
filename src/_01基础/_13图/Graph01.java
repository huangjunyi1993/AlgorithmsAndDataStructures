package _01基础._13图;

import _01基础._13图.graph.Edge;
import _01基础._13图.graph.Graph;
import _01基础._13图.graph.Node;

/**
 * 给定一个样本集，初始化一个图结构
 * Created by huangjunyi on 2022/8/30.
 */
public class Graph01 {

    public static Graph create(int[][] samples) {
        Graph graph = new Graph();
        for (int[] sample : samples) {
            int value = sample[0]; //边的权重
            int from = sample[1]; //边的起始点的值
            int to = sample[2]; //边的终点的值
            //如果图中不包含该点，则添加
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            //如果图中不包含该点，则添加
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            //从图中取出边的起始点
            Node fromNode = graph.nodes.get(from);
            //从图中取出边的终点
            Node toNode = graph.nodes.get(to);
            //构建边
            Edge edge = new Edge(value, fromNode, toNode);
            //把终点添加到起始点的邻居节点集合中
            fromNode.nexts.add(toNode);
            //起始点的出度+1
            fromNode.out++;
            //把边放入起始点
            fromNode.edges.add(edge);
            //终点入度+1
            toNode.in++;
            //把边放入图中
            graph.edges.add(edge);
        }
        return graph;
    }

}
