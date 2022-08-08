package _01基础._01二分;

/**
 * 寻找局部最小
 * arr数组无序，并且相邻两个数不相等，从中找出一个局部最小的数，并返回下标
 * 局部最小：
 * 比如下标为i的数，如果小于小标i-1和i+1的数，则下标为i的数就是局部最小
 * 如果下标为0的数，小于下标为1的数，则下标为0的数就是局部最小
 * 如果下标为arr.length-1的数，小于arr.length-2的数，则arr.length-1的数就是局部最小
 */
public class BinarySearch04 {

    public static int search(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return 0;

        //arr[0] < arr[1]，所以arr[0]满足局部最小，返回0
        if (arr[0] < arr[1]) return 0;

        //arr[arr.length - 1] < arr[arr.length - 2]，所以arr[arr.length - 1]满足局部最小，返回arr.length - 1
        if (arr[arr.length - 1] < arr[arr.length - 2]) return arr.length - 1;

        //此时l~r范围内，arr[l] > arr[l+1] 趋势递减，arr[arr.length-2] < arr[arr.length-1]趋势递增
        //中间必有最小值
        int l = 1;
        int r = arr.length - 2;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            //找到局部最小，返回
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) return mid;
            //如果arr[mid] > arr[mid - 1]，则arr[mid-1] < arr[mid]，趋势递增，l~mid中间必有最小值
            //r赋值为mid-1
            else if (arr[mid] > arr[mid - 1]) r = mid - 1;
            //arr[mid]小于arr[mid-1]，只能排除左边的一部分，从右边的一部分寻找局部最小
            else l = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{3,2,4,6,7,9}));
    }

}
