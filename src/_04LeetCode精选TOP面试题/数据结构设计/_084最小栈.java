package _04LeetCode精选TOP面试题.数据结构设计;

import java.util.LinkedList;

/**
 * Created by huangjunyi on 2022/11/4.
 */
public class _084最小栈 {
    class MinStack {
        private LinkedList<Integer> dataStack;
        private LinkedList<Integer> minStack;
        public MinStack() {
            dataStack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            dataStack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                // 如果栈顶比待压入的数小，则压入栈顶的数
                minStack.push(minStack.peek() < val ? minStack.peek() : val);
            }
        }

        public void pop() {
            if (!dataStack.isEmpty()) {
                dataStack.pop();
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
