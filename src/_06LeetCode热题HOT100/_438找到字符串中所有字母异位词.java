package _06LeetCode热题HOT100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _438找到字符串中所有字母异位词 {
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            char[] sChs = s.toCharArray();
            char[] pChs = p.toCharArray();
            int sn = sChs.length;
            int pn = pChs.length;
            List<Integer> res = new ArrayList<>();
            if (pn > sn) return res;
            // 欠账
            Map<Character, Integer> map = new HashMap<>();
            int all = 0;
            for (char pCh : pChs) {
                if (map.containsKey(pCh)) {
                    map.put(pCh, map.get(pCh) + 1);
                } else {
                    map.put(pCh, 1);
                }
                all++;
            }
            // 初始化窗口，窗口大小是p的长度，把右边界先推到p.len - 1
            for (int i = 0; i < pn - 1; i++) {
                if (map.containsKey(sChs[i])) {
                    map.put(sChs[i], map.get(sChs[i]) - 1);
                    // >=0，在没有多还的情况下，all就减1
                    if (map.get(sChs[i]) >= 0) {
                        all--;
                    }
                }
            }

            // 窗口右划，更新欠账信息，all扣到0，记录答案
            for (int end = pn - 1, start = 0; end < sn; end++, start++) {
                if (map.containsKey(sChs[end])) {
                    map.put(sChs[end], map.get(sChs[end]) - 1);
                    // >=0，在没有多还的情况下，all就减1
                    if (map.get(sChs[end]) >= 0) {
                        all--;
                    }
                }
                if (all == 0) res.add(start);
                if (map.containsKey(sChs[start])) {
                    map.put(sChs[start], map.get(sChs[start]) + 1);
                    // 小于0的时多还的，只有有效还款，all才加1
                    if (map.get(sChs[start]) > 0) {
                        all++;
                    }
                }
            }
            return res;
        }
    }
}
