package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _021找出字符串中第一个匹配项的下标 {
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        char[] str = haystack.toCharArray();
        char[] mat = needle.toCharArray();
        /*
        KMP算法
         */
        int[] next = getNext(mat);
        int si = 0;
        int mi = 0;
        while (si != str.length && mi != mat.length) {
            if (str[si] == mat[mi]) {
                si++;
                mi++;
            } else if (mi == 0) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == mat.length ? si - mi : -1;
    }

    private int[] getNext(char[] mat) {
        if (mat.length == 1) return new int[] {-1};
        int[] next = new int[mat.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < mat.length) {
            if (mat[cn] == mat[i - 1]) {
                next[i++] = ++cn;
            } else if (cn == 0) {
                next[i++] = 0;
            } else {
                cn = next[cn];
            }
        }
        return next;
    }
}
