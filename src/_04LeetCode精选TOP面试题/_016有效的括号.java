package _04LeetCode精选TOP面试题;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/valid-parentheses/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _016有效的括号 {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(' || chs[i] == '[' || chs[i] == '{') {
                stack.push(chs[i] == '(' ? ')' : (chs[i] == '[' ? ']' : '}'));
            } else {
                if (stack.isEmpty()) return false;
                char pop = stack.pop();
                if (pop != chs[i]) return false;
            }
        }
        return stack.isEmpty();
    }
}
