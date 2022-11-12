package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/valid-anagram/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _116有效的字母异位词 {
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) return false;
            char[] chsS = s.toCharArray();
            char[] chsT = t.toCharArray();
            // 字符频率统计
            int[] charCount = new int[256];
            for (int i = 0; i < chsS.length; i++) {
                charCount[chsS[i]]++;
            }
            for (int i = 0; i < chsT.length; i++) {
                // 哪个字符扣到0，就立即返回false
                if (--charCount[chsT[i]] < 0) return false;
            }
            return true;
        }
    }
}
