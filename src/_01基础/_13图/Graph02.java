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
        // 队列用于放入待遍历的节点
        Queue<Node> queue = new LinkedList<>();
        // set记录一个节点是否入过队列，是则不再入队列
        Set<Node> set = new HashSet<>();
        // 头节点入队列
        queue.offer(node);
        // 记录头已经入过队列
        set.add(node);
        // 一直遍历，直到队列为空
        while (!queue.isEmpty()) {
            // 从队列弹出节点，打印
            Node curr;
            System.out.println((curr = queue.poll()).value);
            // 看该节点是否有没有入过队列的节点，有则入队列，等待遍历
            for (Node next : curr.nexts) {
                if (!set.contains(next)) {
                    queue.offer(next);
                    set.add(next);
                }
            }
        }
    }

}
