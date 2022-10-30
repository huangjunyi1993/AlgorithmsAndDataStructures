package _04LeetCode精选TOP面试题;

/**
 * Created by huangjunyi on 2022/10/30.
 */
public class _060填充每个节点的下一个右侧节点指针 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    private static class MyQueue {
        Node head;
        Node tail;
        int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public void offer(Node node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public Node poll() {
            Node cur = this.head;
            head = cur.next;
            cur.next = null;
            size--;
            return cur;
        }

    }
    public Node connect(Node root) {
        if (root == null) return null;
        /*
        还是宽度优先遍历 + 每次取取一批
        只是为了省空间
        自己建一个queue
        如果用LinkedList，它会帮你包一层
         */
        MyQueue myQueue = new MyQueue();
        myQueue.offer(root);
        while (!myQueue.isEmpty()) {
            Node pre = null;
            int size = myQueue.size;
            for (int i = 0; i < size; i++) {
                Node cur = myQueue.poll();
                if (pre == null) {
                    pre = cur;
                } else {
                    pre.next = cur;
                    pre = cur;
                }
                if (cur.left != null) myQueue.offer(cur.left);
                if (cur.right != null) myQueue.offer(cur.right);
            }
        }
        return root;
    }
}
