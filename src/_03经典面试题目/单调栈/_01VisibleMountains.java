package _03经典面试题目.单调栈;

import java.util.LinkedList;

/**
 * 一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度。
 * 比如， {3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山。
 *
 * 山峰A和山峰B能够相互看见的条件为:
 * 1.如果A和B是同一座山，认为不能相互看见。
 * 2.如果A和B是不同的山，并且在环中相邻，认为可以相互看见。
 * 3.如果A和B是不同的山，并且在环中不相邻，假设两座山高度的最小值为min。
 *
 * 1)如果A通过顺时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互 看见
 * 2)如果A通过逆时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互 看见
 * 3)两个方向只要有一个能看见，就算A和B可以相互看见 给定一个不含有负数且没有重复值的数组 arr，请返回有多少对山峰能够相互看见。
 * 进阶: 给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见。
 *
 * Created by huangjunyi on 2022/10/8.
 */
public class _01VisibleMountains {

    private static class Recode {
        int value;
        int times;

        public Recode(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int N = arr.length;

        /*
        使用单调栈求解
        保证栈中元素从栈底到栈底单调递减

        栈中元素用Record对象表示，value表示高度，times表示个数

        先遍历数组，拿到最高的山峰的下标
        先把它封装为Record压入栈中

        然后遍历数组（一个方向即可）
        遇到待压入元素的高度高于栈顶元素，则弹出栈顶元素结算，直到不低于待压入元素的高度

        结算方法是 times * 2 + c(2, times)
        times * 2表示每个山峰左右两边都有一个比它高的山（栈中下面一个，待压入的）
        2 * times 表示山峰间两两可见

        所有山峰压入完毕后
        结算栈中元素
        非栈第两元素，结算方式一样

        栈底倒数第二个元素
        如果栈顶元素times > 1，结算方式也一样
        如果栈顶元素times == 1，代表最高山峰只有一座，那么 times * 1 + c(2, times)
        表示向左向右看，都是同一座山

        栈底元素c(2, times)，两两可见
         */

        // 选出最高山峰的下标
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }

        LinkedList<Recode> stack = new LinkedList<>();
        stack.addFirst(new Recode(arr[maxIndex]));

        // 遍历每一座山峰，尝试压入栈中，遇到栈顶比当前山峰矮的，弹出结算
        int index = nextIndex(maxIndex, N);
        int res = 0;
        while (index != maxIndex) {
            while (stack.peekFirst().value < arr[index]) {
                int times = stack.pollFirst().times;
                res += 2 * times + getInternalSum(times);
            }
            if (stack.peekFirst().value == arr[index]) {
                stack.peekFirst().times += 1;
            } else {
                stack.addFirst(new Recode(arr[index]));
            }
            index = nextIndex(index, N);
        }

        // 结算栈中元素，指定剩下两个
        while (stack.size() > 2) {
            int times = stack.pollFirst().times;
            res += 2 * times + getInternalSum(times);
        }

        // 结算栈底倒数第二个元素
        if (stack.size() == 2) {
            int times = stack.pollFirst().times;
            res += (stack.peekFirst().times > 1 ? 2 * times : times) + getInternalSum(times);
        }

        // 结算栈底元素
        res += getInternalSum(stack.pollFirst().times);
        return res;
    }

    private static int getInternalSum(int times) {
        return times == 1 ? 0 : (times * (times - 1) / 2);
    }

    private static int nextIndex(int index, int size) {
        return index < size - 1 ? index + 1 : 0;
    }

}
