package _03经典面试题目.贪心;

/**
 * 给定一个有序的正数数组 arr 和一个正数 aim ，如果可以自由选择 arr 中的数字，想累加得到 1~aim 范围上所有的数，返回 arr 最少还缺几个数。
 *
 * 例如:
 * arr = {1,2,3,7}，aim = 15
 * 想累加得到1~15范围上所有的数，arr 还缺 14 这个数，所以返回 1。
 *
 * arr = {1,5,7}，aim = 15
 * 想累加得到1~15范围上所有的数，arr 还缺 2 和 4，所以返回 2。
 *
 * Created by huangjunyi on 2022/10/3.
 */
public class _01MinPatches {

    public static int minPatches(int[] arr, int aim) {
        /*
        如何才能最大化的利用arr[i]？
        就是当range前面的累加和等到达arr[i] - 1
        所以如果前面的累加和到不了arr[i] - 1
        就要补充数
         */
        int patches = 0;
        int range = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > range + 1) {
                range += range + 1;
                patches++;
                if (range >= aim) return patches;
            }
            range += arr[i];
            if (range >= aim) return patches;
        }
        while (aim >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }

}
