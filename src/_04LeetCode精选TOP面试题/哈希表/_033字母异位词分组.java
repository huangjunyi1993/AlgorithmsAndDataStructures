package _04LeetCode精选TOP面试题.哈希表;

import java.util.*;

/**
 * https://leetcode.cn/problems/group-anagrams/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _033字母异位词分组 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        /*
        每个字符串中的字符进行排序，然后作为key
        判断map中是否存在相同的key
        如果存在，放一组
        如果不在，map中初始化该组，保存这个key
         */
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = String.valueOf(chs);
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                map.get(key).add(str);
            }
        }
        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}
