package _03经典面试题目.动态规划;

/**
 * 对于一个字符串, 从前开始读和从后开始读是一样的, 我们就称这个字符串是回文串。
 * 例如"ABCBA","AA", "A" 是回文串, 而"ABCD", "AAB"不是回文串。
 * 牛牛特别喜欢回文串, 他手中有一个字符串s, 牛牛在思考能否从字 符串中移除部分(0个或多个)字符使其变为回文串，并且牛牛认为空串不是回文串。
 * 牛牛发现移除的方案可能有很多种, 希望你来帮他计算一下一共有多少种移除方案可以使s变为回文串。
 * 对于两种移除方案, 如果移除的字 符依次构成的序列不一样就是不同的方案。
 * 例如，XXY 4种 ABA 5种
 *
 * 【说明】 这是今年的原题，提供的说明和例子都很让人费解。
 * 现在根据当时题目的所有测试用例，重新解释当时的题目
 * 含义:
 * 1)"1AB23CD21"，你可以选择删除A、B、C、D，然后剩下子序列{1,2,3,2,1}，
 * 只要剩下的子序列是同一个，那 么就只算1种方法，和A、B、C、D选择什么样的删除顺序没有关系。
 * 2)"121A1"，其中有两个{1,2,1}的子序列，第一个{1,2,1}是由{位置0，位置1，位置2}构成，
 * 第二个{1,2,1} 是由{位置0，位置1，位置4}构成。这两个子序列被认为是不同的子序列。
 * 也就是说在本题中，认为字面值一样 但是位置不同的字符就是不同的。
 * 3)其实这道题是想求，str中有多少个不同的子序列，每一种子序列只对应一种删除方法，那就是把多余的东 西去掉，而和去掉的顺序无关。
 * 4)也许你觉得我的解释很荒谬，但真的是这样，不然解释不了为什么，XXY 4种 ABA 5种，而且其他的测 试用例都印证了这一点。

 * Created by huangjunyi on 2022/10/8.
 */
public class _031PalindromeWays {

    public static int getPalindromeWays(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chs = str.toCharArray();
        int N = chs.length;

        /*
        dp范围尝试模型
        dp[i][j]表示字符串从i到j位置，有多少种删除方法使其变成回文串
        ①、i和j位置都不保留
        ②、i位置保留
        ③、j位置保留
        ④、如果i位置字符和j位置字符相等，i和j位置都保留
        以上四种情况的方法数相加
        注意依赖的左侧和下方格子都是两种不同方案混杂的结果：
        dp[i+1][j]等于①和③的方法数相加
        dp[i][j-1]等于①和②的方法数相加
        dp[i + 1][j - 1]等于①的方法数
        所以dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]，才得出①+②+③的方法数
        然后单独判断④是否成立，成立则加上dp[i + 1][j - 1]+1，+1是因为中间空串，i和j位置字符也能组成回文串
         */

        // 先填好两条对角线
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
            if (i + 1 < N) {
                if (chs[i] == chs[i + 1]) {
                    dp[i][i + 1] = 3;
                } else {
                    dp[i][i + 1] = 2;
                }
            }
        }

        // 根据依赖关系，填好其他格子，注意依赖的左侧和下方格子都是两种不同方案混杂的结果
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                if (chs[i] == chs[j]) {
                    dp[i][j] += dp[i + 1][j - 1] + 1;
                }
            }
        }

        return dp[0][N - 1];
    }

    // 暴力方法
    public static int ways1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        char[] path = new char[s.length];
        return process(str.toCharArray(), 0, path, 0);
    }

    public static int process(char[] s, int si, char[] path, int pi) {
        if (si == s.length) {
            return isP(path, pi) ? 1 : 0;
        }
        int ans = process(s, si + 1, path, pi);
        path[pi] = s[si];
        ans += process(s, si + 1, path, pi + 1);
        return ans;
    }

    public static boolean isP(char[] path, int pi) {
        if (pi == 0) {
            return false;
        }
        int L = 0;
        int R = pi - 1;
        while (L < R) {
            if (path[L++] != path[R--]) {
                return false;
            }
        }
        return true;
    }

    public static String randomString(int len, int types) {
        char[] str = new char[len];
        for (int i = 0; i < str.length; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * types));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int N = 10;
        int types = 5;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * N);
            String str = randomString(len, types);
            int ans1 = ways1(str);
            int ans2 = getPalindromeWays(str);
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
