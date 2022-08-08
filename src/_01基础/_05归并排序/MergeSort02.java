package _01基础._05归并排序;

import java.util.Arrays;

/**
 * 遍历实现归并排序
 */
public class MergeSort02 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        //步长从2开始，2->4->8->......
        int len = 2;

        //步长达到数组长度*2就返回
        while (len < arr.length * 2) {

            int l = 0;

            //按步长进行迭代递归
            while (l < arr.length) {
                int mid = l + ((len >> 1) - 1);
                if (mid >= arr.length) break;
                int r = Math.min(l + len - 1, arr.length -1);
                merge(arr, l, mid, r);
                l = r + 1;
            }

            //防止int类型溢出
            if (len > arr.length) {
                break;
            }

            len <<= 1;
        }
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];

        int i = 0;
        int a = l;
        int b = mid + 1;

        //不断的比较左右两边的数，谁小谁排前面，放入临时数组help
        while (a <= mid && b <= r) help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];

        //如果右边的数组放完，左边有剩余，把左边的全部排到后面
        while (a <= mid) help[i++] = arr[a++];

        //如果左边的数组放完，右边有剩余，把右边的全部排到后面
        while (b <= r) help[i++] = arr[b++];

        //按照help数组中的顺序，把排好序的元素放回到原数组arr中
        for (i = 0; i < help.length; i++) arr[l + i] = help[i];

    }

    public static void main(String[] args) {
        int[] arr = {3,5,7,26,7,3,2,45,8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
