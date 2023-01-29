package _04LeetCode精选TOP面试题.数据结构设计;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/flatten-nested-list-iterator/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _138扁平化嵌套列表迭代器 {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        private class Info {
            List<NestedInteger> nestedList;
            int index;

            public Info(List<NestedInteger> nestedList, int index) {
                this.nestedList = nestedList;
                this.index = index;
            }
        }

        private int index;
        private List<NestedInteger> curNestedList;
        private LinkedList<Info> stack;
        private Integer curValue;

        public NestedIterator(List<NestedInteger> nestedList) {
            index = 0;
            curNestedList = nestedList;
            stack = new LinkedList<>();
        }

        @Override
        public Integer next() {
            return curValue;
        }

        @Override
        public boolean hasNext() {
            while (true) {
                if (index == curNestedList.size() && stack.isEmpty()) return false;
                if (index == curNestedList.size()) {
                    Info info = stack.pop();
                    curNestedList = info.nestedList;
                    index = info.index;
                    continue;
                }
                if (!curNestedList.get(index).isInteger()) {
                    stack.push(new Info(curNestedList, index + 1));
                    curNestedList = curNestedList.get(index).getList();
                    index = 0;
                    continue;
                }
                curValue = curNestedList.get(index).getInteger();
                index++;
                return true;
            }
        }
    }

    /*
    栈 + 深度优先遍历 + 记录线索
     */
    public class NestedIteratorZuo implements Iterator<Integer> {

        // 当前迭代到的list
        private List<NestedInteger> list;
        // 封装线索的栈
        // 栈底|0, 2, 1|栈顶，那就是从最外层拿到1号元素（list），
        // 再从这个1号元素list中拿到2号元素（list）
        // 再从2号元素list中拿到0号元素（num）
        // 栈空了，0号元素是num，返回num
        private Stack<Integer> stack;
        // 当前迭代到的数是否使用了
        private boolean used;

        public NestedIteratorZuo(List<NestedInteger> nestedList) {
            list = nestedList;
            stack = new Stack<>();
            stack.push(-1);
            used = true;
            hasNext();
        }

        @Override
        public Integer next() {
            Integer ans = null;
            if (!used) {
                // 调get方法，通过stack记录的线索，扎到栈底，取到当前迭代到的数
                ans = get(list, stack);
                used = true;
                hasNext();
            }
            return ans;
        }

        @Override
        public boolean hasNext() {
            // 栈空，没了
            if (stack.isEmpty()) {
                return false;
            }
            // 没使用
            if (!used) {
                return true;
            }
            // 找下一个数
            if (findNext(list, stack)) {
                used = false;
            }
            return !used;
        }

        /**
         * 栈底|0, 2, 1|栈顶，那就是从最外层拿到1号元素（list），
         * 再从这个1号元素list中拿到2号元素（list）
         * 再从2号元素list中拿到0号元素（num）
         * 栈空了，0号元素是num，返回num
         *
         * @param nestedList list
         * @param stack 封装线索的栈
         * @return num
         */
        private Integer get(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            Integer ans = null;
            if (!stack.isEmpty()) {
                ans = get(nestedList.get(index).getList(), stack);
            } else {
                ans = nestedList.get(index).getInteger();
            }
            stack.push(index);
            return ans;
        }

        /**
         * 扎到线索栈（stack）的栈底，到达最里层的list，找下一个数
         * @param nestedList
         * @param stack
         * @return
         */
        private boolean findNext(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            if (!stack.isEmpty() && findNext(nestedList.get(index).getList(), stack)) {
                stack.push(index);
                return true;
            }
            for (int i = index + 1; i < nestedList.size(); i++) {
                // 找数
                if (pickFirst(nestedList.get(i), i, stack)) {
                    return true;
                }
            }
            return false;
        }

        private boolean pickFirst(NestedInteger nested, int position, Stack<Integer> stack) {
            if (nested.isInteger()) {
                // 是数，压入线索栈，返回
                stack.add(position);
                return true;
            } else {
                // 不是数，是list，记录线索，技术往list里面钻
                List<NestedInteger> actualList = nested.getList();
                for (int i = 0; i < actualList.size(); i++) {
                    if (pickFirst(actualList.get(i), i, stack)) {
                        stack.add(position);
                        return true;
                    }
                }
            }
            return false;
        }

    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
