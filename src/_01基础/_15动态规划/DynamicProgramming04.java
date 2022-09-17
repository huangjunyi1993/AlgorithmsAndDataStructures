package _01基础._15动态规划;

/**
 * 给定一个整形数组，代表一串纸牌
 * 有A、B两个玩家，从中抽取纸牌，但是每次只能拿最左或者最右
 * 假设两个玩家双方博弈
 * 求最后获胜方的结果值
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming04 {

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

    /**
     * 暴露递归
     * @param values
     * @return
     */
    public static int getWinValue(int[] values) {
        return Math.max(first(values, 0, values.length - 1), second(values, 0, values.length - 1));
    }

    /**
     * 动态规划
     * @param values
     * @return
     */
    public static int getWinValueByDp(int[] values) {
        int n = values.length;
        int[][] first = new int[n][n];
        int[][] second = new int[n][n];

        for (int i = 0; i < n; i++) {
            first[i][i] = values[i];
        }

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = i + 1;
            while (left < n - 1 && right < n) {
                first[left][right] = Math.max(values[left] + second[left + 1][right], values[right] + second[left][right - 1]);
                second[left][right] = Math.min(first[left + 1][right], first[left][right - 1]);
                left++;
                right++;
            }
        }


        return Math.max(first[0][n - 1], second[0][n - 1]);
    }

    public static void main(String[] args) {
        int[] values = {1,2,5,23,8,3,122,8};
        System.out.println(getWinValue(values));
        System.out.println(getWinValueByDp(values));
    }


}
