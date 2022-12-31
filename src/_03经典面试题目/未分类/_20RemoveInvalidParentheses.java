package _03经典面试题目.未分类;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/remove-invalid-parentheses/description/
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 * Created by huangjunyi on 2022/12/26.
 */
public class _20RemoveInvalidParentheses {
    class Solution {
        public List<String> removeInvalidParentheses(String s) {
            List<String> res = new ArrayList<>();
            process(s, res, 0, 0, new char[] {'(', ')'});
            return res;
        }

        /**
         * 从checkIndex往后开始检查，如果出现不配对（count < 0)，从removeIndex开始尝试删除
         * @param s
         * @param res
         * @param checkIndex
         * @param removeIndex
         * @param chs
         */
        private void process(String s, List<String> res, int checkIndex, int removeIndex, char[] chs) {
            int count = 0;
            // 从checkIndex往后开始检查
            for (int i = checkIndex; i < s.length(); i++) {
                if (s.charAt(i) == chs[0]) count++;
                if (s.charAt(i) == chs[1]) count--;
                if (count < 0) {
                    // 如果出现不配对（count < 0)，从removeIndex开始尝试删除
                    // 尝试删除，到位置i为止，i后面不合法的，交给递归去搞
                    for (int j = removeIndex; j <= i; j++) {
                        if (s.charAt(j) == chs[1] && (j == removeIndex || s.charAt(j - 1) != chs[1])) {
                            process(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, chs);
                        }
                    }
                    return;
                }
            }
            // 右括号都删除到不超过左括号，反转字符串，处理左括号
            String reversed = new StringBuilder(s).reverse().toString();
            if (chs[0] == '(') {
                process(reversed, res, 0, 0, new char[] {')', '('});
            } else {
                res.add(reversed);
            }
        }
    }
}
