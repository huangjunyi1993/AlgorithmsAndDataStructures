package _03经典面试题目.并查集;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 一个数组中，如果两个数的公共因子有大于1的，则认为这两个数之间有通路
 * 返回数组中，有多少个独立的域
 * Created by huangjunyi on 2022/10/3.
 */
public class _01FindIndependentDomain {

    public static int countDomain01(int[] arr) {
        /*
        通过并查集，把有通路的数合并为一个集合
        最后查询并查集中有多少个集合

        双层循环遍历数组，尝试每两个数间是否有通路

        是否有通路，通过求两个数的最大公约数，不为1则有通路
         */
        UnionSet unionSet = new UnionSet(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (gcd(arr[i], arr[j]) != 1) unionSet.union(i, j);
            }
        }
        return unionSet.size();
    }

    public static int countDomain02(int[] arr) {
        UnionSet unionSet = new UnionSet(arr.length);
        Map<Integer, Integer> factorsMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            /*
            对方法1的内层循环进行优化

            有一个map表记录因子和对应数的下标
            表示一个因子是否有数拥有

            内层循环简化为从1~Math.sqrt(arr[i])，尝试arr[i]的因子（j和arr[i]/j）
            如果map中没有记录，则记录
            有记录，则与map中记录的下标合并
             */
            int sqrt = (int) Math.sqrt(arr[i]);
            for (int j = 1; j <= sqrt; j++) {
                if (arr[i] % j == 0) {
                    if (j != 1) {
                        if (factorsMap.containsKey(j)) {
                            unionSet.union(factorsMap.get(j), i);
                        } else {
                            factorsMap.put(j, i);
                        }
                    }
                    int other = arr[i] / j;
                    if (other != 1) {
                        if (factorsMap.containsKey(other)) {
                            unionSet.union(factorsMap.get(other), i);
                        } else {
                            factorsMap.put(other, i);
                        }
                    }
                }
            }
        }
        return unionSet.size();
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static class Node {
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    private static class UnionSet {

        private Map<Integer, Node> nodeMap;
        private Map<Node, Node> parentMap;
        private Map<Node, Integer> sizeMap;

        public UnionSet(int len) {
            for (int num = 0; num < len; num++) {
                nodeMap.put(num, new Node(num));
                parentMap.put(nodeMap.get(num), nodeMap.get(num));
                sizeMap.put(nodeMap.get(num), 1);
            }
        }

        public Node findRootNode(Node node) {
            LinkedList<Node> stack = new LinkedList<>();
            while (parentMap.get(node) != node) {
                stack.addFirst(node);
                node = parentMap.get(node);
            }
            while (!stack.isEmpty()) parentMap.put(stack.getFirst(), node);
            return node;
        }

        public boolean isSameSet(int a, int b) {
            if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) return false;

            Node rootNodeA = findRootNode(nodeMap.get(a));
            Node rootNodeB = findRootNode(nodeMap.get(b));
            if (rootNodeA == rootNodeB) return true;

            return false;
        }

        public void union(int a, int b) {
            if (!nodeMap.containsKey(a) || !nodeMap.containsKey(b)) return;

            Node rootNodeA = findRootNode(nodeMap.get(a));
            Node rootNodeB = findRootNode(nodeMap.get(b));
            if (rootNodeA == rootNodeB) return;

            Integer sizeA = sizeMap.get(rootNodeA);
            Integer sizeB = sizeMap.get(rootNodeB);

            if (sizeA < sizeB) {
                parentMap.put(rootNodeA, rootNodeB);
                sizeMap.put(rootNodeB, sizeA + sizeB);
                sizeMap.remove(rootNodeA);
            } else {
                parentMap.put(rootNodeB, rootNodeA);
                sizeMap.put(rootNodeA, sizeA + sizeB);
                sizeMap.remove(rootNodeB);
            }

        }

        public int size() {
            return sizeMap.keySet().size();
        }

    }

}
