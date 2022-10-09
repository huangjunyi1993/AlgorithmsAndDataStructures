package _03经典面试题目.堆;

import java.util.PriorityQueue;

/**
 * 给定一个数组arr，代表一堆咖啡机，数组中每个数代表咖啡机泡好一杯咖啡需要的时间
 * 再给定一个整数M，代表有M给人，M个人从0时间点同时到咖啡机去排队泡咖啡
 * 返回一个长度为M的数组，表示每个人泡好咖啡的最优时间
 *
 * Created by huangjunyi on 2022/10/6.
 */
public class _05CoffeeProblem {

    private static class CoffeeMachine {
        private int startTime;
        private int workTime;

        public CoffeeMachine(int startTime, int workTime) {
            this.startTime = startTime;
            this.workTime = workTime;
        }
    }

    public static int[] bestChoices(int[] arr, int M) {
        int[] res = new int[M];
        PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(((o1, o2) -> o1.startTime + o1.workTime - o2.startTime - o2.workTime));
        for (int i = 0; i < arr.length; i++) {
            heap.add(new CoffeeMachine(0, arr[i]));
        }
        for (int i = 0; i < M; i++) {
            CoffeeMachine coffeeMachine = heap.poll();
            res[i] = coffeeMachine.startTime + coffeeMachine.workTime;
            coffeeMachine.startTime = res[i];
            heap.add(coffeeMachine);
        }
        return res;
    }

}
