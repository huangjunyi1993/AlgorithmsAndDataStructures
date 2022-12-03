package _02进阶._22Manacher算法;

/**
 * 给定一个字符串，要求往字符串的后面补字符，使其整体变成回文串
 * 返回需要补的字符串
 * Created by huangjunyi on 2022/9/10.
 */
public class Manacher02 {

    public static String process(String str) {
        if (str == null || str.length() == 0) return null;
        // 对字符串做处理，中间添加分隔符
        char[] chars = addSeparator(str);
        int r = -1; // 最长回文右边界
        int c = -1; // 最长回文中心点
        int[] pArr = new int[chars.length]; // 回文半径数组
        int maxContainsEnd = -1; // 最长回文右边界推到字符串终止位置时的回文半径
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
                // 最长回文右边界r推到字符串终止位置了，抓住此时的回文半径，记到maxContainsEnd中，然后break
                maxContainsEnd = pArr[i];
                break;
            }
        }
        // 如果maxContainsEnd位-1，表示没有回文后缀，后面要补的就是整个字符串的逆序
        if (maxContainsEnd == -1) {
            char[] res = new char[str.length()];
            int index = 0;
            for (int i = str.length() - 1; i >= 0; i--) {
                res[index++] = str.charAt(i);
            }
            return new String(res);
        }
        // 算出要补充的字符串的长度
        int len = str.length() - maxContainsEnd + 1;
        char[] res = new char[len];
        int index = 0;
        // 字符串开头len长度的子串，逆序了，就是要在结尾补充的部分
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
