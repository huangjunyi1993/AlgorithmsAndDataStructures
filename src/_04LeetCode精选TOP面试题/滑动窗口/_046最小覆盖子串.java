package _04LeetCode精选TOP面试题.滑动窗口;

/**
 * https://leetcode.cn/problems/minimum-window-substring/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _046最小覆盖子串 {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        char[] str = s.toCharArray();
        char[] pat = t.toCharArray();
        /*
        利用滑动窗口求解
        用一张map作为欠账表
        r不断往右括，在map上对应下标--，然后有效还款时all--
        all减到0，l开启往右缩，缩到不能再缩，结算
         */
        int[] map = new int[256]; // 欠账表
        for (int i = 0; i < pat.length; i++) {
            map[pat[i]]++; // 记录欠账信息
        }
        int all = t.length(); // 中欠账数
        int l = 0; // 左指针
        int r = 0; // 右指针
        int resL = -1; // 最小子串左边界
        int resR = -1; // 最小子串右边界
        int minLen = -1; // 最小子串长度
        while (r < str.length) {
            map[str[r]]--; // 还款
            if (map[str[r]] >= 0) { // 有效还款
                all--;
            }
            if (all == 0) { // 结算
                while (map[str[l]] < 0) { // l往右缩
                    map[str[l++]]++;
                }
                // 结算
                if (minLen == -1 || minLen > r - l + 1) {
                    minLen = r - l + 1;
                    resL = l;
                    resR = r;
                }
                map[str[l++]]++;
                all++;
            }
            r++;
        }
        return minLen == -1 ? "" : s.substring(resL, resR + 1);
    }
}
