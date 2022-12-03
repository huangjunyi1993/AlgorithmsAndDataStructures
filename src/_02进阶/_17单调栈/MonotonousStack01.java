package _02进阶._17单调栈;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组，返回数组中每一个数左边和右边比它小的数的下标
 * Created by huangjunyi on 2022/9/4.
 */
public class MonotonousStack01 {

    public static int[][] process(int[] arr) {
        int[][] res = new int[arr.length][];
        //单调栈，栈元素是list，防止数组中有相同的数字，相同的数字会放到同一个list里面去
        Stack<List<Integer>> monotonousStack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            //如果栈顶元素对应数组中的数字比当前压入的下标对应的数字大，则弹出栈顶元素结算
            while (!monotonousStack.isEmpty() && arr[monotonousStack.peek().get(0)] > arr[i]) {
                List<Integer> list = monotonousStack.pop();
                // 如果弹出后栈空了，则左边是-1，如果不为空就是此时的栈顶链表的尾部
                int leftLessIndex = monotonousStack.isEmpty() ? -1 : monotonousStack.peek().get(monotonousStack.peek().size() - 1);
                // 一旦弹出，弹出的链表中所有元素都结算
                for (int index : list) {
                    // 左边是leftLessIndex，右边则是要压入的数的下标
                    int[] item = {leftLessIndex, i};
                    res[index] = item;
                }
            }
            //如果栈顶元素对应数组中的数字与压入的下标对应的数字相等，放到栈顶list的尾部
            if (!monotonousStack.isEmpty() && arr[monotonousStack.peek().get(0)] == arr[i]) {
                monotonousStack.peek().add(i);

            }
            //到这里，说明要压入的下标对应的数字比栈顶元素对应的数字大，单独组成一个list压入栈中
            else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                monotonousStack.push(list);
            }
        }

        // 所有的数都进完了，现在栈中有可能还有元素没结算，单独弹出结算
        while (!monotonousStack.empty()) {
            List<Integer> list = monotonousStack.pop();
            // 如果弹出后栈空了，则左边是-1，如果不为空就是此时的栈顶链表的尾部
            int leftLessIndex = monotonousStack.isEmpty() ? -1 : monotonousStack.peek().get(monotonousStack.peek().size() - 1);
            for (int index : list) {
                // 右边则是-1，以内右边没有数比弹出的数小
                int[] item = {leftLessIndex, -1};
                res[index] = item;
            }
        }
        return res;
    }

}
