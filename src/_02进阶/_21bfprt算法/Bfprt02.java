package _02进阶._21bfprt算法;

/**
 * 给定一个整形数组，返回其中第K小的数，bfprt算法求解
 *
 * bfprt算法的作用在于，随便取一个数作为分组的数，就可以通过表达式估算（按比例）出是否可以收敛到我们想要的时间复杂度
 * 例如以5位分组：
 * 1、组内排序，因为只有5个数，所以O(1)，总体O(n/5)，相当于O(n)
 * 2、中位数数组递归调用bfprt算法取中位数，这个是记为T(n/5)
 * 3、挑选出基准值后，在整个数组上进行荷兰国旗的划分，O(n)
 * 4、如果第k小的数（下标k-1）没有在划分后的range内，左右两侧选一侧进行递归，假设进的是左侧，那么排除掉中间区域和右侧区域的，剩余的就可以得出时间复杂度：
 *      因为基准值是中位数组中的中位数，所以在中位数数组中大于等于基准值的数的个数，是总数组大小的n/10（n/5/2）
 *      然后因为中位数组每个数都是5个数一组中的中位数，所以至少还有两被n/10的数比基准值大(大于等于基准值的数，在自己的分组内，都还有后面两个数也比基准值大)
 *      所以至少可以排除3n/10
 *      所以这里的时间复杂度记为T(7n/10)
 * 最后总的时间复杂度为O(n) + T(n/5) + T(7n/10) = O(n)
 * Created by huangjunyi on 2022/9/9.
 */
public class Bfprt02 {

    public static int getMinKth(int[] arr, int k) {
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int l, int r, int index) {
        if (l == r) return arr[l];
        //按照bfprt的策略挑选一个基准值
        int pivot = getPivot(arr, l, r);
        //利用荷兰国旗问题进行分区，然后range为等于pivot的区间
        int[] range = partition(arr, l, r, pivot);
        //如果index位于range中间，直接返回arr[index]
        if (range[0] >= index && range[1] <= index) return arr[index];
        //否则递归调用bfprt算法进行处理
        else if (range[1] < index) return bfprt(arr, range[1] + 1, r, index);
        else return bfprt(arr, l, arr[0] - 1, index);
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int low = l - 1;
        int high = r + 1;
        int i = l;
        while (i < high) {
            if (arr[i] < pivot) swap(arr, i++, ++low);
            else if (arr[i] > pivot) swap(arr, i, --high);
            else i++;
        }
        return new int[] {low + 1, high - 1};
    }

    /**
     * 根据bfprt算法，获取基准值
     * 把数组以5个一组分组，组内进行排序，然后把每组排序后的数翻入一个新数组中，最后递归调用bfprt算法返回该数组的中位数
     * @param arr 原数组
     * @param l 左下标
     * @param r 右下标
     * @return
     */
    private static int getPivot(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int low = l + i * 5;
            mArr[i] = getMedian(arr, low, Math.min(r, low + 4));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    /**
     * 对数组进行插入排序后取其中位数
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int getMedian(int[] arr, int l, int r) {
        insertSort(arr, l, r);
        return arr[(l + r) / 2];
    }

    private static void insertSort(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= l; j--) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
