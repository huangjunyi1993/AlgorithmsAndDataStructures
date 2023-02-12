package _01基础._06堆与堆排序;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个整形数组，int[] arr; 和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr = [3,3,1,2,1,2,5...
 * op  = [T,T,T,T,F,T,F...
 * 依次表示：3用户购买了一件商品，3用户购买了1件商品，1用户购买了1件商品，2用户购买了1件商品
 * 1用户退货了1件商品，2用户购买了1件商品，5用户退货了1件商品
 * 一对arr[i]和op就代表一个事件：
 * 用户号位arr[i]，op[i] == T就代表这个用户购买了一件商品
 * op[i] == F就代表这个用户退货了一件商品
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候，
 * 都给购买次数最多的前K名用户颁奖。
 * 所以每个事件发生后，你都需要一个得奖名单（得奖区）
 * 得奖系统的规则：
 * 1、如果某个用户购买商品数位0，但是有发生了退货事件，则任务该事件无效，得奖名单和之前事件时一直，比如例子中的5用户
 * 2、某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3、每次都是最多K个用户得奖，K也是作为参数传入，如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 * 4、得奖系统分为得奖区和候选区，任何用户只要购买数>0，一定在这两个区域中的一个
 * 5、购买数最大的前K名用户进入得奖区，在最初时如果得奖区没有到达K个用户，那么新进来的用户直接进入得奖区
 * 6、如果购买数不足以进入得奖区的用户，进入候选区
 * 7、如果候选区购买最多的用户，已经足以进入得奖区，该用户就会替换得奖区中购买数最少的用户（大于才能替换），如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户，如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * 8、候选区和得奖区是两套时间，因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有从得奖区出来进入候选区的用户，得奖区时间删除，进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i），从候选区出来进入得奖区的用户，候选区时间删除，进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 9、如果某个用户购买数==0，不管在哪个区域都离开，区域时间删除，离开是指彻底离开，哪个区域都不会找到该用户，如果下次该用户又发生购买行为，产生>)的购买数，会再次根据之前规则回到某个区域中，进入区域的时间重记
 * 请遍历arr数组和op数组，遍历每一步输出一个得奖名单
 * public List<List<Integer>> topK(int[] arr, boolean[] op, int k)
 * Created by huangjunyi on 2022/11/20.
 */
public class Heap06 {

    private Map<Integer, Customer> customerMap; // id和用户的索引表
    private Heap<Customer> candidateHeap; // 候选者堆 大根堆 按购买数降序排，购买数相同按时间升序排
    private Heap<Customer> winnerHeap; // 得奖者堆 小跟堆 按购买数升序排，购买数相同按时间降序排
    private int K; // 指定得奖的用户数

    public List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        this.K = k;
        customerMap = new HashMap<>();
        candidateHeap = new Heap<>(((o1, o2) -> o1.buy != o2.buy ?  o2.buy - o1.buy : o1.time - o2.time));
        winnerHeap = new Heap<>(((o1, o2) -> o1.buy != o2.buy ? o1.buy - o2.buy : o1.time - o2.time));

        // 遍历所有事件 => 执行 => 获取得奖者名单
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buy = op[i];
            int time = i;
            operate(id, buy, time);
            res.add(getWinners());
        }
        return res;
    }

    /**
     * 获取当前得奖者名单（id列表）
     * @return
     */
    private List<Integer> getWinners() {
        // 直接取得奖者堆中所有元素的id
        List<Customer> customers = winnerHeap.getALL();
        return customers.stream().map(customer -> customer.id).collect(Collectors.toList());
    }

    /**
     * 事件处理
     * @param id 用户id
     * @param buy 是否购买，购买 true，退货 false
     * @param time 事件发生时间
     */
    private void operate(int id, boolean buy, int time) {
        // 规则1 如果某个用户购买商品数位0，但是有发生了退货事件，则任务该事件无效
        if (!buy && !customerMap.containsKey(id)) return;

        // 不存在该用户，先初始化
        if (!customerMap.containsKey(id)) customerMap.put(id, new Customer(id, 0, 0));

        // 更新用户购买数
        Customer customer = customerMap.get(id);
        if (buy) customer.buy++; else customer.buy--;

        // 如果用户购买数位0，删除
        if (customer.buy == 0) customerMap.remove(id);

        // 更新候选者堆和得奖者堆
        if (!candidateHeap.contains(customer) && !winnerHeap.contains(customer)) {
            // 候选者堆和得奖者堆都没有该用户，代表是新用户
            if (winnerHeap.size() < K) {
                // 得奖者堆没满，直接进
                customer.time = time;
                winnerHeap.push(customer);
            } else {
                // 得奖者堆满了，先进入候选者堆
                customer.time = time;
                candidateHeap.push(customer);
            }
        } else if (candidateHeap.contains(customer)) {
            // 候选者堆包含该用户，如果购买数为0，删掉，否者堆调整
            if (customer.buy == 0) {
                candidateHeap.remove(customer);
            } else {
                candidateHeap.resign(customer);
            }
        } else {
            // 得奖者堆包含该用户，如果购买数为0，删掉，否者堆调整
            if (customer.buy == 0) {
                winnerHeap.remove(customer);
            } else {
                winnerHeap.resign(customer);
            }
        }
        move(time);
    }

    private void move(int time) {
        // 候选者堆空，返回
        if (candidateHeap.size() == 0) return;

        // 候选者堆不空，但是得奖者堆没满，那是前面删掉了一个得奖者
        if (winnerHeap.size() < K) {
            Customer pop = candidateHeap.pop();
            pop.time = time;
            winnerHeap.push(pop);
        }

        // 候选者堆不空，得奖者堆满了，如果候选者堆顶PK过了得奖者堆顶，交换
        else {
            if (candidateHeap.peek().buy > winnerHeap.peek().buy) {
                Customer oldCandidate = candidateHeap.pop();
                Customer oldWinner = winnerHeap.pop();
                oldCandidate.time = time;
                oldWinner.time = time;
                winnerHeap.push(oldCandidate);
                candidateHeap.push(oldWinner);
            }
        }
    }

    private class Customer {
        int id;
        int buy;
        int time;

        public Customer(int id, int buy, int time) {
            this.id = id;
            this.buy = buy;
            this.time = time;
        }
    }

    private class Heap<T> {

        private List<T> arr; // 堆容器
        private Map<T, Integer> indexMap; // 反向索引表
        private int heapSize; // 堆大小
        private Comparator<T> comparator; // 比较器

        public Heap(Comparator<T> comparator) {
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
            while (comparator.compare(arr.get(index), arr.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void sink(int index, int heapSize) {
            //堆调整：下沉
            int l = index * 2 + 1;
            int r;
            int smallSon;
            //还有左孩子，就继续比较，没有左孩子，就不再比较了
            while (l < heapSize) {
                r = index * 2 + 2;
                smallSon = r < heapSize && comparator.compare(arr.get(r), arr.get(l)) < 0 ? r : l;
                if (comparator.compare(arr.get(index), arr.get(smallSon)) <= 0) break;
                swap(index, smallSon);
                index = smallSon;
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

}
