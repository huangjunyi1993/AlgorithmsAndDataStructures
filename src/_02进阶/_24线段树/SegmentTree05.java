package _02进阶._24线段树;

/**
 * 动态开点线段树
 * 只支持单点增加 + 范围查询（累加和）
 * Created by huangjunyi on 2023/1/22.
 */
public class SegmentTree05 {
    /**
     * 线段树节点
     */
    public static class Node {
        public int sum;
        public Node left;
        public Node right;
    }

    /**
     * 动态开点线段树
     * 下标从1开始
     * arr[0] -> 1
     */
    public static class DynamicSegmentTree {
        private Node root;
        private int size;

        public DynamicSegmentTree(int max) {
            root = new Node();
            this.size = max;
        }

        public void add(int index, int value) {
            add(root, 1, size, index, value);
        }

        private void add(Node curr, int left, int right, int index, int value) {
            if (left == right) {
                curr.sum += value;
            } else {
                int mid = (left + right) / 2;
                if (index <= mid) {
                    if (curr.left == null) curr.left = new Node();
                    add(curr.left, left, mid, index, value);
                } else {
                    if (curr.right == null) curr.right = new Node();
                    add(curr.right, mid + 1, right, index, value);
                }
                curr.sum = (curr.left == null ? 0 : curr.left.sum) + (curr.right == null ? 0 : curr.right.sum);
            }
        }

        public int query(int start, int end) {
            return query(root, 1, size, start, end);
        }

        private int query(Node curr, int left, int right, int start, int end) {
            if (curr == null) return 0;
            if (start <= left && end >= right) return curr.sum;
            int mid = (left + right) / 2;
            if (end <= mid) {
                return query(curr.left, left, mid, start, end);
            } else if (start > mid) {
                return query(curr.right, mid + 1, right, start, end);
            } else {
                return query(curr.left, left, mid, start, end) + query(curr.right, mid + 1, right, start, end);
            }
        }
    }

    public static class Right {
        public int[] arr;

        public Right(int size) {
            arr = new int[size + 1];
        }

        public void add(int i, int v) {
            arr[i] += v;
        }

        public int query(int s, int e) {
            int sum = 0;
            for (int i = s; i <= e; i++) {
                sum += arr[i];
            }
            return sum;
        }

    }

    public static void main(String[] args) {
        int size = 10000;
        int testTime = 50000;
        int value = 500;
        DynamicSegmentTree dst = new DynamicSegmentTree(size);
        Right right = new Right(size);
        System.out.println("测试开始");
        for (int k = 0; k < testTime; k++) {
            if (Math.random() < 0.5) {
                int i = (int) (Math.random() * size) + 1;
                int v = (int) (Math.random() * value);
                dst.add(i, v);
                right.add(i, v);
            } else {
                int a = (int) (Math.random() * size) + 1;
                int b = (int) (Math.random() * size) + 1;
                int s = Math.min(a, b);
                int e = Math.max(a, b);
                int ans1 = dst.query(s, e);
                int ans2 = right.query(s, e);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                    System.out.println(ans1);
                    System.out.println(ans2);
                }
            }
        }
        System.out.println("测试结束");
    }



}
