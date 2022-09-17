package _02进阶._21bfprt算法;

/**
 * 给定一个整形数组，返回其中第K小的数
 * Created by huangjunyi on 2022/9/6.
 */
public class Bfprt01 {

    public static int getMinKth(int[] arr, int k) {
        return process(arr, 0, arr.length - 1, k - 1);
    }

    private static int process(int[] arr, int l, int r, int i) {
        if (l == r) return arr[l];
        int[] range = partition(arr, l, r);
        if (i >= range[0] && i <= range[1]) return arr[i];
        else if (i <range[0]) return process(arr, l, range[0] - 1, i);
        else return process(arr, range[1] + 1, r, i);
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
