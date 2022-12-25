package _03经典面试题目.动态规划;

/**
 * 给定一个字符串s，求s中有多少个字面值不同的子序列
 *
 * Created by huangjunyi on 2022/10/16.
 */
public class _038DistinctSubseq {

    public static int distinctSubseq(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 1;
        char[] chs = s.toCharArray();

        /*
        用一个dp表记录结尾字符和不同子序列的个数
        用一个all变量记录所有不同子序列的个数
        每次遍历一个字符，就以该字符结尾，与all中记录的所有子序列拼出新的子序列，然后减去重复的 => all - dp[ch - 'a']
         */

        int[] dp = new int[26];
        int all = 1;
        for (char ch : chs) {
            int add = all - dp[ch - 'a'];
            dp[ch - 'a'] += add;
            all += add;
        }
        return all;
    }

}
