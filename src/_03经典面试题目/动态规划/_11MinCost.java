package _03经典面试题目.动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如 s1 = “abcde”，s2 = “axbc”。
 * 返回1。s2删掉'x'就是s1的子串了。
 * Created by huangjunyi on 2022/9/25.
 */
public class _11MinCost {

    /**
     * 如果s2较小的时候的解法
     * @param s1
     * @param s2
     * @return
     */
    public static int getMinCost01(String s1, String s2) {
        /*
        枚举s2的多有子串
        保存在list中
        然后对list进行排序，按照长度从大到小排
        然后与s1进行匹配（使用KMP，或者直接使用String的api）
        如果匹配成功，则立即s2长度减去当前子串长度的值
         */
        List<String> s2SubList = getSubList(s2);
        s2SubList.sort((o1, o2) -> o2.length() - o1.length());
        for (String subStr : s2SubList) {
            if (s1.contains(subStr)) return s2.length() - subStr.length();
        }
        return s2.length();
    }

    private static List<String> getSubList(String s2) {
        char[] chars = s2.toCharArray();
        List<String> subList = new ArrayList<>();
        getSubList(chars, 0, "", subList);
        return subList;
    }

    private static void getSubList(char[] chars, int cur, String path, List<String> subList) {
        if (cur == chars.length) {
            subList.add(path);
            return;
        }
        getSubList(chars, cur + 1, path, subList);
        getSubList(chars, cur + 1, path + chars[cur], subList);
    }

    /**
     * s1较小时的解法
     * @param s1
     * @param s2
     * @return
     */
    public static int getMinCost02(String s1, String s2) {
        int res = Integer.MAX_VALUE;
        char[] chars2 = s2.toCharArray();
        /*
        枚举s1的子串
        求s2和s1的子串的编辑距离
        ic和rc为系统最大值，dc为1
        然后在所有距离中选最小
         */
        for (int i = 0; i < s1.length(); i++) {
            for (int j = i + 1; j <= s1.length(); j++) {
                String s1Sub = s1.substring(i, j);
                res = Math.min(res, distance(chars2, s1Sub.toCharArray()));
            }
        }
        return res;
    }

    private static int distance(char[] chars2, char[] s1SubChars) {
        int[][] dp = new int[chars2.length][s1SubChars.length];
        dp[0][0] = chars2[0] == s1SubChars[0] ? 0 : Integer.MAX_VALUE;
        for (int i = 1; i < s1SubChars.length; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int j = 1; j < chars2.length; j++) {
            dp[j][0] = (dp[j - 1][0] != Integer.MAX_VALUE || chars2[j] == s1SubChars[0]) ? j : Integer.MAX_VALUE;
        }
        for (int i = 1; i < chars2.length; i++) {
            for (int j = 1; j < s1SubChars.length; j++) {
                /*
                1、如果str2字符串第i位和是s1子串的第j位相等，看str2的0~i-1和s1子串0~j-1是否有效则沿用，代表当前位不用删
                2、如果str2字符串第i位和是s1子串的第j位不相等，看str2的0~i-1和s1子串0~j是否有效，有效则加1，代表多删1位
                 */
                dp[i][j] = Integer.MAX_VALUE;
                if (chars2[i] == s1SubChars[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
            }
        }
        return dp[chars2.length - 1][s1SubChars.length - 1];
    }

    /**
     * s1较小时的解法，优化后
     * @param s1
     * @param s2
     * @return
     */
    public static int getMinCost03(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return s2.length();
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int N = chars1.length;
        int M = chars2.length;
        /*
        dp表，M行N列
        M是s2的长度
        N是s1的长度
        dp[i][j]表示s2从0~i的子串，删多上个字符，能变成s1从0~j的子串
         */
        int[][] dp = new int[M][N];
        int res = M;
        /*
        遍历s1字符串的每个子串
        每次遍历，枚举s1从start列开始，往后的所有子串(start~end)
        然后内存嵌套的for循环，枚举s2的子串，填dp表，得出dp[M-1][end]，表示s2删除多少个字符能到s1的子串start~end
        dp[M-1][end]再与结果res做对比
         */
        for (int start = 0; start < N; start++) {
            /*
            先填好start列
            如个当前行对应s2的字符与s1的start列的字符相等，则填row，表示删除row个字符，s2能到s1的start列字符
            或者前一行的start不为M，也可以通过删除row个字符，s2能到s1的start列字符
            以上这两种情况，当前这个格子都可以填row
             */
            dp[0][start] = chars2[0] == chars1[start] ? 0 : M;
            for (int row = 1; row < M; row++) {
                dp[row][start] = (chars2[row] == chars1[start] || dp[row - 1][start] != M) ? row : M;
            }
            res = Math.min(res, dp[M - 1][start]);
            /*
            遍历枚举s1从start列开始的子串，直到长度超过s2的长度
             */
            for (int end = start + 1; end < N && end - start + 1 <= M; end++) {
                /*
                从first行开始填这一列，因为first行以前的，代表的s2长度小于当前s1子串的长度，不可能删到当前s1的子串
                 */
                int first = end - start;
                dp[first][end] = (chars2[first] == chars1[end] && dp[first - 1][end - 1] != M) ? 0 : M;
                for (int row = first + 1; row < M; row++) {
                    /*
                    1、长度M
                    2、如果当前列前一行不等于M，那么多删一个字符，即可到当前s1子串，dp[row - 1][end] + 1
                    3、如果前一列前一行不等于M，并且当前行对应s2中的字符，等于end列对应s1中的字符，那么当前s2字符不用删除，取dp[row - 1][end - 1]
                    以上三种情况的结果值中的最小值，填到当前格子
                     */
                    dp[row][end] = M;
                    if (dp[row - 1][end] != M) dp[row][end] = dp[row - 1][end] + 1;
                    if (dp[row - 1][end - 1] != M && chars2[row] == chars1[end]) dp[row][end] = Math.min(dp[row][end], dp[row - 1][end - 1]);
                }
                res = Math.min(res, dp[M - 1][end]);
            }
        }
        return res;
    }

}
