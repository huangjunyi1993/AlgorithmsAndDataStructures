package _01基础._14暴力递归;

/**
 * 给定一个整形数组，代表一串纸牌
 * 有A、B两个玩家，从中抽取纸牌，但是每次只能拿最左或者最右
 * 假设两个玩家双方博弈
 * 求最后获胜方的结果值
 * Created by huangjunyi on 2022/9/3.
 */
public class Recursive07 {

    /**
     * 先手拿牌
     * @param values
     * @param left
     * @param right
     * @return
     */
    public static int first(int[] values, int left, int right) {
        if (left == right) return values[left];
        return Math.max(values[left] + second(values, left + 1, right), values[right] + second(values, left, right - 1));
    }

    /**
     * 后手拿牌
     * @param values
     * @param left
     * @param right
     * @return
     */
    public static int second(int[] values, int left, int right) {
        if (left == right) return 0;
        return Math.min(first(values, left + 1, right), first(values, left, right - 1));
    }

    public static int getWinValue(int[] values) {
        return Math.max(first(values, 0, values.length - 1), second(values, 0, values.length - 1));
    }

}
