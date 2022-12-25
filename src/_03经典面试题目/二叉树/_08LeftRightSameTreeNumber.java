package _03经典面试题目.二叉树;

/**
 * 如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的子树是相等子树
 * 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
 * Created by huangjunyi on 2022/12/25.
 */
public class _08LeftRightSameTreeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }
    private static class Info {
        private int val;
        private String str;

        public Info(int val, String str) {
            this.val = val;
            this.str = str;
        }
    }
    public static int sameNumber(Node head) {
        return process(head).val;
    }
    private static Info process(Node node) {
        if (node == null) {
            return new Info(0, "#");
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int val = leftInfo.val + rightInfo.val + (leftInfo.str.equals(rightInfo.str) ? 1 : 0);
        // 构造先序串，由于与其他子树比对是否相等，后续串也可以，但是不能是中序，中序有歧义
        String str = node.value + "," + leftInfo.str + "," + rightInfo.str;
        return new Info(val, str);
    }

    // 时间复杂度O(N * logN)
    public static int sameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
    }

    public static boolean same(Node h1, Node h2) {
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        // 两个都不为空
        return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
    }

    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameNumber1(head);
            int ans2 = sameNumber(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");

    }
}
