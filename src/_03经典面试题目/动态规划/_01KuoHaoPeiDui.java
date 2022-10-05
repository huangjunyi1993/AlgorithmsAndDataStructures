package _03经典面试题目.动态规划;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串的长度
 * Created by huangjunyi on 2022/9/17.
 */
public class _01KuoHaoPeiDui {

    public static int maxLength(String str) {
        if (str == null || str.length() < 2) return 0;
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];
        int max = 0;
        for (int i = 1; i < chars.length; i++) {
            /*
            如果当前位)，看dp前一位记录的长度，往前推对应长度，然后看是否是(，
            如果是，则dp当前位置最起码是前一位记录的长度+2，
            然后dp位置再继续往前推一位，看记录的长度是否大于零，
            是的话则代表可以接上，dp当前位置记录的长度继续扩大
             */
            if (chars[i] == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

}
