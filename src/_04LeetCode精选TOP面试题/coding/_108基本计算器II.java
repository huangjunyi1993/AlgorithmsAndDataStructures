package _04LeetCode精选TOP面试题.coding;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/basic-calculator-ii/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _108基本计算器II {
    class Solution {
        public int calculate(String s) {
            // 数queue
            LinkedList<Integer> numQueue = new LinkedList<>();
            // 符号queue
            LinkedList<Character> calQueue = new LinkedList<>();
            char[] chs = s.toCharArray();
            int num = 0;
            for (int i = 0; i < chs.length; i++) {
                char ch = chs[i];
                if (ch >= '0' && ch <= '9') {
                    // 是数字，累计到num
                    num = num * 10 + ch - '0';
                } else if (ch == ' ') {
                    // 空格跳过
                    continue;
                } else {
                    // 是符号，如去前面一个符号是*或者/，要先结算
                    if (!calQueue.isEmpty() && (calQueue.peekLast() == '*' || calQueue.peekLast() == '/')) {
                        char cal = calQueue.pollLast();
                        Integer num1 = numQueue.pollLast();
                        if (cal == '*') {
                            numQueue.addLast(num1 * num);
                        } else {
                            numQueue.addLast(num1 / num);
                        }
                        num = 0;
                        calQueue.addLast(ch);
                    } else {
                        // 前一个符号不是*或者/，直接入队列
                        numQueue.addLast(num);
                        calQueue.addLast(ch);
                        num = 0;
                    }
                }
            }
            // 遍历完后，会剩下一个数没有处理
            if (!calQueue.isEmpty() && (calQueue.peekLast() == '*' || calQueue.peekLast() == '/')) {
                char cal = calQueue.pollLast();
                Integer num1 = numQueue.pollLast();
                if (cal == '*') {
                    numQueue.addLast(num1 * num);
                } else {
                    numQueue.addLast(num1 / num);
                }
            } else {
                numQueue.addLast(num);
            }

            // 剩下的及时加减符号的计算，从左往右
            while (!calQueue.isEmpty()) {
                Integer num1 = numQueue.pollFirst();
                Integer num2 = numQueue.pollFirst();
                Character ch = calQueue.pollFirst();
                switch (ch) {
                    case '+' :
                        numQueue.addFirst(num1 + num2);
                        break;
                    case '-' :
                        numQueue.addFirst(num1 - num2);
                        break;
                }
            }

            // 算完，数队列剩一个数，弹出
            return numQueue.poll();
        }
    }
}
