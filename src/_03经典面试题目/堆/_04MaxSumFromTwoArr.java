package _03经典面试题目.堆;

import java.io.*;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 给定两个有序数组arr1和arr2，再给定一个正数K,求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2
 *
 * Created by huangjunyi on 2022/10/2.
 */
public class _04MaxSumFromTwoArr {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) tokenizer.nval;
            tokenizer.nextToken();
            int k = (int) tokenizer.nval;
            int[] arr1 = new int[n];
            int[] arr2 = new int[n];
            for (int i = 0; i < n; i++) {
                tokenizer.nextToken();
                arr1[i] = (int) tokenizer.nval;
            }
            for (int i = 0; i < n; i++) {
                tokenizer.nextToken();
                arr2[i] = (int) tokenizer.nval;
            }
            int[] res = maxSumTopK(arr1, arr2, k);
            for (int i = 0; i < res.length; i++) {
                out.print(res[i] + " ");
            }
        }
        out.flush();
        out.close();
    }

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
        int N = arr1.length;
        int M = arr2.length;
        K = Math.min(K, N * M);
        // 大根堆，根据sum进行排序
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);
        heap.add(new Node(arr1.length - 1, arr2.length - 1, arr1[arr1.length - 1] + arr2[arr2.length - 1]));
        // 去重表，防止重复放入，二维转一维
        HashSet<Long> visited = new HashSet<>();
        visited.add((long) ((N - 1) * M + M - 1));
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
            if (node.index1 - 1 >= 0 && !visited.contains((long)(node.index1 - 1) * M + node.index2)) {
                visited.add((long)(node.index1 - 1) * M + node.index2);
                heap.add(new Node(node.index1 - 1,node.index2, arr1[node.index1 - 1] + arr2[node.index2]));
            }
            if (node.index2 - 1 >= 0 && !visited.contains((long) node.index1 * M + node.index2 - 1)) {
                visited.add((long) node.index1 * M + node.index2 - 1);
                heap.add(new Node(node.index1,node.index2 - 1, arr1[node.index1] + arr2[node.index2 - 1]));
            }
            visited.remove((long) node.index1 * arr1.length + node.index2);
        }
        return res;
    }

}
