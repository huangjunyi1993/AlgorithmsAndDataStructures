package _04LeetCode精选TOP面试题;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-ladder-ii/
 * Created by huangjunyi on 2023/1/15.
 */
public class _156单词接龙II {
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            wordSet.add(beginWord);
            // 单词跳转表（邻接表）
            Map<String, Set<String>> wordTable = initWordTable(wordSet);

            // 层级表，word => 到beginWord的距离
            Map<String, Integer> startLevelMap = initLevelMap(beginWord, wordTable);
            List<List<String>> res = new ArrayList<>();
            if (!startLevelMap.containsKey(endWord)) {
                // endWord没有到startWord的距离，返回空集合
                return res;
            }
            // 层级表，word => 到endWord的距离
            Map<String, Integer> endLevelMap = initLevelMap(endWord, wordTable);

            LinkedList<String> path = new LinkedList<>();
            process(wordTable, startLevelMap, endLevelMap, path, res, beginWord, endWord);
            return res;
        }

        private void process(Map<String, Set<String>> wordTable, Map<String, Integer> startLevelMap,
                             Map<String, Integer> endLevelMap, LinkedList<String> path, List<List<String>> res,
                             String curWord, String endWord) {
            path.addLast(curWord);
            if (curWord.equals(endWord)) {
                res.add(new ArrayList<>(path));
            } else {
                Set<String> words = wordTable.get(curWord);
                if (words == null) return;
                for (String word : words) {
                    if (startLevelMap.get(word) == startLevelMap.get(curWord) + 1
                            && endLevelMap.get(word) == endLevelMap.get(curWord) - 1) {
                        process(wordTable, startLevelMap, endLevelMap, path, res, word, endWord);
                    }
                }
            }
            path.pollLast();
        }

        private Map<String, Integer> initLevelMap(String beginWord, Map<String, Set<String>> wordTable) {
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
                if (!wordTable.containsKey(cur)) continue;
                for (String word : wordTable.get(cur)) {
                    if (visited.contains(word)) continue;
                    queue.offer(word);
                    levelMap.put(word, levelMap.get(cur) + 1);
                    visited.add(word);
                }
            }
            return levelMap;
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
