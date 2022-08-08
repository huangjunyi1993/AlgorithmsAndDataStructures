package _01基础._06堆与堆排序;

import java.util.*;

/**
 * 可动态调整的堆：
 * 当堆中的元素的值发生变化时，可以重新调整该元素在堆中的位置
 */
public class Heap04<T> {

    private List<T> arr;
    private Map<T, Integer> indexMap;
    private int heapSize;
    private Comparator<T> comparator;

    public Heap04(Comparator<T> comparator) {
        this.comparator = comparator;
        this.arr = new ArrayList<>();
        indexMap = new HashMap<>();
        this.heapSize = 0;
    }

    public void push(T t) {
        int size = arr.size();
        arr.add(t);
        indexMap.put(t, size);
        floatUp(size);
        heapSize++;
    }

    public T pop() {
        T res = arr.get(0);
        swap(0, heapSize - 1);
        arr.remove(heapSize - 1);
        indexMap.remove(res);
        sink(0, --heapSize);
        return res;
    }

    public void resign(T t) {
        //元素的值发生变化，重新调整该元素在堆中的位置
        int index = indexMap.get(t);
        floatUp(index);
        sink(index, heapSize);
    }

    private void floatUp(int index) {
        //堆调整：上浮
        //while (arr[index] > arr[(index - 1) / 2]) {
        while (comparator.compare(arr.get(index), arr.get((index - 1) / 2)) > 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void sink(int index, int heapSize) {
        //堆调整：下沉
        int l = index * 2 + 1;
        int r;
        int bigSon;
        //还有左孩子，就继续比较，没有左孩子，就不再比较了
        while (l < heapSize) {
            r = index * 2 + 2;
            //bigSon = r < heapSize && arr[r] > arr[l] ? r : l;
            bigSon = r < heapSize && comparator.compare(arr.get(r), arr.get(l)) > 0 ? r : l;
            //if (arr[bigSon] <= arr[index]) break;
            if (comparator.compare(arr.get(bigSon), arr.get(index)) <= 0) break;
            swap(index, bigSon);
            index = bigSon;
            l = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        T t1 = arr.get(i);
        T t2 = arr.get(j);
        arr.set(i, t2);
        arr.set(j, t1);
        //元素位置互换后，更新元素位置表
        indexMap.put(t1, j);
        indexMap.put(t2, i);
    }

}
