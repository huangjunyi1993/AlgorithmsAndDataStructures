package _01基础._13图;

import _01基础._13图.graph.Node;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 图的深度优先遍历
 * Created by huangjunyi on 2022/8/30.
 */
public class Graph03 {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }

        Deque<Node> stack = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        System.out.println(node.value);
        stack.push(node);
        set.add(node);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            for (Node next : curr.nexts) {
                if (!set.contains(next)) {
                    System.out.println(next.value);
                    set.add(next);
                    stack.push(curr);
                    stack.push(next);
                    break;
                }
            }
        }
    }

}
