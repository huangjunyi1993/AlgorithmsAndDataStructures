package _03经典面试题目.栈;

import java.util.LinkedList;

/**
 * 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右 括号，返回公式的计算结果。
 * 【举例】
 * str="48((70-65)-43)+81"，返回-1816。
 * str="3+14"，返回7。
 * str="3+(14)"，返回7。
 * 【说明】 1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。 2.如果是负数，就需要用括号括起来，比如"4(-3)"。但如果负数作为公式的开头 或括号部分的开头，则可以没有括号，比如"-34"和"(-3*4)"都是合法的。 3.不用考虑计算过程中会发生溢出的情况。
 *
 * Created by huangjunyi on 2022/10/8.
 */
public class _01ExpressionComputer {

    public static int getResult(String expression) {
        char[] exp = expression.toCharArray();
        return process(exp, 0)[0];
    }

    /**
     * 递归处理表达式，
     * 遇到左括号继续往下递归，遇到结尾或者右括号停，
     * 返回[结果值，计算到的位置]
     *
     * @param exp 表达式
     * @param i 计算起始位置
     * @return [结果值，计算到的位置]
     */
    private static int[] process(char[] exp, int i) {
        // 用一个栈存储本轮递归的计算中间值，包括数值和符号，如[1,+,2,-](栈顶)
        LinkedList<String> stack = new LinkedList<>();
        int cur = 0;
        while (i != exp.length && i != ')') {
            if (exp[i] >= '0' && exp[i] <= '9') {
                // 遇到数值，直接计算cur
                cur = cur * 10 + exp[i++] - '0';
            } else if (exp[i] != '(') {
                // 遇到符号位，把cur和符号入栈
                addNum(stack, cur);
                stack.addLast(String.valueOf(exp[i++]));
                cur = 0;
            } else {
                int[] bra = process(exp, i + 1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(stack, cur);
        return new int[] {getNum(stack), i};
    }

    private static int getNum(LinkedList<String> stack) {
        int res = 0;
        boolean add = true;
        while (!stack.isEmpty()) {
            String cur = stack.pollLast();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                int num = Integer.valueOf(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

    private static void addNum(LinkedList<String> stack, int num) {
        if (!stack.isEmpty()) {
            String top = stack.pollLast();
            if (top.equals("+") || top.equals("-")) {
                stack.addLast(top);
            } else {
                // 如果栈顶是*或/，要先计算结果再入栈
                int pre = Integer.valueOf(stack.pollLast());
                num = top.equals("*") ? num * pre : pre / num;
            }
        }
        stack.addLast(String.valueOf(num));
    }

}
