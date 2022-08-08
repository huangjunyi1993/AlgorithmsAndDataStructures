package _01基础._11贪心算法;

import java.util.Arrays;

/**
 * 给定一个字符串数组，把数组里的所有字符串拼接起来，返回所有拼接结果中字典序最小的拼接结果
 */
public class Greed01 {

    public static String findLowestDictionaryOrderAppend(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        Arrays.sort(strs, (str1, str2) -> (str1 + str2).compareTo(str2 + str1));

        String result = "";
        for (String str : strs) {
            result += str;
        }

        return result;
    }

}
