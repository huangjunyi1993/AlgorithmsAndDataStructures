package _03经典面试题目.动态规划;

/**
 * 给定一个全是正数的数组arr，定义一下arr的最小不可组成和的概念：
 * 1，arr的所有非空子集中，把每个子集内的所有元素加起来会出现很多的值，其中最小的记为min，最大的记为max；
 * 2，在区间[min,max]上，如果有一些正数不可以被arr某一个子集相加得到，那么这些正数中最小的那个，就是arr的最小不可组成和；
 * 3，在区间[min,max]上，如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最小不可组成和；
 * 举例： arr = {3,2,5}
 * arr的min为2，max为10，
 * 在区间[2,10]上，4是不能被任何一个子集相加得到的值中最小的，所以4是arr的最小不可组成和；
 * arr = {3,2,4}
 * arr的min为2，max为9，
 * 在区间[2,9]上，8是不能被任何一个子集相加得到的值中最小的，所以8是arr的最小不可组成和；
 * arr = {3,1,2} arr的min为1，max为6，
 * 在区间[2,6]上，任何数都可以被某一个子集相加得到，所以7是arr的最小不可组成和；
 * 请写函数返回arr的最小不可组成和。
 *
 * Created by huangjunyi on 2022/10/3.
 */
public class _017MinUnformedSum {

    public static int getMinUnFormedSum(int[] arr) {
        // 求 min和max
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max += arr[i];
        }

        /*
        创建dp表
        并初始化第一行和第一列
        dp[i][j]表示arr上0~i的1数，能否累加出j
         */
        boolean[][] dp = new boolean[arr.length][max + 1];
        dp[0][arr[0]] = true;
        for (int i = 0; i < arr.length; i++) dp[i][0] = true;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= max; j++) {
                /*
                1、如果arr上0~i-1的数能累加出j，那么0~i也可以，只要把i上的数舍弃
                2、如果arr上0~i-1的数，能累加出j-arr[i]
                这两种情况，dp[i][j]都是true
                 */
                dp[i][j] = dp[i - 1][j] | (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }

        // 最后一行，从min列开始，到max列，看有没有为false的，有的化，第一个false的列值就是最小不可累加和
        for (int i = min; i <= max; i++) {
            if (!dp[arr.length - 1][i]) return i;
        }
        return max + 1;
    }

}
