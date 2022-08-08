package _01基础._06堆与堆排序;

import java.util.Arrays;

/**
 * 堆结构
 */
public class Heap01 {

    private int[] arr;

    private int heapSize;

    public Heap01(int size) {
        arr = new int[size];
        heapSize = 0;
    }

    public void push(int num) {
        if (heapSize == arr.length) throw new RuntimeException("heap is full");
        arr[heapSize] = num;

        //堆调整：上浮
        floatUp(arr, heapSize++);
    }

    private void floatUp(int[] arr, int index) {
        //堆调整：上浮
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void sink(int[] arr, int heapSize) {
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

    public int pop() {
        if (heapSize == 0) throw new RuntimeException("heap is empty");
        int res = arr[0];

        swap(arr, 0, --heapSize);
        sink(arr, heapSize);
        return res;
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
