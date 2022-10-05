package _03经典面试题目.堆;

import java.util.PriorityQueue;

/**
 * 给定两个有序数组arr1和arr2，再给定一个正数K,求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2
 *
 * Created by huangjunyi on 2022/10/2.
 */
public class _04MaxSumFromTwoArr {

    private static class Node {
        private int index1;
        private int index2;
        private int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    public static int[] maxSumTopK(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0 || K < 0) return null;
        // 大根堆，根据sum进行排序
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);
        // 去重表，防止节点重复放入
        boolean[][] visited = new boolean[arr1.length][arr2.length];
        heap.add(new Node(arr1.length - 1, arr2.length - 1, arr1[arr1.length - 1] + arr2[arr2.length - 1]));
        visited[arr1.length - 1][arr2.length - 1] = true;
        int[] res = new int[K];
        int resIndex = 0;
        /*
         * 循环填写res
         *
         * 想象一张二维表sum[][]
         * sum[i][j] 表示arr[i]与arr[j]相加的和
         *
         * 每次弹出大根堆的堆顶，放入sum到结果集res中
         * 并且把弹出的节点的左边和上边的值，组成新的节点，放入堆中
         */
        while (resIndex < K) {
            Node node = heap.poll();
            res[resIndex++] = node.sum;
            if (node.index1 - 1 >= 0 && !visited[node.index1 - 1][node.index2]) {
                heap.add(new Node(node.index1 - 1,node.index2, arr1[node.index1 - 1] + arr2[node.index2]));
            }
            if (node.index2 - 1 >= 0 && !visited[node.index1][node.index2 - 1]) {
                heap.add(new Node(node.index1,node.index2 - 1, arr1[node.index1] + arr2[node.index2 - 1]));
            }
        }
        return res;
    }

}
