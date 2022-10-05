package _03经典面试题目.未分类;

/**
 * 寻找第K小的数值对
 *
 * 长度为N的数组arr，一定可以组成N^2个数值对。
 * 例如arr = [3,1,2]，
 * 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
 * 也就是任意两个数都有数值对，而且自己和自己也算数值对。
 * 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：
 * (1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 *
 * <a>https://img-blog.csdnimg.cn/20210622233547700.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3lzaHVvbw==,size_16,color_FFFFFF,t_70</a>
 * Created by huangjunyi on 2022/9/18.
 */
public class _03XunZhaoDiKXiaoDeShuZhiDui {

    public static int[] findkthMinPair(int[] arr, int k) {
        if (arr == null || arr.length == 0) return null;
        /*
        首先对数组进行排序，
        排好序后，第一个数就是数组中的下标为 (k - 1) / n 的数，第一个数记为f
        然后遍历数组，看数组中小于f的有几个数，记为a，看数组等于f的有几个数，记为b
        然后 k - (a * n)，表示去掉第一个数小于f的数组成的所有数值对，剩余的都是第一个数位f的
        然后转化为寻找在这 k - (a * n) 个以f开头的数值对中，哪一个是要返回的数值对
        因为数值在第一个值相同时，根据第二个数进行排序，所以 ((k - a * n) - 1) / b 就是要返回的数值对

        然后可以通过改写快排代替真正的排序，进一步降低时间复杂度
         */
        int n = arr.length;
        if (k > n * n) return null;
        int f = findKthNum(arr, (k - 1) / n);
        int a = 0;
        int b = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < f) a++;
            if (arr[i] == f) b++;
        }
        int p = findKthNum(arr, ((k - a * n) - 1) / b);
        return new int[]{f, p};
    }

    private static int findKthNum(int[] arr, int k) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int pivot = arr[l + (int)(Math.random() * (r - l + 1))];
            int[] range = patition(arr, l, r, pivot);
            if (k < range[0]) r = range[0] - 1;
            else if (k > range[1]) l = range[1] + 1;
            else return pivot;
        }
        return arr[l];
    }

    private static int[] patition(int[] arr, int low, int high, int pivot) {
        int l = low;
        int r = high;
        int cur = low;
        while (cur < r) {
            if (arr[cur] < pivot) swap(arr, cur++, l++);
            else if (arr[cur] > pivot) swap(arr, cur, r--);
            else cur++;
        }
        return new int[] {l ,r};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
