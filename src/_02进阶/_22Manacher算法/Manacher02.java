package _02进阶._22Manacher算法;

/**
 * 给定一个字符串，要求往右边补字符，使其整体变成回文串
 * 返回需要补的字符串
 * Created by huangjunyi on 2022/9/10.
 */
public class Manacher02 {

    public static String process(String str) {
        if (str == null || str.length() == 0) return null;
        char[] chars = addSeparator(str);
        int r = -1;
        int c = -1;
        int[] pArr = new int[chars.length];
        int maxContainsEnd = -1;
        for (int i = 0; i < chars.length; i++) {
            pArr[i] = r > i ? Math.max(pArr[2 * c - i], r - i) : 1;
            while (i - pArr[i] > -1 && i + pArr[i] < chars.length) {
                if (chars[i - pArr[i]] == chars[i + pArr[i]]) pArr[i]++;
                else break;
            }
            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }
            if (r == chars.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        if (maxContainsEnd == -1) {
            char[] res = new char[str.length()];
            int index = 0;
            for (int i = str.length() - 1; i >= 0; i--) {
                res[index++] = str.charAt(i);
            }
            return new String(res);
        }
        int len = str.length() - maxContainsEnd + 1;
        char[] res = new char[len];
        int index = 0;
        for (int i = len - 1; i >= 0; i--) {
            res[index++] = str.charAt(i);
        }
        return new String(res);
    }

    private static char[] addSeparator(String str) {
        char[] chars = str.toCharArray();
        char[] newChars = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return newChars;
    }

    public static void main(String[] args) {
        System.out.println(process("rstabccba"));
        System.out.println(process("rstabccbaz"));
    }

}
