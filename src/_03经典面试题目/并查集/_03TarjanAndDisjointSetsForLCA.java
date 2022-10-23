package _03经典面试题目.并查集;

import java.util.*;

/**
 * public class Query {
 *     public Node o1;
 *     public Node o2;
 *     public Query(Node o1, Node o2) {
 *         this.o1 = o1;
 *         this.o2 = o2;
 *     }
 * }
 *
 * 一个Query类的实例表示一条查询语句，表示想要查询o1节点和o2节点的最近公共祖先节点。
 * 给定一棵二叉树的头结点head，并给定所有的查询语句，即一个Query类型的数据 Query[] ques，请返回
 * Node类型的数组Node[] ans，ans[i]表示ques[i]这条查询的答案，即ques[i].o1和ques[i].o2的最近公共祖先。
 *
 * 【要求】
 * 如果二叉树的节点数为N，查询语句的条数为M，整个处理过程的时间复杂度要求达到O(N + M)
 *
 * Created by huangjunyi on 2022/10/16.
 */
public class _03TarjanAndDisjointSetsForLCA {

    private class Node {
        int value;
        Node left;
        Node right;
    }

    private class Query {
        public Node o1;
        public Node o2;
        public Query(Node o1, Node o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    private static class Element {
        Node node;

        public Element(Node node) {
            this.node = node;
        }
    }

    /**
     * 并查集
     */
    private static class UnionSet {
        private Map<Node, Element> nodeElementMap;
        private Map<Element, Element> fatherMap;
        private Map<Element, Integer> sizeMap;

        public UnionSet(List<Node> nodes) {
            nodeElementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes) {
                Element element = new Element(node);
                nodeElementMap.put(node, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public Element findFather(Node node) {
            Element element = nodeElementMap.get(node);
            LinkedList<Element> stack = new LinkedList<>();
            while (element != fatherMap.get(element)) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), element);
            }
            return element;
        }

        public void union(Node node1, Node node2) {
            if (!nodeElementMap.containsKey(node1) || !nodeElementMap.containsKey(node2)) return;
            if (isSameSet(node1, node2)) return;
            Element father1 = findFather(node1);
            Element father2 = findFather(node2);
            Integer size1 = sizeMap.get(father1);
            Integer size2 = sizeMap.get(father2);
            if (size1 < size2) {
                fatherMap.put(father1, father2);
                sizeMap.put(father2, size1 + size2);
                sizeMap.remove(father1);
            } else {
                fatherMap.put(father2, father1);
                sizeMap.put(father1, size1 + size2);
                sizeMap.remove(father2);
            }
        }

        private boolean isSameSet(Node node1, Node node2) {
            return findFather(node1) == findFather(node2);
        }

    }


    public static Node[] tarJanQuery(Node head, Query[] ques) {

        /*
        利用并查集求解
        总体思路是用所有的二叉树节点成一个并查集
        然后中序遍历二叉树
        把当前中序遍历所遍历到的节点都union到一个集合中，并给这个集合打一个tag，这个tag就是当前节点
        tag记录到一个Map中，tagMap，key为union集合的代表节点，value为当前节点

        利用一个queryMap记录一个节点与那些节点要查询公共祖先
        利用一个indexMap记录要查询公共祖先的节点与填写答案到ans时的下标位置
        queryMap和indexMap是正向反向都记录，如果里面的节点没有遍历到，会被丢弃，那么答案就会在反向的那个节点遍历到的时候结算

        中序遍历前，
        先处理号queryMap和indexMap

        然后中序遍历完当前节点，第三次回到当前节点时
        再结算该节点的答案
        查询queryMap中是否有要和当前节点求公共祖先的节点
        如果有，则用从queryMap中的取出的阶段，查询tagMap是否有tag节点，有，那么tag就是公共祖先
        答案的下标从indexMap中取
        如果没有tagMap没有对应的tag，那么就直接丢掉，也不放回queryMap中，indexMap中的也丢掉

         */

        Map<Node, LinkedList<Node>> queryMap = new HashMap<>();
        Map<Node, LinkedList<Integer>> indexMap = new HashMap<>();
        Map<Node, Node> tagMap = new HashMap<>();
        UnionSet unionSet = new UnionSet(getAllNode(head));
        Node[] ans = new Node[ques.length];
        setQueryMapAndIndexMapAndEasyAns(queryMap, indexMap, ques, ans);
        process(head, queryMap, indexMap, tagMap, unionSet, ans);
        return ans;
    }

    /**
     * 通过ques查询语句数组，生成queryMap和indexMap
     * @param queryMap 一个节点和那些节点要求公共祖先
     * @param indexMap 一个节点要求公共祖先，那么求得的答案放在ans的哪个下标位置
     * @param ques 查询语句数组
     * @param ans 返回的答案，也就是求得的公共祖先组成的数组
     */
    private static void setQueryMapAndIndexMapAndEasyAns(Map<Node, LinkedList<Node>> queryMap,
                                                         Map<Node, LinkedList<Integer>> indexMap,
                                                         Query[] ques,
                                                         Node[] ans) {
        for (int i = 0; i < ques.length; i++) {
            Node o1 = ques[i].o1;
            Node o2 = ques[i].o2;
            if (o1 == o2 || o1 == null || o2 == null) {
                ans[i] = o1 == null ? o2 : o1;
            }
            if (!queryMap.containsKey(o1)) {
                queryMap.put(o1, new LinkedList<>());
                indexMap.put(o1, new LinkedList<>());
            }
            if (!queryMap.containsKey(o2)) {
                queryMap.put(o2, new LinkedList<>());
                indexMap.put(o2, new LinkedList<>());
            }
            queryMap.get(o1).add(o2);
            indexMap.get(o1).add(i);
            queryMap.get(o2).add(o1);
            indexMap.get(o2).add(i);
        }
    }

    /**
     * 中序遍历二叉树，并收集答案
     * @param node 当前遍历到的节点
     * @param queryMap 一个节点和那些节点要求公共祖先
     * @param indexMap 一个节点要求公共祖先，那么求得的答案放在ans的哪个下标位置
     * @param tagMap 标签节点map，节点合并成一个结合后，会给该集合打一个tag，该tag就是这些节点的共同祖先
     * @param unionSet 并查集
     * @param ans 返回的答案，也就是求得的公共祖先组成的数组
     */
    private static void process(Node node,
                                Map<Node, LinkedList<Node>> queryMap,
                                Map<Node, LinkedList<Integer>> indexMap,
                                Map<Node, Node> tagMap,
                                UnionSet unionSet,
                                Node[] ans) {

        /*
        先中序遍历，遍历完左树和有序
        把该合并的阶段都合并
        然后打上tag为当前节点node
         */
        process(node.left, queryMap, indexMap, tagMap, unionSet, ans);
        unionSet.union(node.left, node);
        tagMap.put(unionSet.findFather(node).node, node);
        process(node.right, queryMap, indexMap, tagMap, unionSet, ans);
        unionSet.union(node.right, node);
        tagMap.put(unionSet.findFather(node).node, node);

        /*
        节点当前节点对应问题的答案
         */
        LinkedList<Node> nodes = queryMap.get(node);
        LinkedList<Integer> indexs = indexMap.get(node);
        while (nodes != null && !nodes.isEmpty()) {
            Node queryNode = nodes.poll();
            Integer ansIndex = indexs.poll();
            Node tag = tagMap.get(unionSet.findFather(queryNode).node);
            if (tag != null) {
                ans[ansIndex] = tag;
            }
        }

    }

    private static List<Node> getAllNode(Node head) {
        List<Node> nodes = new ArrayList<>();
        addNode(head, nodes);
        return nodes;
    }

    private static void addNode(Node node, List<Node> nodes) {
        if (node == null) return;
        nodes.add(node);
        addNode(node.left, nodes);
        addNode(node.right, nodes);
    }
}
