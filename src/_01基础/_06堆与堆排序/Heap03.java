package _01基础._06堆与堆排序;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 几乎有序的数组排序问题：
 * 一个数组几乎有序，数组在排序完之后，每一个数距离原位置移动的距离不超过k
 * 给定一个数组，与k，对数组进行排序
 */
public class Heap03 {

    public static void sort(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length - 1, k); index++) {
            heap.add(arr[index]);
        }

        int i = 0;
        for (; index < arr.length; index++) {
            arr[i++] = heap.poll();
            heap.add(arr[index]);
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

}
