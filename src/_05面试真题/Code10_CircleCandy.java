package _05面试真题;

import java.util.Arrays;

/**
 * 来自网易
 * 给定一个正数数组arr，表示每个小朋友的得分
 * 任何两个相邻的小朋友，如果得分一样，怎么分糖果无所谓，但如果得分不一样，分数大的一定要比分数少的多拿一些糖果
 * 假设所有的小朋友坐成一个环形，返回在不破坏上一条规则的情况下，需要的最少糖果数
 * Created by huangjunyi on 2023/1/18.
 */
public class Code10_CircleCandy {

    public static int minCandy(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int n = arr.length;

        /*
        改写普通分糖果问题
        因为是环形，所以0下标和length-1有可能互相影响
        所以求left和right数组时，不再是从0下标和length-1下标开始
        而是改为从价值洼地minIndex开始，然后回到价值洼地停
         */

        // 挑出价值洼地
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= arr[nextIndex(i, n)] && arr[i] <= arr[lastIndex(i, n)]) minIndex = i;
        }

        // 不再是从0下标开始从左往右，而是从价值洼地开始，因为有价值洼地卡住（不受两边影响），就不需要考虑环形
        int[] left2Right = new int[n];
        left2Right[minIndex] = 1;
        for (int i = nextIndex(minIndex, n); i != minIndex; i = nextIndex(i, n)) {
            left2Right[i] = arr[i] > arr[lastIndex(i, n)] ? left2Right[lastIndex(i, n)] + 1 : 1;
        }

        // 不再是从length-1下标开始从右往左，而是从价值洼地开始，因为有价值洼地卡住（不受两边影响），就不需要考虑环形
        int[] right2Left = new int[n];
        right2Left[minIndex] = 1;
        for (int i = lastIndex(minIndex, n); i != minIndex; i = lastIndex(i, n)) {
            right2Left[i] = arr[i] > arr[nextIndex(i, n)] ? right2Left[nextIndex(i, n)] + 1 : 1;
        }

        // 还是每个位置求max，累加
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.max(left2Right[i], right2Left[i]);
        }
        return res;
    }

    public static int nextIndex(int i, int n) {
        return i == n - 1 ? 0 : (i + 1);
    }

    public static int lastIndex(int i, int n) {
        return i == 0 ? (n - 1) : (i - 1);
    }

    public static int minCandyTest(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        int n = arr.length;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= arr[lastIndex(i, n)] && arr[i] <= arr[nextIndex(i, n)]) {
                minIndex = i;
                break;
            }
        }
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++, minIndex = nextIndex(minIndex, n)) {
            nums[i] = arr[minIndex];
        }
        int[] left = new int[n + 1];
        left[0] = 1;
        for (int i = 1; i <= n; i++) {
            left[i] = nums[i] > nums[i - 1] ? (left[i - 1] + 1) : 1;
        }
        int[] right = new int[n + 1];
        right[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            right[i] = nums[i] > nums[i + 1] ? (right[i + 1] + 1) : 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 4, 2, 3, 2 };
        System.out.println(minCandy(arr));
        System.out.println(minCandyTest(arr));
    }

}
