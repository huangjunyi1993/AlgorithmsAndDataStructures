package _01基础._11贪心算法;

import java.util.PriorityQueue;

/**
 * 金条切分
 * 给定一块金条，切分它需要花费和金条长度值一样的铜板个数
 * 给定一个数组，代表每个人期望分得的金条
 * 如何切分才能使得铜板花费个数最小，
 * 最后返回铜板个数
 */
public class Greed04 {

    public static int process(int[] arr) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
        }

        int sum = 0; // 代价
        int cur = 0;

        while (q.size() > 1) {
            cur = q.poll() + q.poll();
            sum += cur;
            q.add(cur);
        }
        return sum;
    }

}
