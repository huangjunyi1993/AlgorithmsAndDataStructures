package _01基础._05归并排序;

/**
 * 求数组中的逆序对个数
 */
public class MergeSort04 {

    public static int sort(int[] arr) {
        return process(arr, 0, arr.length-1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0; // 一个数，天然有序，返回

        int mid = l + ((r - l) >> 1); //取中点

        return process(arr, l, mid) + //递归切分
        process(arr, mid + 1, r) + //递归切分
        merge(arr, l, mid, r); //此时左右两边都局部有序，进行合并
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];

        int i = 0;
        int a = l;
        int b = mid + 1;
        int res = 0;

        while (a <= mid && b <= r) {
            //右边的数比左边的数小，则左边数组从下标a开始，到下标mid，这么多个数都可以与arr[b]组成逆序对
            if (arr[a] > arr[b]) {
                res += (mid - a + 1);
            }
            help[i++] = arr[a] <= arr[b] ? arr[a++] : arr[b++];
        }

        //如果右边的数组放完，左边有剩余，把左边的全部排到后面
        while (a <= mid) help[i++] = arr[a++];

        //如果左边的数组放完，右边有剩余，把右边的全部排到后面
        while (b <= r) help[i++] = arr[b++];

        //按照help数组中的顺序，把排好序的元素放回到原数组arr中
        for (i = 0; i < help.length; i++) arr[l + i] = help[i];

        return res;
    }

}
