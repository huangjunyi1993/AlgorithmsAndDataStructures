package _03经典面试题目.图的优先遍历;

import java.util.*;

/**
 * 给定三个参数：
 * 二叉树的头节点head，树上某个节点target，正数K
 * 从target开始，可以向上走或者向下走
 * 返回与target的距离是k的所有节点
 * Created by huangjunyi on 2022/12/23.
 */
public class _03DistanceKNodes {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(root, null);
        // 生成parent表：node => parent
        createParentMap(root, parentMap);
        // 宽度优先遍历
        LinkedList<Node> queue = new LinkedList<>();
        // 去重表
        List<Node> visited = new ArrayList<>();
        queue.offer(root);
        visited.add(root);
        // 记录遍历层数，第K层就是答案
        int level = 0;
        List<Node> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (level == K) res.add(cur);
                if (cur.left != null && !visited.contains(cur.left)) {
                    queue.offer(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.offer(cur.right);
                    visited.add(cur.right);
                }
                if (parentMap.get(cur) != null && !visited.contains(parentMap.get(cur))) {
                    queue.offer(parentMap.get(cur));
                    visited.add(parentMap.get(cur));
                }
                level++;
                if (level > K) break;
            }
        }
        return res;
    }

    private static void createParentMap(Node node, Map<Node, Node> parentMap) {
        if (node == null) return;
        if (node.left != null) parentMap.put(node.left, node);
        if (node.right != null) parentMap.put(node.right, node);
        createParentMap(node.left, parentMap);
        createParentMap(node.right, parentMap);
    }

}
