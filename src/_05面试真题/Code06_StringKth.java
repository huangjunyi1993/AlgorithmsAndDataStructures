package _05面试真题;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个长度len，表示一共有几位
 * 所有字符都是小写(a~z)，可以生成长度为1，长度为2，
 * 长度为3...长度为len的所有字符串
 * 如果把所有字符串根据字典序排序，每个字符串都有所在的位置。
 * 给定一个字符串str，给定len，请返回str是总序列中的第几个
 * 比如len = 4，字典序的前几个字符串为:
 * a aa aaa aaaa aaab ... aaaz ... azzz b ba baa baaa ... bzzz c ...
 * a是这个序列中的第1个，bzzz是这个序列中的第36558个
 * Created by huangjunyi on 2023/1/18.
 */
public class Code06_StringKth {
    public static int kth(String s, int len) {
        if (s == null || s.length() == 0 || s.length() > len) return -1;
        char[] chs = s.toCharArray();
        int res = 0;
        /*
        bzzz：
        第一位b之前的字符开头的，长度为3的，有A个，加b自己1，A+1=甲
        第二位z之前的字符开头的，长度为2的，有B个，加z自己1，B+1=乙
        第三位z之前的字符开头的，长度为1的，有C个，加z自己1，C+1=丁
        第四位z之前的字符开头的，长度为0的，有D个，加z自己1，D+1=戊
        res = 甲 + 乙 + 丁 + 戊

        求长度为len的有几个，f函数，假设len=3：
        26^3 + 26^2 + 26^1 + 26^0
         */
        for (int i = 0, rest = len - 1; i < chs.length; i++, rest--) {
            res += (chs[i] - 'a') * f(rest) + 1;
        }
        return res;
    }

    private static int f(int len) {
        int res = 1;
        for (int i = 1, base = 26; i <= len; i++, base *= 26) {
            res += base;
        }
        return res;
    }

    // 为了测试
    public static List<String> all(int len) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= len; i++) {
            char[] path = new char[i];
            process(path, 0, ans);
        }
        return ans;
    }

    // 为了测试
    public static void process(char[] path, int index, List<String> ans) {
        if (index == path.length) {
            ans.add(String.valueOf(path));
        } else {
            for (char c = 'a'; c <= 'z'; c++) {
                path[index] = c;
                process(path, index + 1, ans);
            }
        }
    }

    public static void main(String[] args) {
        int len = 4;
        // 暴力方法得到所有字符串
        List<String> ans = all(len);
        // 根据字典序排序，所有字符串都在其中
        ans.sort((a, b) -> a.compareTo(b));

        String test = "bzzz";
        // 根据我们的方法算出test是第几个？
        // 注意我们算出的第几个，是从1开始的
        // 而下标是从0开始的，所以变成index，还需要-1
        int index = kth(test, len) - 1;
        // 验证
        System.out.println(ans.get(index));
    }
}
