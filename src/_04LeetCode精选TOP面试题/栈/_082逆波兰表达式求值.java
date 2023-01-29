package _04LeetCode精选TOP面试题.栈;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/4.
 */
public class _082逆波兰表达式求值 {
    class Solution {
        public int evalRPN(String[] tokens) {
            // 遇到数字压栈，遇到运算符弹出两个计算后压回栈中
            LinkedList<Integer> stack = new LinkedList<>();
            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i];
                if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                    compute(stack, token);
                } else {
                    stack.push(Integer.valueOf(token));
                }
            }
            return stack.peek();
        }

        private void compute(LinkedList<Integer> stack, String token) {
            Integer num2 = stack.pop();
            Integer num1 = stack.pop();
            switch (token) {
                case "+":
                    stack.push(num1 + num2);
                    break;
                case "-":
                    stack.push(num1 - num2);
                    break;
                case "*":
                    stack.push(num1 * num2);
                    break;
                case "/":
                    stack.push(num1 / num2);
                    break;
            }
        }
    }
}
