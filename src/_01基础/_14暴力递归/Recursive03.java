package _01基础._14暴力递归;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个字符串，返回所有不重复的子序列
 * Created by huangjunyi on 2022/9/2.
 */
public class Recursive03 {

    public static List<String> getSubSeq(String str) {
        char[] chars = str.toCharArray();
        Set<String> set = new HashSet<>();
        String path = "";
        int index = 0;
        process(chars, index, set, path);
        List<String> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    private static void process(char[] chars, int index, Set<String> set, String path) {
        if (index == chars.length) {
            set.add(path);
            return;
        }

        process(chars, index + 1, set, path);
        process(chars, index + 1, set, path + chars[index]);

    }

}
