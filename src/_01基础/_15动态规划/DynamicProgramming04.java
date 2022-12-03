package _01基础._15动态规划;

/**
 * 给定一个整形数组，代表一串纸牌
 * 有A、B两个玩家，从中抽取纸牌，但是每次只能拿最左或者最右
 * 假设两个玩家绝顶聪明（博弈）
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
        // 只剩下一张牌，先手拿走
        if (left == right) return values[left];
        // 因为先手决定聪明，会拿走最大的分数
        return Math.max(
                // 拿走最左侧牌，然后先手就转为后手了，调后手函数，获得剩下的分数
                values[left] + second(values, left + 1, right),
                // 拿走最右侧牌，然后先手就转为后手了，调后手函数，获得剩下的分数
                values[right] + second(values, left, right - 1)
        );
    }

    /**
     * 后手拿牌
     * @param values
     * @param left
     * @param right
     * @return
     */
    public static int second(int[] values, int left, int right) {
        // 只剩下一张牌，会被先手拿走，后手拿不到
        if (left == right) return 0;
        // 因为先手决定聪明，会让后手拿到最小的分数
        return Math.min(
                // 最左侧牌被先手拿走，后手转为先手，在left+1~right上调先手函数做决定
                first(values, left + 1, right),
                // 最右侧牌被先手拿走，后手转为先手，在left~right-1上调先手函数做决定
                first(values, left, right - 1)
        );
    }

    /**
     * 暴力递归
     * @param values
     * @return
     */
    public static int getWinValue(int[] values) {
        return Math.max(
                // 先手玩家获得的分数
                first(values, 0, values.length - 1),
                // 后手玩家获得的分数
                second(values, 0, values.length - 1)
        );
    }

    /**
     * 先手拿牌
     * @param values
     * @param left
     * @param right
     * @param firstMap
     *@param secondMap @return
     */
    public static int firstByCacheMap(int[] values, int left, int right, int[][] firstMap, int[][] secondMap) {
        // 如果缓存中有值，直接返回缓存中的值
        if (firstMap[left][right] != -1) return firstMap[left][right];
        int res = 0;
        // 只剩下一张牌，先手拿走
        if (left == right) res = values[left];
        // 因为先手决定聪明，会拿走最大的分数
        else res = Math.max(
                // 拿走最左侧牌，然后先手就转为后手了，调后手函数，获得剩下的分数
                values[left] + secondByCacheMap(values, left + 1, right, firstMap, secondMap),
                // 拿走最右侧牌，然后先手就转为后手了，调后手函数，获得剩下的分数
                values[right] + secondByCacheMap(values, left, right - 1, firstMap, secondMap)
        );
        // 记录缓存表
        firstMap[left][right] = res;
        return res;
    }

    /**
     * 后手拿牌
     * @param values
     * @param left
     * @param right
     * @param firstMap
     *@param secondMap @return
     */
    public static int secondByCacheMap(int[] values, int left, int right, int[][] firstMap, int[][] secondMap) {
        // 如果缓存中有值，直接返回缓存中的值
        if (secondMap[left][right] != -1) return secondMap[left][right];
        int res = 0;
        // 只剩下一张牌，会被先手拿走，后手拿不到
        if (left == right) res = 0;
        // 因为先手决定聪明，会让后手拿到最小的分数
        else res = Math.min(
                // 最左侧牌被先手拿走，后手转为先手，在left+1~right上调先手函数做决定
                firstByCacheMap(values, left + 1, right, firstMap, secondMap),
                // 最右侧牌被先手拿走，后手转为先手，在left~right-1上调先手函数做决定
                firstByCacheMap(values, left, right - 1, firstMap, secondMap)
        );
        // 记录缓存表
        secondMap[left][right] = res;
        return res;
    }

    /**
     * 暴力递归改成记忆化搜索
     * @param values
     * @return
     */
    public static int getWinValueByCacheMap(int[] values) {
        // 因为暴力递归中，有两个函数，先后，后手，所以搞两个缓存表
        int[][] firstMap = new int[values.length][values.length];
        int[][] secondMap = new int[values.length][values.length];
        // 初始化缓存表
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                firstMap[i][j] = -1;
                secondMap[i][j] = -1;
            }
        }
        return Math.max(
                // 先手玩家获得的分数
                firstByCacheMap(values, 0, values.length - 1, firstMap, secondMap),
                // 后手玩家获得的分数
                secondByCacheMap(values, 0, values.length - 1, firstMap, secondMap)
        );
    }

    /**
     * 动态规划
     * @param values
     * @return
     */
    public static int getWinValueByDp(int[] values) {
        int n = values.length;
        // 因为暴力递归中，有两个函数，先后，后手，所以搞两个dp表
        int[][] first = new int[n][n];
        int[][] second = new int[n][n];

        // 根据暴力递归先手函数的base case（if (left == right) return values[left];），初始化first
        for (int i = 0; i < n; i++) {
            first[i][i] = values[i];
        }

        /*
        根据暴力递归推断dp表的依赖关系
        first[i][j]依赖second[i+1][j]和second[i][j-1]
        second[i][j]依赖first[i+1][j]和first[i][j-1]
        所以依着对角线从上往下填，从左往右填完所有对角线就ok
        因为left <= right，所以，左下半部分不需要填

        因为0对角线已经初始化了，从1对角线开始
         */
        for (int i = 1; i < n; i++) {
            int left = 0; // 行
            int right = i; // 列
            while (left < n - 1 && right < n) {
                first[left][right] = Math.max(values[left] + second[left + 1][right], values[right] + second[left][right - 1]);
                second[left][right] = Math.min(first[left + 1][right], first[left][right - 1]);
                // 依着对角线填，所以行++，列++
                left++;
                right++;
            }
        }


        return Math.max(first[0][n - 1], second[0][n - 1]);
    }

    public static void main(String[] args) {
        int[] values = {1,2,5,23,8,3,122,8};
        System.out.println(getWinValue(values));
        System.out.println(getWinValueByCacheMap(values));
        System.out.println(getWinValueByDp(values));
    }


}
