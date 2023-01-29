package _04LeetCode精选TOP面试题.双指针;

/**
 * https://leetcode.cn/problems/valid-palindrome/?favorite=2ckc81c
 * Created by huangjunyi on 2022/12/14.
 */
public class _068验证回文串 {
    class Solution {
        public boolean isPalindrome(String s) {
            char[] chs = s.toCharArray();
            // 左右指针
            int l = 0;
            int r = s.length() - 1;
            // 左右指针先偏移到字母或数字位置
            while (l < s.length() && !(chs[l] >= 'a' && chs[l] <= 'z') && !(chs[l] >= 'A' && chs[l] <= 'Z') && !(chs[l] >= '0' && chs[l] <= '9')) l++;
            while (r >= 0 && !(chs[r] >= 'a' && chs[r] <= 'z') && !(chs[r] >= 'A' && chs[r] <= 'Z') && !(chs[r] >= '0' && chs[r] <= '9')) r--;
            // 两指针往中间，遍历比较
            while (l < r) {
                // 两字符不相等
                if (chs[l] != chs[r]) {
                    if (chs[l] < chs[r]) {
                        // 如果是数字，则直接false，是字母，看是否大小写转换
                        if ((chs[l] >= '0' && chs[l] <= '9') || (chs[r] >= '0' && chs[r] <= '9') || (chs[l] + 32 != chs[r])) {
                            return false;
                        }
                    } else {
                        // 如果是数字，则直接false，是字母，看是否大小写转换
                        if ((chs[l] >= '0' && chs[l] <= '9') || (chs[r] >= '0' && chs[r] <= '9') || (chs[r] + 32 != chs[l])) {
                            return false;
                        }
                    }
                }
                // 左右指针往中间靠
                l++;
                r--;
                // 如果不是字母或数字，还要继续偏移
                while (l < s.length() && !(chs[l] >= 'a' && chs[l] <= 'z') && !(chs[l] >= 'A' && chs[l] <= 'Z') && !(chs[l] >= '0' && chs[l] <= '9')) l++;
                while (r >= 0 && !(chs[r] >= 'a' && chs[r] <= 'z') && !(chs[r] >= 'A' && chs[r] <= 'Z') && !(chs[r] >= '0' && chs[r] <= '9')) r--;
            }
            return true;
        }
    }
    public static void main(String[] args) {
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
        System.out.println((int) '0');
        System.out.println((int) '9');
        System.out.println((int) 'P');
    }
}
