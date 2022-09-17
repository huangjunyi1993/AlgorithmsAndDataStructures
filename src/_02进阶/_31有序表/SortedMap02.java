package _02进阶._31有序表;

/**
 *给定一个数组arr，一个整数k表示窗口大小，返回每个窗口的中位数
 * Created by huangjunyi on 2022/9/17.
 */
public class SortedMap02 {

    private static class Node implements Comparable<Node> {

        private int index;
        private int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value != o.value ? this.value - o.value : this.index - o.index;
        }
    }

    private static class SizeBalancedTreeSet<E extends Comparable<E>> {
        private SizeBalancedTree02<E, Object> map;
        private static final Object PRESENT = new Object();
        public E getIndex(int index) {
            return this.map.getIndexKey(index);
        }
        public void add(E element) {
            this.map.put(element, PRESENT);
        }
        public void remove(E element) {
            this.map.remove(element);
        }
        public int size() {
            return this.map.getSize();
        }
    }

    public static double[] medianSlidingWindow(int[] arr, int k) {
        if (arr == null || arr.length < k) return null;
        /*
        通过改写有序表，记录窗口中结点的排序
         */
        SizeBalancedTreeSet<Node> set = new SizeBalancedTreeSet<>();
        for (int i = 0; i < k - 1; i++) {
            set.add(new Node(i, arr[i]));
        }
        double[] res = new double[arr.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < arr.length; i++) {
            set.add(new Node(i, arr[i]));
            /*
            取出当前有序表中的排在中间的结点，该节点的值最为当前窗口的中位数，如果是偶数个数，则取上下中位数两个节点，然后相加除2
             */
            if (set.size() % 2 == 0) {
                int v1 = set.getIndex(set.size() / 2 - 1).value;
                int v2 = set.getIndex(set.size() / 2).value;
                res[index++] = ((double) v1 + (double) v2) / 2;
            } else {
                res[index++] = set.getIndex(set.size() / 2).value;
            }
            set.remove(new Node(i - k + 1, arr[i - k + 1]));
        }
        return res;
    }

}
