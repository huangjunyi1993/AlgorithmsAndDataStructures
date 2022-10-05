package _03经典面试题目.动态规划;

/**
 * 给出一组正整数arr，你从第0个数向最后一个数，每个数的值表示你从这个位置可以向右跳跃的最大长度,计算如何以最少的跳跃次数跳到最后一个数。
 *
 * Created by huangjunyi on 2022/10/2.
 */
public class _014MinStepNum {

    public static int minStep(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int step = 0; // 当前步数
        int curR = 0; // 当前步数能到的最右边界
        int next = -1; // 当前步数再跳下一步，能到的最右边界
        for (int i = 0; i < arr.length; i++) {
            // 如果已经超出当前步数能到的右边界，则增加步数，并更新能到的右边界
            if (curR < i) {
                step++;
                curR = next;
            }
            // 尝试更新当前步再跳下一步，能到的右边界
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

}
