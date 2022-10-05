package _03经典面试题目.堆;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * TopRecord{
 * public TopRecord(int K) : 构造时事先指定好K的大小，构造后就固定不变了
 * public void add(String str) : 向该结构中加入一个字符串，可以重复加入
 * public List<String> top() : 返回之前加入的所有字符串中，词频最大的K个
 * }
 * 要求：
 * add方法，复杂度O(log K);
 * top方法，复杂度O(K)
 * Created by huangjunyi on 2022/9/25.
 */
public class _03TopRecode {

    private static class TopRecode {
        //词频表
        private HashMap<String, Integer> timesMap;
        //单词在heap中的下标
        private HashMap<String, Integer> indexMap;
        //小根堆
        private String[] heap;
        private int size = 0;
        private int len;

        public TopRecode(int K) {
            heap = new String[K];
            timesMap = new HashMap<>();
            indexMap = new HashMap<>();
            len = K;
        }

        /**
         * 1、更新词频
         * 2、如果单词在indexMap中已经存在，代表单词在heap中，直接做堆的向下调整
         * 3、如果单词在indexMap中不存在，分两种情况：
         *  ① 堆没满，放在堆底，然后做向上调整
         *  ② 堆满了，跟堆顶比较，词频比堆顶大，则替换堆顶，做堆的向下调整
         * @param str
         */
        public void add(String str) {
            if (timesMap.containsKey(str)) {
                timesMap.put(str, timesMap.get(str) + 1);
            } else {
                timesMap.put(str, 1);
            }
            if (indexMap.containsKey(str)) {
                sink(indexMap.get(str), size - 1);
            } else {
                if (size != len) {
                    heap[size] = str;
                    indexMap.put(str, size);
                    up(size++);
                } else {
                    if (timesMap.get(heap[0]) >= timesMap.get(str)) {
                        return;
                    }
                    indexMap.remove(heap[0]);
                    indexMap.put(str, 0);
                    heap[0] = str;
                    sink(0, size - 1);
                }
            }
        }

        private void up(int i) {
            while ((i - 1) / 2 >= 0) {
                int parentIndex = (i - 1) / 2;
                if (timesMap.get(heap[parentIndex]) <= timesMap.get(heap[i])) break;
                swap(parentIndex, i);
            }
        }

        /**
         * 直接返回堆中的单词，就是topK
         * @return
         */
        public List<String> top() {
            return Arrays.asList(heap);
        }

        private void sink(int i, int end) {
            while (i * 2 + 1 <= end) {
                int sonIndex = i * 2 + 1;
                if (i * 2 + 2 <= end && timesMap.get(heap[sonIndex]) > timesMap.get(heap[i * 2 + 2])) sonIndex = i * 2 + 2;
                if (timesMap.get(heap[sonIndex]) < timesMap.get(heap[i])) {
                    swap(i, sonIndex);
                } else {
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            Integer indexI = indexMap.get(heap[i]);
            Integer indexJ = indexMap.get(heap[j]);
            indexMap.put(heap[i], indexJ);
            indexMap.put(heap[j], indexI);
            String temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

    }

    public static void main(String[] args) {
        TopRecode topRecode = new TopRecode(3);
        topRecode.add("a");
        topRecode.add("d");
        topRecode.add("a");
        topRecode.add("e");
        topRecode.add("a");
        topRecode.add("f");
        topRecode.add("g");
        topRecode.add("c");
        topRecode.add("c");
        topRecode.add("b");
        topRecode.add("h");
        topRecode.add("b");
        System.out.println(topRecode.top());
    }

}
