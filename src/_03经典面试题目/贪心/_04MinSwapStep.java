package _03经典面试题目.贪心;

/**
 * 一个数组中只有两种字符'G'和'B'，
 * 可以让所有的G都放在左侧，所有的B都放在右侧
 * 或者可以让所有的G都放在右侧，所有的B都放在左侧
 * 但是只能在相邻字符之间进行交换操作，请问请问至少需要交换几次，
 * Created by huangjunyi on 2022/12/18.
 */

public class _04MinSwapStep {
    public static int minSteps(String s) {
        if (s == null || s.length() == 0) return 0;
        /*
        贪心：
        假设让G走左边最优，
        那么右边的G，没有理由跑到左边的G前面去
        假设让B走左边最优，
        那么右边的B，没有理由跑到左边的B前面去

        因此，让G走左，让B走左，各求一答案，PK一下
         */
        int res1 = 0; // 方案1，G走左
        int res2 = 0; // 方案2，B走左
        int g = 0; // 方案1，已经挪到左边的G数量
        int b = 0; // 方案2，已经挪到左边的B数量
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == 'G') {
                res1 += i - g++;
            } else {
                res2 += i - b++;
            }
        }
        return Math.min(res1, res2);
    }

}
