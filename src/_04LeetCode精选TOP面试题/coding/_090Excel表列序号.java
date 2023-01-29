package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/excel-sheet-column-number/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _090Excel表列序号 {
    class Solution {
        public int titleToNumber(String columnTitle) {
            char[] chs = columnTitle.toCharArray();
            int res = 0;
            for (char ch : chs) {
                // A B C
                // 26 ^ 2 * 1 + 26 ^ 1 * 2 + 26 ^ 0 * 3
                res = res * 26 + ((ch - 'A') + 1);
            }
            return res;
        }
    }
}
