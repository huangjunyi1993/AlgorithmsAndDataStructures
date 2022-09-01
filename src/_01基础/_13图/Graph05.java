package _01基础._13图;

import _01基础._13图.graph.Edge;
import _01基础._13图.graph.Graph;
import _01基础._13图.graph.Node;

import java.util.*;

/**
 * 最小生成树
 * 在不破坏连通性的情况下，返回图中权重最小边的集合
 * 利用kruskal算法生成最小生成树
 * Created by huangjunyi on 2022/8/30.
 */
public class Graph05 {
    static class UnionSet<T> {

        /**
         * 存放子Node到父Node的映射
         */
        private Map<Node, Node> parentMap = new HashMap<>();

        /**
         * 存放根Node与集合大小的映射
         */
        private Map<Node, Integer> sizeMap = new HashMap<>();


        public UnionSet(Collection<Node> values) {
            for (Node node : values) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findRootNode(Node node) {

            Stack<Node> stack = new Stack<>();

            //一直往上寻找，找到找到根Node，沿途的Node记录到stack
            while (parentMap.get(node) != node) {
                stack.push(node);
                node = parentMap.get(node);
            }

            //修改沿途的Node的父节点，使得下次findRootNode的代价更小
            while (!stack.isEmpty()) parentMap.put(stack.pop(), node);

            return node;
        }

        public boolean isSameSet(Node a, Node b) {
            //a对应的Node，和b对应的Node，同时找到根Node，如果是同一个根Node，代表a和b在一个集合中
            return findRootNode(a) == findRootNode(b);
        }

        public void union(Node a, Node b) {
            //a对应的Node，和b对应的Node，同时往上找到根Node
            Node rootNodeA = findRootNode(a);
            Node rootNodeB = findRootNode(b);

            //在同一个集合中，不用合并
            if (rootNodeA == rootNodeB) return;

            int sizeA = sizeMap.get(rootNodeA);
            int sizeB = sizeMap.get(rootNodeB);

            //数量小的集合的根Node的父Node指向数量大的集合的跟Node
            //然后更新sizeMap信息，sizeMap只需要存储根Node与集合大小的映射
            if (sizeA <= sizeB) {
                parentMap.put(rootNodeA, rootNodeB);
                sizeMap.put(rootNodeB, sizeA + sizeB);
                sizeMap.remove(rootNodeA);
            } else {
                parentMap.put(rootNodeB, rootNodeA);
                sizeMap.put(rootNodeA, sizeA + sizeB);
                sizeMap.remove(rootNodeB);
            }

        }

        public int countSet() {
            return sizeMap.size();
        }

    }

    public static Set<Edge> kruskalMST(Graph graph) {

        //根据图中的点构建一个并查集
        UnionSet unionSet = new UnionSet(graph.nodes.values());

        //对所有的边进行排序，权重小的牌前面
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        queue.addAll(graph.edges);

        //每次从堆中取出一条边，如果两个点不在一个集合里面，保留这条边到结果集中，并把两个点在并查集中合并
        Set<Edge> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            if (!unionSet.isSameSet(e.from, e.to)) {
                result.add(e);
                unionSet.union(e.from, e.to);
            }
        }

        return result;
    }

}
