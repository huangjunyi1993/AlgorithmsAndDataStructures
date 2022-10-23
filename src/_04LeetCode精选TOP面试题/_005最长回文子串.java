package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _005最长回文子串 {
    public String longestPalindrome(String s) {
        /*
        利用Manacher算法求解
         */

        // 获得处理串，加#作为虚轴
        char[] chs = getManacherStr(s.toCharArray());
        int[] rArr = new int[chs.length];
        int R = -1; // 回文最右边界
        int C = -1; // 回文最右中心点
        int maxR = 0; // 最长回文半径
        int maxC = Integer.MIN_VALUE; // 最长回文中心点

        for (int i = 0; i < chs.length; i++) {
            // i没有被右边界包住，rArr[i] = 1，正常尝试扩
            // i被右边界包住，可以优化，i`的半径和右边界到i的长度，取最小值
            rArr[i] = i < R ? Math.min(rArr[2 * C - i], R - i) : 1;
            // 尝试扩，i`左边界和最右回文左边界压线，i没被包住，两种情况
            while (i + rArr[i] < chs.length && i - rArr[i] > -1) {
                if (chs[i + rArr[i]] == chs[i - rArr[i]]) rArr[i]++;
                else break;
            }
            // 更新最右边界和最右中心点
            if (i + rArr[i] > R) {
                R = i + rArr[i];
                C = i;
            }
            // 更新最长边界和最长中心点
            if (rArr[i] > maxR) {
                maxR = rArr[i];
                maxC = i;
            }
        }
        // 映射回原串，取出最长回文串
        maxC = (maxC - 1) / 2;
        maxR = maxR - 1;
        return s.substring((maxR & 1) == 0 ? maxC - (maxR / 2) + 1 : maxC - (maxR / 2), maxC + (maxR / 2) + 1);
    }

    private char[] getManacherStr(char[] oldChs) {
        char[] chs = new char[oldChs.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            if (i % 2 == 0) chs[i] = '#';
            else chs[i] = oldChs[index++];
        }
        return chs;
    }
}
