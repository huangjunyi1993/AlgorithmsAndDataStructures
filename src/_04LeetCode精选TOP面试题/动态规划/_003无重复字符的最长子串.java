package _04LeetCode精选TOP面试题.动态规划;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 *
 * Created by huangjunyi on 2022/10/22.
 */
public class _003无重复字符的最长子串 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) return 0;
        /*
        枚举字符串中一个每个字符结尾，最长推到多长
        用一个dp表记录
        返回最长的答案
        dp[i]等于dp[i-1]和i字符上次出现的位置这两个值中的最大值

        然后通过一个pre遍历优化掉dp，因为每次遍历，只依赖到dp表前一项

        用数组优化掉用于记录字符上次出现位置的map
         */
        char[] chs = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        int cur = 0;
        int pre = -1;
        int len = 0;
        for (int i = 0; i < chs.length; i++) {
            pre = Math.max(pre, map[chs[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chs[i]] = i;
        }
        return len;
    }

}
