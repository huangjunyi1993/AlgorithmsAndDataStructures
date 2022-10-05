package _03经典面试题目.贪心;

/**
 * 给定一个全是小写字母的字符串str, 删除多余字符，使得每种字符只保留一个，并让最终结果的字符串的字典序最小
 *
 * 举例
 * str='acbc', 删掉第一个'c', 得到‘abc’，是所有结果字符串中字典序最小的。
 * str='dbcacbca’, 删掉第一个‘b’, 'c', 第二个‘c’,'a', 得到‘dabc’是所有结果字符串中字典序最小的。
 *
 * Created by huangjunyi on 2022/10/3.
 */
public class _02RemoveDuplicateLettersLessLexi {

    public static String remove(String str) {
        if (str == null || str.length() < 2) return str;
        char[] chs = str.toCharArray();
        // 计算词频
        int[] map = new int[26];
        for (int i = 0; i < chs.length; i++) {
            map[chs[i] - 'a']++;
        }

        /*
        遍历字符串
        每遍历一个字符，词频减减
        减到某个词频为0跳出循环
        期间记录最小的字符第一次出现的位置
         */
        int pickCharIndex = 0;
        for (int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            if (ch < chs[pickCharIndex]) pickCharIndex = i;
            map[ch - 'a']--;
            if (map[ch - 'a'] == 0) break;
        }

        // 从挑选的位置往后截取字符串，排除本次挑出的字符，继续递归
        return chs[pickCharIndex] + remove(str.substring(pickCharIndex + 1).replaceAll(String.valueOf(chs[pickCharIndex]), ""));
    }

}
