package _01基础._06堆与堆排序;

import java.util.*;

/**
 * 可动态调整的堆：
 * 当堆中的元素的值发生变化时，可以重新调整该元素在堆中的位置
 */
public class Heap05<T> {

    private List<T> arr; // 堆容器
    private Map<T, Integer> indexMap; // 反向索引表
    private int heapSize; // 堆大小
    private Comparator<T> comparator; // 比较器

    public Heap05(Comparator<T> comparator) {
        this.comparator = comparator;
        this.arr = new ArrayList<>();
        indexMap = new HashMap<>();
        this.heapSize = 0;
    }

    public void push(T t) {
        int size = arr.size();
        arr.add(t); // 添加到尾部
        indexMap.put(t, size); // 记录反向索引
        floatUp(size); // 向上调整
        heapSize++;
    }

    public T pop() {
        T res = arr.get(0); // 弹出的元素
        swap(0, heapSize - 1); // 与尾部交换
        arr.remove(heapSize - 1); // 删掉要弹出的元素
        indexMap.remove(res); // 删除反向索引
        sink(0, --heapSize); // 交换到头部的元素，做向下调整
        return res;
    }

    public void remove(T obj) {
        T replace = arr.get(heapSize - 1); // 尾部元素
        Integer index = indexMap.get(obj); // 要删除元素的位置
        indexMap.remove(obj);
        heapSize--;
        if (obj == replace) return; // 如果要删除的元素，就是尾部元素，删除就走
        arr.set(index, replace); // 尾部元素替换到删除位置
        indexMap.put(replace, index); // 更新反向索引
        resign(replace); // 堆调整
    }

    public void resign(T t) {
        //元素的值发生变化，重新调整该元素在堆中的位置
        int index = indexMap.get(t);
        floatUp(index);
        sink(index, heapSize);
    }

    public List<T> getALL() {
        return new ArrayList<>(this.arr);
    }

    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    public int size() {
        return heapSize;
    }

    public T peek() {
        return arr.get(0);
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
        //元素位置互换
        T t1 = arr.get(i);
        T t2 = arr.get(j);
        arr.set(i, t2);
        arr.set(j, t1);
        //元素位置互换后，更新元素位置表
        indexMap.put(t1, j);
        indexMap.put(t2, i);
    }

}
