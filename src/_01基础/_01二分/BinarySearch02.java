package _01基础._01二分;

/**
 * 二分查找大于等于某个数的最左侧位置
 * 如：数组 000111222333，num 1
 * 则返回的下标为3
 */
public class BinarySearch02 {

    public static int search(int[] arr, int num) {
        if (arr == null || arr.length == 0) return -1;
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= num) {
                index = mid; // arr[mid]是目前已知的大于等于num的最左侧位置
                r = mid - 1; // 排查mid右边的数
            } else l = mid + 1; // mid以及mid左边的数都比num小，排查mid右边的数
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{0,0,1,1,1,2,2,2,2,3,3,3,3,3}, 1));
    }

}
