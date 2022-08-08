package _01基础._05归并排序;

import java.util.Arrays;

/**
 * 荷兰国旗问题：
 * 给定一个数组，并且给定一个基准值，数组中小于该值的数放左边，大于该值的数放右边，等于该值的数放中间
 * 然后返回中间等于该值的区域的左右边界
 */
public class QuickSort01 {

    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};

        if (l == r) return new int[]{l, r};

        int low = l - 1; //小于基准值的区域的右边界
        int high = r; //大于基准值的区域的左边界，以arr[r]作为基准值，先把他包裹在右区域内，最后再处理
        int i = l;

        while (i < high) {
            //小于基准值，把arr[i]与小于区域的右一个数交换，小于区域往右括一位，i跳到下一位
            if (arr[i] < arr[r]) swap(arr, i++, ++low);
            //大于基准值，把arr[i]与大于区域左边一个数交换，大于区域往左括一位，i不动，因为新换过来的这个数还没看过
            else if (arr[i] > arr[r]) swap(arr, i, --high);
            //等于基准值，i跳下一位
            else i++;
        }

        //处理arr[r]，与大于区域的左边界的数交换，相当于等于区域往右括一位
        swap(arr, high, r);

        return new int[]{low + 1, high};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
