package _03经典面试题目.动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 输入: s = “abcabcbb”
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 “abc”，所以其长度为 3。
 *
 * Created by huangjunyi on 2022/10/3.
 */
public class _019LengthOfLongestNoRepeatSubstring {

    public static int getMaxNoRepeatLen(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chs = str.toCharArray();

        // 记录字符上次出现的位置
        Map<Character, Integer> chIndexMap = new HashMap<>();
        chIndexMap.put(chs[1], 0);

        int preLen = 1; // 以前一个字符结尾，最长的不重复子串长度
        int max = 1; // 返回的结果，最长的不重复子串长度

        for (int i = 1; i < chs.length; i++) {
            char curCh = chs[i];
            /*
            如果当前字符上次出现的位置，位于以上个字符结尾的最长不重复子串的区间内，
            那么以当前字符结尾的最长不重复子串，只能从该字符上次出现的位置后一位开始，到当前字符

            如果当前字符上次出现的位置，不在以上个字符结尾的最长不重复子串的区间内，
            那么以当前1字符结尾的最长不重复子串，就是在以上个字符结尾的最长不重复子串后面，添加当前字符
             */
            int curLen = chIndexMap.containsKey(curCh) && chIndexMap.get(curCh) >= i - preLen ?
                    i - chIndexMap.get(curCh) :
                    preLen + 1;
            max = Math.max(max, curCh);
            preLen = curLen;
        }
        return max;
    }

}
