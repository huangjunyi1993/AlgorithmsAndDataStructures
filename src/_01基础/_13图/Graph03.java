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

        // 栈用于存储遍历过的节点，沿着节点路径一直往里遍历，遍历到的节点放入栈中
        Deque<Node> stack = new LinkedList<>();
        // set记录遍历过的节点，一个节点遍历过，就不在入栈
        Set<Node> set = new HashSet<>();
        // 入栈前先打印，表示已经遍历过
        System.out.println(node.value);
        // 头节点入栈
        stack.push(node);
        // 记录头节点遍历过
        set.add(node);
        // 一直遍历直到栈为空
        while (!stack.isEmpty()) {
            // 从栈中弹出栈顶节点，遍历弹出节点的邻居，把没有遍历过的邻居节点入栈
            Node curr = stack.pop();
            for (Node next : curr.nexts) {
                if (!set.contains(next)) {
                    // 遍历到没有访问过的邻居，打印该邻居
                    System.out.println(next.value);
                    // 记录该邻居已经访问过
                    set.add(next);
                    // 当前节点和邻居节点入栈，返回，进入下一轮循环
                    stack.push(curr);
                    stack.push(next);
                    break;
                }
            }
        }
    }

}
