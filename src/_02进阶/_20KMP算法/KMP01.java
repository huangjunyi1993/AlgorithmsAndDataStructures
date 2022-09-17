package _02进阶._20KMP算法;

/**
 * kmp算法
 * Created by huangjunyi on 2022/9/4.
 */
public class KMP01 {

    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || match.length() == 0 || match.length() > str.length()) return -1;
        int[] next = getNext(match);
        int x = 0;
        int y = 0;
        while (x < str.length() && y < match.length()) {
            if (str.charAt(x) == match.charAt(y)) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == match.length() ? x - y : -1;
    }

    private static int[] getNext(String match) {
        if (match.length() == 1) return new int[]{-1};
        int[] next = new int[match.length()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < match.length()) {
            if (match.charAt(i - 1) == match.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn == 0) {
                next[i++] = 0;
            } else {
                cn = next[cn];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(getIndexOf("abbabbabbac", "abbabbac"));
    }
}
