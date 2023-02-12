package _01基础._05归并排序;


/**
 * 随机快排
 */
public class QuickSort02 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) return;
        //先对数组做分区处理，得出三个区域，[小于基准值区域|等于基准值区域|大于基准值区域]
        int[] partition = partition(arr, l, r);
        //递归处理左区域
        process(arr, l, partition[0] - 1);
        //递归处理右区域
        process(arr, partition[1] + 1, r);
    }

    private static int[] partition(int[] arr, int l, int r) {
        //在指定区域内，随机选取一个数，作为基准值
        swap(arr, l + (int)(Math.random() * (r - l + 1)), r);

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

        //返回等于基准值区域的左右边界
        return new int[]{low + 1, high};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
