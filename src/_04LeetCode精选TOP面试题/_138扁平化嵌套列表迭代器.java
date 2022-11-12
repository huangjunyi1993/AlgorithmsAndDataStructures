package _04LeetCode精选TOP面试题;

import java.util.Iterator;
import java.util.List;

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

        public NestedIterator(List<NestedInteger> nestedList) {

        }

        @Override
        public Integer next() {
            return null;
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
