package _01基础._01二分;

/**
 * 二分查找小于等于某个数的最右侧位置
 * 如：数组 000111222333，num 5
 * 则返回的下标为3
 */
public class BinarySearch03 {

    public static int search(int[] arr, int num) {
        if (arr == null || arr.length == 0) return -1;
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > num) r = mid - 1; //mid以及mid右侧都是大于num的数，都排除
            else {
                index = mid; // 目前已知的小于等于num的最右侧位置
                l = mid + 1; // 排除左边的数
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{0,0,1,1,1,3,3,3,3,3}, 2));
    }

}
