package _04LeetCode精选TOP面试题;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-ladder/?favorite=2ckc81c
 * Created by huangjunyi on 2022/12/16.
 */
public class _069单词接龙 {
    class Solution {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) return 0;
            Set<String> wordSet = new HashSet<>(wordList);
            wordSet.add(beginWord);
            // 单词跳转表
            Map<String, Set<String>> wordTable = initWordTable(wordSet);
            // 宽度优先遍历用的队列
            LinkedList<String> queue = new LinkedList<>();
            // 层数map，记录单词与遍历到的层数的映射关系
            Map<String, Integer> levelMap = new HashMap<>();
            levelMap.put(beginWord, 1);
            queue.add(beginWord);
            // 去重表，防止死循环
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);
            while (!queue.isEmpty()) {
                String cur = queue.poll();
                if (cur.equals(endWord)) return levelMap.get(cur);
                if (!wordTable.containsKey(cur)) continue;
                for (String word : wordTable.get(cur)) {
                    if (visited.contains(word)) continue;
                    queue.offer(word);
                    levelMap.put(word, levelMap.get(cur) + 1);
                    visited.add(word);
                }
            }
            return 0;
        }

        /**
         * 生成单词跳转表
         * @param wordSet
         * @return
         */
        private Map<String, Set<String>> initWordTable(Set<String> wordSet) {
            Map<String, Set<String>> wordTable = new HashMap<>();
            for (String word : wordSet) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (chs[i] == ch) continue;
                        char oldCh = chs[i];
                        chs[i] = ch;
                        String newWord = String.valueOf(chs);
                        if (wordSet.contains(newWord)) {
                            if (!wordTable.containsKey(word)) {
                              wordTable.put(word, new HashSet<>());
                            }
                            wordTable.get(word).add(newWord);
                        }
                        chs[i] = oldCh;
                    }
                }
            }
            return wordTable;
        }
    }
}
