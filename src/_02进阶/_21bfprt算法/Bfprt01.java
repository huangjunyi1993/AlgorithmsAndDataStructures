package _02进阶._21bfprt算法;

/**
 * 给定一个整形数组，返回其中第K小的数
 * Created by huangjunyi on 2022/9/6.
 */
public class Bfprt01 {

    /**
     * 递归版本
     */
    public static int getMinKth01(int[] arr, int k) {
        // 找第K小的数，就是找数组中排序后下标诶k-1的数
        return process(arr, 0, arr.length - 1, k - 1);
    }

    private static int process(int[] arr, int l, int r, int i) {
        // base case
        if (l == r) return arr[l];
        // 对数组指定区域做partition，返回等于区域的左右边界
        int[] range = partition(arr, l, r);
        // 如果目标位置i在等于区域内部，则返回arr[i]，就是第K小的数
        if (i >= range[0] && i <= range[1]) return arr[i];
            // 目标位置在等于区域左边，对左边做partition
        else if (i <range[0]) return process(arr, l, range[0] - 1, i);
            // 目标位置在等于区域右边，对右边做partition
        else return process(arr, range[1] + 1, r, i);
    }

    /**
     * 迭代版本
     */
    public static int getMinKth02(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        int i = k - 1;
        while (l < r) {
            // 对数组指定区域做partition，返回等于区域的左右边界
            int[] range = partition(arr, l, r);
            // 如果目标位置i在等于区域内部，则返回arr[i]，就是第K小的数
            if (i >= range[0] && i <= range[1]) return arr[i];
                // 目标位置在等于区域左边，对左边做partition
            else if (i <range[0]) r = range[0] - 1;
                // 目标位置在等于区域右边，对右边做partition
            else l = range[1] + 1;
        }
        return arr[l];
    }

    private static int[] partition(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int low = l - 1;
        int high = r;
        int i = l;
        while (i < high) {
            if (arr[i] < arr[r]) swap(arr, i++, ++low);
            else if (arr[i] > arr[r]) swap(arr, i, --high);
            else i++;
        }
        swap(arr, high, r);
        return new int[]{low + 1, high};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
