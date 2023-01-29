package _04LeetCode精选TOP面试题.前缀树;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-break-ii/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/2.
 */
public class _077单词拆分II {
    class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>(wordDict);
            LinkedList<String> path = new LinkedList<>();
            List<String> result = new ArrayList<>();
            process(0, 0, path, result, s, dict);
            return result;
        }

        private void process(int index, int start,
                             LinkedList<String> path, List<String> result,
                             String s, Set<String> dict) {
            if (index == s.length()) {
                if (start == s.length()) {
                    result.add(String.join(" ", path));
                }
                return ;
            }
            String subStr = s.substring(start, index + 1);
            if (dict.contains(subStr)) {
                path.addLast(subStr);
                process(index + 1, index + 1, path, result, s, dict);
                path.pollLast();
            }
            process(index + 1, start, path, result, s, dict);
        }
    }
}
