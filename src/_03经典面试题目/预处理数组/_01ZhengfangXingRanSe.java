package _03经典面试题目.预处理数组;

/**
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将 会被覆盖。
 * 目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。
 * 如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 * Created by huangjunyi on 2022/9/17.
 */
public class _01ZhengfangXingRanSe {

    public static int minPaint(String str) {
        if (str == null || str.length() < 2) return 0;
        char[] chars = str.toCharArray();
        /*
        枚举分界线，对比每个分界线下左边G的数量和右边R的数量，看那个分界线最小，就是答案
        可以用两个预处理数组left和right求解，left记录左边G的数量，right记录右边R的数量
        因为对比分界线的遍历是从左往右的，所以可以用整形left省掉一个预处理数组
        又因为可以前统计R的总数量，记录为right，然后没到一个位置，如果是R，则right--，所以又省掉一个预处理数组
         */
        int right = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R') right++;
        }
        int res = right;
        int left = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == 'G') left++;
            if (chars[i] == 'R') right--;
            res = Math.min(res, left + right);
        }
        res = Math.min(res, left + (chars[chars.length - 1] == 'G' ? 1 : 0));
        return res;
    }

}
