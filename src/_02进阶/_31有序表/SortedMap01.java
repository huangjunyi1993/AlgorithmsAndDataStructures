package _02进阶._31有序表;

import java.util.TreeSet;

/**
 * 需要改写有序表的题目一
 * 给定一个数组arr，和两个整数a和b（a <= b)，
 * 求该数组中有多少个子数组，累加和在[a, b]返回上，
 * 返回达标的子数组数量
 * Created by huangjunyi on 2022/9/17.
 */
public class SortedMap01 {

    private static class SizeBalancedTreeSet<E extends Comparable<E>> {
        private SizeBalancedTree01<E, Object> map;
        private static final Object PRESENT = new Object();

        public SizeBalancedTreeSet() {
            this.map = new SizeBalancedTree01<>();
        }

        public void add(E element) {
            this.map.put(element, PRESENT);
        }

        public int countLess(E element) {
            return this.map.countLessKey(element);
        }
    }

    public static int countRangeSum(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) return 0;
        int sum = 0;
        int res = 0;
        /*
        通过改写有序表实现，有序表中每个节点加一个all属性，表示以当前节点为头节点的子树中，节点的个数，如果添加了相同的值，all回加1
        而size则表示以当前节点为头节点的子树中，不同值的节点的个数
         */
        SizeBalancedTreeSet<Integer> set = new SizeBalancedTreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //求sb树中小于sum - a + 1的节点个数
            int count1 = set.countLess(sum - a + 1);
            //求sb树中小于sum - b的节点个数
            int count2 = set.countLess(sum - b);
            /*
            (count1 - count2)就是从0~i-1，数组累加和在[sum-b, sum-1]之间的个数
            也就是以arr[i]结尾，累加和在[a, b]范围内的子数组个数
             */
            res += (count1 - count2);
            set.add(sum);
        }
        return res;
    }

}
