package _01基础._12并查集;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 * 有如果样本，类型为T
 * 一开始每个都是自己为一个集合
 * 有两个方法：isSameSet、union
 * isSameSet(T a, T b)，返回两个样本是否在一个集合中
 * union(T a, T b)，把a样本和b样本所在的集合合并成一个集合
 * 用最小代价实现isSameSet、union两个方法
 */
public class UnionSet01<T> {

    /**
     * 存放value到Node的映射
     */
    private Map<T, Node<T>> nodeMap = new HashMap();

    /**
     * 存放子Node到父Node的映射
     */
    private Map<Node<T>, Node<T>> parentMap = new HashMap<>();

    /**
     * 存放根Node与集合大小的映射
     */
    private Map<Node<T>, Integer> sizeMap = new HashMap<>();


    public UnionSet01(List<T> values) {
        for (T value : values) {
            Node<T> node = new Node<>(value);
            nodeMap.put(value, node);
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node<T> findRootNode(Node<T> node) {

        Stack<Node<T>> stack = new Stack<>();

        //一直往上寻找，找到找到根Node，沿途的Node记录到stack
        while (parentMap.get(node) != node) {
            stack.push(node);
            node = parentMap.get(node);
        }

        //修改沿途的Node的父节点，使得下次findRootNode的代价更小（扁平化）
        while (!stack.isEmpty()) parentMap.put(stack.pop(), node);

        return node;
    }

    public boolean isSameSet(T a, T b) {
        //如果a和b有其中一个没有被记录过，返回false
        if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) return false;

        //a对应的Node，和b对应的Node，同时找到根Node，如果是同一个根Node，代表a和b在一个集合中
        return findRootNode(nodeMap.get(a)) == findRootNode(nodeMap.get(b));
    }

    public void union(T a, T b) {
        if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) return;

        //a对应的Node，和b对应的Node，同时往上找到根Node
        Node<T> rootNodeA = findRootNode(nodeMap.get(a));
        Node<T> rootNodeB = findRootNode(nodeMap.get(b));

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

    public static class Node<T> {
        private T value;

        public Node(T value) {
            this.value = value;
        }
    }

}
