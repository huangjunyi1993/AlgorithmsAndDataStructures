package _01基础._13图;

import _01基础._13图.graph.Edge;
import _01基础._13图.graph.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Dijkstra算法求一个起始点到所有点的最短距离
 * 1、定义一个堆，并且该堆可以调整元素的value（到原点的距离），然后重写排序
 2、一开始先翻入原点到堆中，并且距离是0
 3、循环判断堆是否为空，不为空则弹出一个节点并进行堆调整，此时该节点到原点的距离就是最短距离，放入结果集中
 4、取出该点的所有邻接点，遍历并判断邻接点是否未进入过堆，是则直接翻入堆中然后进行堆调整；如果该节点在堆中但是未弹出，则判断当前算出的距离（当前弹出的node与原点的距离 + 该邻接点与当前弹出结点间的边的权重）是否更小，是则更新该节点在堆中记录的value（与原点的距离）并进行堆调整；如果以上两个条件不满足，表示该节点曾经进入过堆并且已经弹出，忽略该节点
 * Created by huangjunyi on 2022/8/31.
 */
public class Graph07 {

    private static class NodeHeap {
        private Node[] heap;
        private Map<Node, Integer> distanceMap;
        private Map<Node, Integer> indexMap;
        private int size;

        public NodeHeap(int size) {
            this.size = size;
            this.heap = new Node[size];
            this.distanceMap = new HashMap<>();
            this.indexMap = new HashMap<>();
        }

        /**
         * 往堆中放入节点，如果存在则更新，如果已经已经出队则忽略
         * @param node
         * @param distance
         */
        public void push(Node node, int distance) {
            if (!indexMap.containsKey(node)) {
                //如果indexMap中的key不包含该node，则表示该node从未进过堆，直接添加
                heap[size] = node;
                distanceMap.put(node, distance);
                indexMap.put(node, size);
                floatUp(size++);
            } else if (indexMap.get(node) != -1 && distance < distanceMap.get(node)) {
                //如果indexMap中的key包含该node，且value不等于-1，则表示该node在堆中未弹出
                //如果该node与起始点新的距离比distanceMap中记录的小，则更新distanceMap同时进行堆调整
                distanceMap.put(node, distance);
                floatUp(indexMap.get(node));
            }
        }

        /**
         * 弹出堆顶节点，返回其与起始点的最小距离
         * @return
         */
        public PopNode pop() {
            PopNode popNode = new PopNode(heap[0], distanceMap.get(heap[0]));
            distanceMap.remove(heap[0]);
            indexMap.put(heap[0], -1);
            swap(0, size - 1);
            heap[--size] = null;
            sink(0);
            return popNode;
        }

        /**
         * 堆向下调整
         * @param i
         */
        private void sink(int i) {
            int left = i * 2 + 1;
            while (left < size) {
                int smallChild = distanceMap.get(heap[left]) < distanceMap.get(heap[left + 1]) ? left : left + 1;
                if (distanceMap.get(heap[smallChild]) < distanceMap.get(heap[i])) {
                    swap(smallChild, i);
                    i = smallChild;
                    left = i * 2 + 1;
                } else {
                    break;
                }
            }
        }

        /**
         * 堆向上调整
         * @param i
         */
        private void floatUp(int i) {
            while (i > 0) {
                if (distanceMap.get(heap[i]) < distanceMap.get(heap[(i - 1) / 2])) {
                    swap(i, (i - 1) / 2);
                    i = (i - 1) / 2;
                } else {
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            indexMap.put(heap[i], j);
            indexMap.put(heap[j], i);
            Node temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }

    private static class PopNode {
        private Node node;
        private int distance;

        public PopNode(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static Map<Node, Integer> dijkstra(Node head, int size) {
        //创建一个堆结构
        NodeHeap nodeHeap = new NodeHeap(size);
        /**
         * push方法三种处理：
         * 1、如果该节点未进入过堆，则直接添加，并进行堆调整
         * 2、如果该节点进入过堆，但是未弹出，如果与起始点新的距离更短，则更新距离并调整堆
         * 3、如果该节点进入过堆，但是已经弹出，直接忽略
         */
        nodeHeap.push(head, 0);
        Map<Node, Integer> result = new HashMap<>();
        //循环直到堆为空
        while (!nodeHeap.isEmpty()) {
            //弹出的就是最终该节点与起始点的最短距离
            PopNode popNode = nodeHeap.pop();
            result.put(popNode.node, popNode.distance);
            //遍历该节点的邻接边，调用push方法尝试放入或更新对端的点
            for (Edge edge : popNode.node.edges) {
                nodeHeap.push(edge.to, popNode.distance + edge.value);
            }
        }
        return result;
    }

}
