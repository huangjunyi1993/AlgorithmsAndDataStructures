package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _018括号生成 {
    public List<String> generateParenthesis(int n) {
        char[] path = new char[n * 2];
        List<String> res = new ArrayList<>();
        // 暴力递归求解
        process(path, res, 0, 0, n);
        return res;
    }

    private void process(char[] path, List<String> res, int index, int leftNum, int leftRest) {
        if (index == path.length) {
            res.add(String.valueOf(path));
        } else {
            // 左括号减去右括号之后，有剩余的左括号可以与右括号配对
            if (leftNum > 0) {
                path[index] = ')';
                process(path, res, index + 1, leftNum - 1, leftRest);
            }
            // 还有剩余的左括号可以放置
            if (leftRest > 0) {
                path[index] = '(';
                process(path, res, index + 1, leftNum + 1, leftRest - 1);
            }
        }
    }
}
