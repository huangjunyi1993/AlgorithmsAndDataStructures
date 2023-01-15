package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/expression-add-operators/
 * Created by huangjunyi on 2023/1/15.
 */
public class _155给表达式添加运算符 {
    class Solution {
        public List<String> addOperators(String num, int target) {
            char[] chs = num.toCharArray(); // 原字符串
            char[] path = new char[num.length() + num.length() - 1]; // 拼接串
            List<String> res = new ArrayList<>(); // 答案
            long firstNum = 0; // 第一个数字
            if (chs[0] == '0') {
                // 第一个字符为0，第一个数字只能为0，不能和后面的字符拼成一个数字
                firstNum = 0;
                path[0] = '0';
                process(chs, 1, target, 0, firstNum, path, 1, res);
            } else {
                // 遍历第一个数字的所有情况，跑后续递归
                for (int i = 0; i < chs.length; i++) {
                    firstNum = firstNum * 10 + chs[i] - '0';
                    path[i] = chs[i];
                    process(chs, i + 1, target, 0, firstNum, path, i + 1, res);
                }
            }
            return res;
        }

        /**
         * @param chs 原字符串
         * @param index 到了原字符串的第i位
         * @param target 要凑出的目标数
         * @param left 左边不会变的数
         * @param cur 右边会变的数
         * @param path 拼接串
         * @param len 拼接串长度
         * @param res 答案
         */
        private void process(char[] chs, int index, int target,
                             long left, long cur,
                             char[] path, int len, List<String> res) {
            // base case：原串遍历完，看是否凑出了target，是则收答案
            if (index == chs.length) {
                if (left + cur == target) {
                    System.out.println(left + " " + cur + " " + target);
                    res.add(new String(path, 0, len));
                }
                return;
            }

            // 遍历当前字符与后面字符拼成一个答案的可能性，跑后续递归
            long n = 0;
            int j = len + 1;
            for (int i = index; i < chs.length; i++) {
                n = n * 10 + chs[i] - '0';
                path[j++] = chs[i];

                path[len] = '+';
                process(chs, i + 1, target, left + cur, n, path, j, res);

                path[len] = '-';
                process(chs, i + 1, target, left + cur, -n, path, j, res);

                path[len] = '*';
                process(chs, i + 1, target, left, + cur * n, path, j, res);

                if (chs[index] == '0') break;
            }
        }
    }
}