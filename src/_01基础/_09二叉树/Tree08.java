package _01基础._09二叉树;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 二叉树中寻找两个结点的最低公共祖先
 */
public class Tree08 {

    public static Node findLowestAncestor(Node head, Node node1, Node node2) {
        if (head == null) return null;
        //parentMap记录结点与父节点的映射关系
        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        //填充parentMap
        fillParentMap(head, parentMap);
        Set<Node> node1Path = new HashSet<>();
        //拿着node1结点通过parentMap往上遍历，记录走过的路径，记录到一个set中
        Node curr = node1;
        node1Path.add(curr);
        while (parentMap.get(curr) != null) {
            curr = parentMap.get(curr);
            node1Path.add(curr);
        }
        //拿着node2结点通过parentMap往上遍历，遇到set中记录的结点，就是两结点的最低公共祖先
        curr = node2;
        while (!node1Path.contains(curr)) {
            curr = parentMap.get(curr);
        }
        return curr;
    }

    private static void fillParentMap(Node node, Map<Node, Node> parentMap) {
        if (node.left != null) {
            parentMap.put(node.left, node);
            fillParentMap(node.left, parentMap);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            fillParentMap(node.right, parentMap);
        }
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
