package _01基础._05归并排序;


/**
 * 求数组最小和：
 * 一个数组中，一个数左边比它小的数之和，称为小和，数组中所有数的小和加起来，称为数组小和
 */
public class MergeSort03 {

    public static int sort(int[] arr) {
        return process(arr, 0, arr.length-1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0; // 一个数，天然有序，返回

        int mid = l + ((r - l) >> 1); //取中点

        return process(arr, l, mid) + //递归切分;
        process(arr, mid + 1, r) + //递归切分
        merge(arr, l, mid, r); //此时左右两边都局部有序，进行合并
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];

        int i = 0;
        int a = l;
        int b = mid + 1;
        int res = 0;

        //数组最小和的求解，转化为：求一个数右边有多少个数比它大，然后就将值(当前数 * 右边比它大的数的个数)累加到res中
        while (a <= mid && b <= r) {
            //如果左边数，比又变小，则从下标b开始，直到下标r，这么多个数都比arr[a]大
            if (arr[a] < arr[b]) {
                res += arr[a] * (r - b + 1);
            }
            //只有左边比右边小，才拷贝左边，否则都拷贝右边
            help[i++] = arr[a] < arr[b] ? arr[a++] : arr[b++];
        }

        //如果右边的数组放完，左边有剩余，把左边的全部排到后面
        while (a <= mid) help[i++] = arr[a++];

        //如果左边的数组放完，右边有剩余，把右边的全部排到后面
        while (b <= r) help[i++] = arr[b++];

        //按照help数组中的顺序，把排好序的元素放回到原数组arr中
        for (i = 0; i < help.length; i++) arr[l + i] = help[i];

        return res;

    }

    public static void main(String[] args) {
        System.out.println(sort(new int[]{1,3,4,2,5}));
    }

}
