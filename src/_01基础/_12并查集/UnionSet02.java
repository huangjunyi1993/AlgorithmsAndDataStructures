package _01基础._12并查集;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 一个并查集的使用例子
 * 一个人有三个id，只有其中一个id相同，就认为是同一个人
 * 给定一个Person数组，将其合并，并返回里面有几个人
 */
public class UnionSet02 {

    private static class Person {
        private int id1;
        private int id2;
        private int id3;
    }

    public static class UnionSet<T> {

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


        public UnionSet(T[] values) {
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

            //修改沿途的Node的父节点，使得下次findRootNode的代价更小
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

        public int countSet() {
            return sizeMap.size();
        }

    }

    public static class Node<T> {
        private T value;

        public Node(T value) {
            this.value = value;
        }
    }

    public static int merge(Person[] persons) {
        UnionSet<Person> unionSet = new UnionSet<>(persons);

        Map<Integer, Person> id1Map = new HashMap<>(); //记录id1和person的映射关系
        Map<Integer, Person> id2Map = new HashMap<>(); //记录id2和person的映射关系
        Map<Integer, Person> id3Map = new HashMap<>(); //记录id3和person的映射关系

        /**
         * 用三个map，分表记录id1、id2、id3与person的映射关系
         * 遍历给定的person数组，如果map里面没有包含该id，则添加其映射关系
         * 如果已经包含该id，则调用并查集UnionSet的union方法，把这两个person合并
         */
        for (Person person : persons) {
            if (id1Map.containsKey(person.id1)) {
                unionSet.union(person, id1Map.get(person.id1));
            } else {
                id1Map.put(person.id1, person);
            }
            if (id2Map.containsKey(person.id2)) {
                unionSet.union(person, id2Map.get(person.id2));
            } else {
                id2Map.put(person.id2, person);
            }
            if (id3Map.containsKey(person.id3)) {
                unionSet.union(person, id3Map.get(person.id3));
            } else {
                id3Map.put(person.id3, person);
            }
        }

        //返回并查集中集合的数目，id相同的都通过union方法合并了，所以一个集合代表一个人
        return unionSet.countSet();
    }

}
