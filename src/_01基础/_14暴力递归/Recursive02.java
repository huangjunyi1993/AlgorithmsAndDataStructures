package _01基础._14暴力递归;

import java.util.Stack;

/**
 * 不使用额外空间使得栈中元素逆序
 * Created by huangjunyi on 2022/9/2.
 */
public class Recursive02 {

    /**
     * 取出栈底元素
     * @param stack
     * @return
     */
    public static int getBottom(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int bottom = getBottom(stack);
            stack.push(result);
            return bottom;
        }
    }

    /**
     * 把栈中元素逆序
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        int bottom = getBottom(stack);
        reverse(stack);
        stack.push(bottom);
    }

}
