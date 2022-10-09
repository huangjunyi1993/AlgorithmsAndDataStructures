package _03经典面试题目.位运算;

/**
 * Nim博弈问题
 * 给定一个非负数组，每一个值代表该位置上有几个铜板。
 * a和b玩游戏，a先手，b后手， 轮到某个人的时候，只能在一个位置上拿任意数量的铜板，但是不能不拿。
 * 谁最先把铜 板拿完谁赢。假设a和b都极度聪明，请返回获胜者的名字
 * Created by huangjunyi on 2022/10/7.
 */
public class _01NimProblem {

    public static String nim(int[] arr) {
        /*
        把所有的数异或起来
        异或和不为0，先手赢，否则后手赢

        因为双方绝顶聪明
        所以只要异或和不为0
        先手就可以每次通过拿走一些硬币，使得异或和为0
        然后后手无论怎么拿，异或和都不为0
        最后最先面对无硬币可拿的一定是后手
         */
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor != 0 ? "a" : "b";
    }

}
