package _01基础._09二叉树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 二叉树按层遍历，并且统计最大层的结点数
 */
public class Tree02 {

    /**
     * 用一个HashMap记录每个结点处于哪一层
     * @param head
     * @return
     */
    public static int maxLevelNumber01(Node head) {
        if (head == null) return 0;

        Map<Node, Integer> nodeLevelMap = new HashMap<>();
        nodeLevelMap.put(head, 1); //记录头结点为第一层
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        int max = 0;
        int currentLevel = 1; //当前正在遍历的层级
        int currentLevelWidth = 0; //当前遍历层级的宽度

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Integer nodeLevel = nodeLevelMap.get(node);

            if (node.left != null) {
                nodeLevelMap.put(node.left, nodeLevel + 1); //左结点层级为当前节点层级+1
                queue.offer(node.left);
            }
            if (node.right != null) {
                nodeLevelMap.put(node.right, nodeLevel + 1); //右结点层级为当前节点层级+1
                queue.offer(node.right);
            }

            if (currentLevel == nodeLevel) currentLevelWidth++; //当前结点层级等于当前遍历层级，当前遍历层级宽度+1
            else {
                //当前结点层级不等于当前遍历层级，结算
                max = Math.max(currentLevelWidth, max);
                currentLevelWidth = 1;
                currentLevel = nodeLevel;
            }
        }

        max = Math.max(currentLevelWidth, max);
        return max;
    }

    /**
     * 用一个变量记录当前层的最右结点，当到达当前层最右结点时，结算
     * @param head
     * @return
     */
    public static int maxLevelNumber02(Node head) {
        if (head == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        Node currentLevelEnd = head; //当前遍历层的最右结点
        Node nextLevelEnd = null; //下一层的最右结点
        int max = 0; //最大层宽度
        int currentLevelWidth = 0; //当前遍历层的宽度

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            currentLevelWidth++; //当前层宽度+1
            if (node.left != null) {
                queue.offer(node.left);
                nextLevelEnd = node.left; //更新下一层的最右结点
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelEnd = node.right; //更新下一层的最右结点
            }
            //当前结点是当前层最右结点，结算
            if (node == currentLevelEnd) {
                max = Math.max(currentLevelWidth, max);
                currentLevelWidth = 0;
                currentLevelEnd = nextLevelEnd;
                nextLevelEnd = null;
            }
        }
        return max;
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
    }

}
