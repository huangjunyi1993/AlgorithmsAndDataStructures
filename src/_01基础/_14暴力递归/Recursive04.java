package _01基础._14暴力递归;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个字符串，返回不重复的全排序
 * Created by huangjunyi on 2022/9/2.
 */
public class Recursive04 {

    public static List<String> getAllSortedResult(String str) {
        Set<String> set = new HashSet<>();
        int i = 0;
        char[] chars = str.toCharArray();
        process(chars, i, set);
        List<String> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    private static void process(char[] chars, int index, Set<String> set) {
        if (index == chars.length) {
            set.add(String.valueOf(chars));
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process(chars, index + 1, set);
            swap(chars, index, i);
        }

    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

}
