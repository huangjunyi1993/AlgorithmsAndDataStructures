package _03经典面试题目.动态规划;

/**
 * 给定两个字符串，求最长公共子串
 *
 * 使用动态规划空间压缩技巧
 * Created by huangjunyi on 2022/9/25.
 */
public class _04MaxLenSubStr {

    public static String getMaxSubStr(String str1, String str2) {
        if (str1 == null || str2 == null) return null;
        if (str1.length() == 0 || str2.length() == 0) return "";
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        /*
        使用row、col、i、j等几个变量，达到空间压缩的效果
        原本应该申请一个二维数组
        然后动态转移方程应该是
        dp[i][j] = chars1[j] == chars1[i] ? dp[i-1][j-1] + 1 : 0
        所以每个格子直依赖它左上方的值
        因此不需要二维数组
        只需一个row、col变量，记录起始行和起始列，每次遍历从左上方往右下方遍历
         */
        int row = 0;
        int col = chars1.length - 1;
        int max = 0;
        int end = -1;
        while (row < chars2.length) {
            int i = row;
            int j = col;
            int len = 0;
            while (i < chars2.length && j < chars1.length) {
                if (chars1[j] == chars1[i]) len++;
                else len = 0;
                if (len > max) {
                    max = len;
                    end = j;
                }
            }
            if (col > 0) col--;
            else row++;
        }
        return str1.substring(end - max + 1, end + 1);
    }

}
