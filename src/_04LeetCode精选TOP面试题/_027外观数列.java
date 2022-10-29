package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/count-and-say/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/26.
 */
public class _027外观数列 {
    public String countAndSay(int n) {
        if (n < 1) return "";
        if (n == 1) return "1";
        char[] chs = countAndSay(n - 1).toCharArray();
        StringBuilder res = new StringBuilder();
        int times = 1;
        for (int i = 1; i < chs.length; i++) {
            if (chs[i - 1] == chs[i]) {
                times++;
            } else {
                res.append(times).append(chs[i - 1]);
                times = 1;
            }
        }
        res.append(times).append(chs[chs.length - 1]);
        return res.toString();
    }
}
