package _01基础._06堆与堆排序;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * 5、购买数最大的前K名用户进入得奖区，在最初时入股得奖区没有到达K个用户，那么新进来的用户直接进入得奖区
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
    private Heap05<Customer> candidateHeap; // 候选者堆 大根堆 按购买数降序排，购买数相同按时间升序排
    private Heap05<Customer> winnerHeap; // 得奖者堆 小跟堆 按购买数升序排，购买数相同按时间降序排
    private int K; // 指定得奖的用户数

    public List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        this.K = k;
        customerMap = new HashMap<>();
        candidateHeap = new Heap05<>(((o1, o2) -> o1.buy != o2.buy ?  o2.buy - o1.buy : o1.time - o2.time));
        winnerHeap = new Heap05<>(((o1, o2) -> o1.buy != o2.buy ? o1.buy - o2.buy : o1.time - o2.time));

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

}
