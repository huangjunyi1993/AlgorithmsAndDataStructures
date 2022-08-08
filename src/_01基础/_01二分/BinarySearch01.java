package _01基础._01二分;

/**
 * 普通二分查找
 */
public class BinarySearch01 {

    public static int search(int[] arr, int num) {
        if (arr == null || arr.length == 0) return -1;
        int l = 0;
        int r = arr.length - 1;
        int mid;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid] > num) r = mid - 1;
            else if (arr[mid] < num) l = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{1,2,4,6,7,9}, 4));
    }

}
