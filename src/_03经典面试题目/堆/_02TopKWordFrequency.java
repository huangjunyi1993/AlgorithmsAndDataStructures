package _03经典面试题目.堆;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个由字符串组成的数组String[] strs，给定一个正数k
 * 返回词频最大的前k个字符串，假设结果是唯一的
 * Created by huangjunyi on 2022/9/25.
 */
public class _02TopKWordFrequency {

    public static String[] getTopK(String[] strs, int k) {
        /*
        先变量字符串数组，用一个hash表记录词频
        然后申请一个k大小的小跟堆
        遍历hash表
        每次看堆顶字符串的词频是否小于当前的字符串
        如果是，则弹出堆顶，压入当前字符串
        最后把堆中所有字符串弹出，用一个k长度的字符串数组接收
         */
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            if (map.containsKey(str)) map.put(str, map.get(str) + 1);
            else map.put(str, 1);
        }
        int len = Math.min(k, strs.length);
        PriorityQueue<String> heap = new PriorityQueue<>(len, Comparator.comparingInt(map::get));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (heap.size() != len) heap.add(entry.getKey());
            else {
                if (map.get(heap.peek()) < entry.getValue()) {
                    heap.poll();
                    heap.add(entry.getKey());
                }
            }
        }
        String[] res = new String[len];
        int i = 0;
        while (!heap.isEmpty()) {
            res[i++] = heap.poll();
        }
        return res;
    }

}
