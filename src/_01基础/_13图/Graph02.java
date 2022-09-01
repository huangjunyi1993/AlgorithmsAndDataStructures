package _01基础._13图;

import _01基础._13图.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的广度优先遍历
 * Created by huangjunyi on 2022/8/30.
 */
public class Graph02 {

    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node curr;
            System.out.println((curr = queue.poll()).value);
            for (Node next : curr.nexts) {
                if (!set.contains(next)) {
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
    }

}
