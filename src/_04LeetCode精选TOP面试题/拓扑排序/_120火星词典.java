package _04LeetCode精选TOP面试题.拓扑排序;

import java.util.*;

/**
 * 给定一些单词，反推字典序，有可能是无序的（循环）
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _120火星词典 {

    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        // 遍历单词表，前一个后一个比较
        // 根据比较的结果，生成有向图
        // 根据有向图进行拓扑排序
        // 如果有环，则表示毫无规律

        // 初始化入度表
        HashMap<Character, Integer> indegreeMap = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegreeMap.put(c, 0);
            }
        }

        // 遍历单词表，构建图
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            int len = Math.min(cur.length, next.length);
            int j = 0;
            for (;j < len; j++) {
                if (cur[j] != next[j]) {
                    if (!graph.containsKey(cur[j])) {
                        graph.put(cur[j], new HashSet<>());
                    }
                    if (!graph.get(cur[j]).contains(next[j])) {
                        graph.get(cur[j]).add(next[j]);
                        indegreeMap.put(next[j], indegreeMap.get(next[j]) + 1);
                    }
                }
            }
            if (j < cur.length && j == next.length) {
                return "";
            }
        }

        // 通过拓扑排序，生成字典序
        StringBuilder res = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            res.append(cur);
            HashSet<Character> next = graph.get(cur);
            for (Character ch : next) {
                indegreeMap.put(ch, indegreeMap.get(ch) - 1);
                if (indegreeMap.get(ch) == 0) {
                    queue.offer(ch);
                }
            }
        }
        return res.length() == indegreeMap.size() ? res.toString() : "";
    }

}
