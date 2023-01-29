package _06LeetCode热题HOT100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/partition-labels/
 * Created by huangjunyi on 2023/1/19.
 */
public class _763划分字母区间 {
    class Solution {
        public List<Integer> partitionLabels(String s) {
            char[] chs = s.toCharArray();
            int n = s.length();

            // key：字符，value：该字符在字符串中的最右位置
            HashMap<Character, Integer> charRightEdgeMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                charRightEdgeMap.put(chs[i], i);
            }

            List<Integer> res = new ArrayList<>(); // 返回的答案
            int count = 0; // 当前这一块的字符数
            int curEdge = 0; // 当前这一块的最右边界

            for (int i = 0; i < n; i++) {
                if (i > curEdge) { // 当前下标已经超出当前块的最右边界，结算
                    res.add(count);
                    curEdge = i;
                    count = 0;
                }
                // 尝试推高当前块的最右边界
                curEdge = Math.max(curEdge, charRightEdgeMap.get(chs[i]));
                count++;
            }
            if (count != 0) res.add(count);

            return res;
        }
    }
}
