package _03经典面试题目.有序表;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 *
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 *
 * Created by huangjunyi on 2022/10/21.
 */
public class _03SmallestRange {
    private static class Node {
        int value;
        int arrid;
        int index;

        public Node(int value, int arrid, int index) {
            this.value = value;
            this.arrid = arrid;
            this.index = index;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();
        /*
        通过有序表求解

        有序表节点记录值、来自哪个数组、下标

        一开始先从每个数组中选0位置数组成Node放入有序表
        然后每次弹出表头表尾，区间更小则更新
         */
        TreeSet<Node> orderSet = new TreeSet<>((o1, o2) -> o1.value != o2.value ? o1.value - o2.value : o1.arrid - o2.arrid);
        for (int i = 0; i < N; i++) {
            orderSet.add(new Node(nums.get(i).get(0), i, 0));
        }
        boolean set = false;
        int a = Integer.MIN_VALUE;
        int b = Integer.MAX_VALUE;
        while (orderSet.size() == N) {
            int min = orderSet.first().value;
            int max = orderSet.last().value;
            if (!set || (max - min) < (b - a)) {
                set = true;
                a = min;
                b = max;
            }
            Node node = orderSet.pollFirst();
            if (node.index + 1 < nums.get(node.arrid).size()) {
                orderSet.add(new Node(nums.get(node.arrid).get(node.index + 1), node.arrid, node.index + 1));
            }
        }
        return new int[] {a, b};
    }
}
