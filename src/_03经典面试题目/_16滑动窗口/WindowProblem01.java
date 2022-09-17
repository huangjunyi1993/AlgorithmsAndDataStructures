package _03经典面试题目._16滑动窗口;

/**
 * 给定一个有序数组，从左往右依次表示X轴上从左往右点的位置
 * 给定一个正整数k，返回如果有一根长度为k的绳子，最多能盖住几个点
 * 绳子的边缘点碰到X轴上的点，也算盖住
 * Created by huangjunyi on 2022/9/17.
 */
public class WindowProblem01 {

    public static int maxPoint(int[] arr, int k) {
        /*
        利用窗口求解，
        l指针每次走一步，走到数组结尾就停
        r指针在每次循环中尝试往右括，括到 arr[r] - arr[l] > k，就停
        然后尝试用 r - l 更新max变量
         */
        int l = 0;
        int r = 0;
        int max = Integer.MIN_VALUE;
        while (l < arr.length) {
            while (r < arr.length && arr[r] - arr[l] <= k) r++;
            max = Math.max(max, r - l);
            l++;
        }
        return max;
    }

}
