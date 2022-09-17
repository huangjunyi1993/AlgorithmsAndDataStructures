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
                //小跟堆记录当前窗口的最小值
                while (!minDeque.isEmpty() && arr[minDeque.peekLast()] > arr[r]) minDeque.pollLast();
                minDeque.addLast(arr[r]);
                //大根堆记录当前窗口的最大值
                while (!maxDeque.isEmpty() && arr[maxDeque.peekLast()] < arr[r]) maxDeque.pollLast();
                maxDeque.addLast(arr[r]);
                //如果最大值减去最小值超了，最退出当前循环，否则r指针继续往右括
                if (sum < arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()]) break;
                r++;
            }
            // r-l为当前循环得出的达标子数组的数量，累加到结果中取
            res += (r - l);
            /*
            l指针往右推一位，继续下一轮循环
            但l++前，要检查两个堆的堆顶是否是l指针指向的下标，是的话要删除
            对于小顶堆，不存在值为l又不是堆顶的情况，因为不是堆顶代表后面有比它更小的数的下标，如果是这样，那么l在上面就已经弹出了
            对于大顶堆，不存在值为l又不是堆顶的情况，因为不是堆顶代表后面有比它更大的数的下标，如果是这样，那么l在上面就已经弹出了
             */
            if (l == minDeque.peekFirst()) minDeque.pollFirst();
            if (l == maxDeque.peekFirst()) maxDeque.pollFirst();
            l++;
        }
        return res;
    }

}
