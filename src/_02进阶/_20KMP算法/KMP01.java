package _02进阶._20KMP算法;

/**
 * kmp算法
 * Created by huangjunyi on 2022/9/4.
 */
public class KMP01 {

    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || match.length() == 0 || match.length() > str.length()) return -1;
        // 根据匹配串，求出next数组
        int[] next = getNext(match);
        int x = 0; // 字符串指针
        int y = 0; // 匹配串指针
        // 进行匹配
        while (x < str.length() && y < match.length()) {
            if (str.charAt(x) == match.charAt(y)) {
                // 匹配，两个指针++
                x++;
                y++;
            } else if (next[y] != -1) {
                // 匹配失败，匹配串指针调回next[y]位置，相当于匹配串往右推
                y = next[y];
            } else {
                // 此时y回到0了，next[0] == -1，表示以x开头不可能匹配上了，x往后移一位
                x++;
            }
        }
        // 出来了，如果匹配串指针越界了，表示匹配成功了，否则就是匹配串失败了
        return y == match.length() ? x - y : -1;
    }

    private static int[] getNext(String match) {
        if (match.length() == 1) return new int[]{-1};
        int[] next = new int[match.length()];
        next[0] = -1;
        next[1] = 0;
        int j = 0; // 代表左边前缀串长度，也表示要与i-1位置字符比较的字符的位置
        int i = 2; // 现在求next数组i位置的值
        while (i < match.length()) {
            // 前缀串后一个字符，和i-1位置字符相等，则next[i]就是前缀串长度+1
            if (match.charAt(i - 1) == match.charAt(j)) {
                next[i++] = ++j;
            } else if (j == 0) {
                // j都回到0了，还不匹配，那么next[i]只能是0，表示该位置i没有前缀串与后缀串匹配
                next[i++] = 0;
            } else {
                // 不匹配，根据next数组往左跳
                j = next[j];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(getIndexOf("abbabbabbac", "abbabbac"));
    }
}
