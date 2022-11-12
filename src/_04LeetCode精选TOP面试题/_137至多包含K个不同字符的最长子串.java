package _04LeetCode精选TOP面试题;

/**
 * 给定一个字符串，和一个整形K
 * 返回字符串中不同字符个数不超过K的最长子串长度
 * Created by huangjunyi on 2022/11/12.
 */
public class _137至多包含K个不同字符的最长子串 {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        /*
        滑动窗口 + 记账表
         */
        int[] count = new int[256];
        int r = 0;
        int res = 0;
        int diff = 0;
        char[] chs = s.toCharArray();
        for (int l = 0; l < chs.length; l++) {
            while (l < s.length() && (diff < k || (diff == k && count[chs[r]] > 0))) {
                diff += count[chs[r]] == 0 ? 1 : 0;
                count[chs[r]]++;
                r++;
            }
            res = Math.max(res, r - l);
            diff -= count[chs[l]] == 1 ? 1 : 0;
            count[chs[l]]--;
        }
        return res;
    }

}
