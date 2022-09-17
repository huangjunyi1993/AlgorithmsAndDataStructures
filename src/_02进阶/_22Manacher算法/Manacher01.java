package _02进阶._22Manacher算法;

/**
 * 给定一个字符串，求出它的最长回文子串，利用Manacher算法求解
 * Created by huangjunyi on 2022/9/10.
 */
public class Manacher01 {

    public static int getMaxSubPalindromeString(String str) {
        if (str == null || str.length() == 0) return 0;
        int r = -1;
        int c = -1;
        char[] chars = addSeparator(str);
        int[] rArr = new int[chars.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            rArr[i] = r > i ? Math.max(rArr[2 * c - i], r - i) : 1;
            while (i + rArr[i] < chars.length && i - rArr[i] > -1) {
                if (chars[i + rArr[i]] == chars[i - rArr[i]]) rArr[i]++;
                else break;
            }
            if (i + rArr[i] > r) {
                r = i + rArr[i];
                c = i;
            }
            max = Math.max(max, rArr[i]);
        }
        return max - 1;
    }

    private static char[] addSeparator(String str) {
        char[] chars = str.toCharArray();
        char[] newChars = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return newChars;
    }

    public static void main(String[] args) {
        System.out.println(getMaxSubPalindromeString("abcaaaaaacbasss"));
    }

}
