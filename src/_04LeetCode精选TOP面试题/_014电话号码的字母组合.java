package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _014电话号码的字母组合 {
    public static char[][] phone = {
            {},
            {},
            { 'a', 'b', 'c' }, // 2
            { 'd', 'e', 'f' }, // 3
            { 'g', 'h', 'i' }, // 4
            { 'j', 'k', 'l' }, // 5
            { 'm', 'n', 'o' }, // 6
            { 'p', 'q', 'r', 's' }, // 7
            { 't', 'u', 'v' },   // 8
            { 'w', 'x', 'y', 'z' }, // 9
    };

    public List<String> letterCombinations(String digits) {
        /*
        深度优先遍历
         */
        char[] nums = digits.toCharArray();
        char[] path = new char[digits.length()];
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        process(nums, path, 0, res);
        return res;
    }

    private void process(char[] nums, char[] path, int i, List<String> res) {
        if (i == nums.length) {
            res.add(String.valueOf(path));
        } else {
            char[] chs = phone[nums[i] - '0'];
            for (int i1 = 0; i1 < chs.length; i1++) {
                path[i] = chs[i1];
                process(nums, path, i + 1, res);
            }
        }
    }
}
