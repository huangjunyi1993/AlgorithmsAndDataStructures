package _01基础._06堆与堆排序;

import java.util.Arrays;

/**
 * 堆排序
 *
 * 1、模拟从数组放入元素，调整为大顶堆
 * 2、每一步，把堆顶元素换到数组尾部，然后size--，然后从堆顶开始做向下调整
 * 3、循环第二步，直到size为0
 */
public class Heap02 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        //模拟从数组放入元素，调整为大顶堆
        for (int i = 0; i < arr.length; i++) {
            floatUp(arr, i);
        }

        int heapSize = arr.length;

        while (true) {
            swap(arr, 0, --heapSize);
            if (heapSize == 0) break;
            sink(arr, heapSize);
        }

    }

    private static void floatUp(int[] arr, int index) {
        //堆调整：上浮
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void sink(int[] arr, int heapSize) {
        //堆调整：下沉
        int index = 0;
        int l = index * 2 + 1;
        int r;
        int bigSon;
        //还有左孩子，就继续比较，没有左孩子，就不再比较了
        while (l < heapSize) {
            r = index * 2 + 2;
            bigSon = r < heapSize && arr[r] > arr[l] ? r : l;
            if (arr[bigSon] <= arr[index]) break;
            swap(arr, index, bigSon);
            index = bigSon;
            l = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
