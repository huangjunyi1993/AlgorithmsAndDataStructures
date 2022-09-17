package _02进阶._16滑动窗口;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 固定大小的窗口从左往右划过数组，返回每个窗口中的最大值
 * Created by huangjunyi on 2022/9/4.
 */
public class Window01 {

    public static int[] getMaxInWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w <= 0) return null;
        //用一个双端队列记录窗口内最大值的优先级，记录的时数组下标
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //每次往队列里面压入元素时，保证从左往右递减的顺序
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) deque.pollLast();
            deque.addLast(i);

            //如果判断到划出窗口的数的下标正好等于双端队列头部记录的下标，那边从双端队列弹出头部元素
            if (deque.peekFirst() == i - w) {
                deque.pollFirst();
            }

            //保证窗口的左边界来到下标0，才开始往结果集放入元素
            if (i >= w - 1) {
                res[index++] = arr[deque.peekFirst()];
            }
        }
        return res;
    }

}
