package _03经典面试题目.前缀和;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一棵二叉树的头节点head，和一个数K
 * 路径的定义:
 * 可以从任何一个点开始，但是只能往下走，往下可以走到任何节点停止
 * 返回路径累加和为K的所有路径中，最长的路径最多有几个节点？
 *
 * Created by huangjunyi on 2022/10/6.
 */
public class _04TreeLongestSumEqualsK {

    private static class Node {
        int value;
        Node left;
        Node right;
    }

    private static int res = 0;
    public static int getMaxSum(Node head, int K) {
        if (head == null) return 0;
        Map<Integer, Integer> sumLevelMap = new HashMap<>();
        sumLevelMap.put(0, -1);
        process(head, 0, 0, sumLevelMap, K);
        return res;
    }

    /**
     * 递归遍历二叉树的每一个节点，利用前缀和求解最长路径
     * @param cur 当前访问的节点
     * @param level 当前层
     * @param sum 从头节点到当前节点的累加和
     * @param sumLevelMap  累加和和层的映射表
     * @param K 题目要求的累加和
     */
    private static void process(Node cur, int level, int sum, Map<Integer, Integer> sumLevelMap, int K) {
        if (cur == null) {
            return;
        }
        sum += cur.value;
        /*
        头节点到当前节点的累加和为sum，如果map中存在 sum - K 的记录
        则从map中累加和为 sum - K 的该层节点的后一个节点，到当前节点
        累加和为K
        可以尝试更新res
         */
        if (sumLevelMap.containsKey(sum - K)) {
            res = Math.max(res, level - sumLevelMap.get(sum - K));
        }
        // 如果sum这个累加和没有曾经添加到map中，则填进去，代表是累加和sum最早的层是level
        if (!sumLevelMap.containsKey(sum)) {
            sumLevelMap.put(sum, level);
        }
        process(cur.left, level + 1, sum, sumLevelMap, K);
        process(cur.right, level + 1, sum, sumLevelMap, K);
        // 返回上一层前，还原现场
        if (sumLevelMap.get(sum) == level) {
            sumLevelMap.remove(sum);
        }
    }

}
