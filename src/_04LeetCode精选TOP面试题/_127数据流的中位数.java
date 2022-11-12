package _04LeetCode精选TOP面试题;

import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/find-median-from-data-stream/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _127数据流的中位数 {
    class MedianFinder {
        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;
        public MedianFinder() {
            minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);
            maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        public void addNum(int num) {
            // 第一个数进大根堆
            if (maxHeap.isEmpty()) {
                maxHeap.add(num);
            } else {
                // 如果没有大于大根堆堆顶，入大根堆
                if (maxHeap.peek() >= num) {
                    maxHeap.add(num);
                }
                // 否则进小跟堆
                else {
                    minHeap.add(num);
                }
            }

            // 平衡，保证两个堆间大小差不超过2
            if (maxHeap.size() == minHeap.size() + 2) {
                minHeap.add(maxHeap.poll());
            }
            if (maxHeap.size() == minHeap.size() - 2) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            // 两堆大小相等，则取堆顶数相加除2
            if (minHeap.size() == maxHeap.size()) {
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            }
            // 否则去大小较大的堆的堆顶元素
            else {
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }
    }
}
