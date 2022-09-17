package _02进阶._16滑动窗口;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个整形数组arr，整数sum，
 * 如果arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值-sub中最小值 <= sum
 * 返回arr中达标子数组的数量
 * Created by huangjunyi on 2022/9/4.
 */
public class Window02 {

    public static int process(int[] arr, int sum) {
        if (arr == null || arr.length == 0) return 0;
        Deque<Integer> minDeque = new LinkedList<>();
        Deque<Integer> maxDeque = new LinkedList<>();
        int res = 0;
        int l = 0, r = 0;
        while (l < arr.length) {
            while (r < arr.length) {
                while (!minDeque.isEmpty() && arr[minDeque.peekLast()] > arr[r]) minDeque.pollLast();
                minDeque.addLast(arr[r]);
                while (!maxDeque.isEmpty() && arr[maxDeque.peekLast()] < arr[r]) maxDeque.pollLast();
                maxDeque.addLast(arr[r]);
                if (sum < arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()]) break;
                r++;
            }
            if (l == minDeque.peekFirst()) minDeque.pollFirst();
            if (l == maxDeque.peekFirst()) maxDeque.pollFirst();
            l++;
        }
        return res;
    }

}
