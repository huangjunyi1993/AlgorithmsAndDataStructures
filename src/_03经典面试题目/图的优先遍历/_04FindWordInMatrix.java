package _03经典面试题目.图的优先遍历;

/**
 *
 * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
 * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
 * 比如：
 * char[][] m = {
 *     { 'a', 'b', 'z' },
 *     { 'c', 'd', 'o' },
 *     { 'f', 'e', 'o' },
 * };
 * word = "zooe"
 * 是可以找到的
 *
 * 设定1：可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
 *
 * 设定2：不可以走重复路的情况下，返回能不能找到
 * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
 *
 * 写出两种设定下的code
 *
 * Created by huangjunyi on 2022/12/26.
 */
public class _04FindWordInMatrix {

    // 可以走重复的设定
    public static boolean findWord1(char[][] m, String word) {
        if (word == null || word.length() == 0) return true;
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return false;
        char[] chs = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                // 矩阵中每个点都作为开始尝试一遍
                if (process1(m, chs, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean process1(char[][] m, char[] chs, int i, int j, int k) {
        // 走出整个word了
        if (k == chs.length) return true;
        // 越界或者字符不相等，false
        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] != chs[k]) return false;
        // 上下左右四个方向
        return process1(m, chs, i - 1, j, k + 1)
                || process1(m, chs, i + 1, j, k + 1)
                || process1(m, chs, i, j - 1, k + 1)
                || process1(m, chs, i, j +1, k + 1);
    }

    // 不可以走重复路的设定
    public static boolean findWord2(char[][] m, String word) {
        if (word == null || word.length() == 0) return true;
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) return false;
        char[] chs = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                // 矩阵中每个点都作为开始尝试一遍
                if (process2(m, chs, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean process2(char[][] m, char[] chs, int i, int j, int k) {
        // 走出整个word了
        if (k == chs.length) return true;
        // 越界或者回头路或者字符不相等，false
        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length || m[i][j] == 0 || m[i][j] != chs[k]) return false;

        // 标记为已走过
        char tmp = m[i][j];
        m[i][j] = 0;

        // 上下左右四个方向
        boolean res = process1(m, chs, i - 1, j, k + 1)
                || process1(m, chs, i + 1, j, k + 1)
                || process1(m, chs, i, j - 1, k + 1)
                || process1(m, chs, i, j +1, k + 1);

        // 恢复现场
        m[i][j] = tmp;

        return res;
    }

    public static void main(String[] args) {
        char[][] m = { { 'a', 'b', 'z' }, { 'c', 'd', 'o' }, { 'f', 'e', 'o' }, };
        String word1 = "zoooz";
        String word2 = "zoo";
        // 可以走重复路的设定
        System.out.println(findWord1(m, word1));
        System.out.println(findWord1(m, word2));
        // 不可以走重复路的设定
        System.out.println(findWord2(m, word1));
        System.out.println(findWord2(m, word2));

    }

}
