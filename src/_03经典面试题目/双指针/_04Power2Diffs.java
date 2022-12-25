package _03经典面试题目.双指针;


/**
 * 给定一个有序数组arr，其中值可能为正、负、0。 返回arr中每个数都平方之后不同的结果有多少种？
 * Created by huangjunyi on 2022/12/25.
 */
public class _04Power2Diffs {

    public static int diff(int[] arr) {
        int N = arr.length;
        // 左右指针
        int left = 0;
        int right = N - 1;
        int count = 0;
        // 每一轮，count++，
        // 然后哪个指针的绝对值大，哪个指针滑动，并且滑到与原来的值的绝对值不相等的位置
        // 如果两个指针的绝对值一样，两个指针都滑动
        while (left <= right) {
            count++;
            int leftAbs = Math.abs(arr[left]);
            int rightAbs = Math.abs(arr[right]);
            if (leftAbs > rightAbs) {
                while (left < N && leftAbs == Math.abs(arr[left])) left++;
            } else if (rightAbs > leftAbs) {
                while (right >= 0 && rightAbs == Math.abs(arr[right])) right--;
            } else {
                while (left < N && leftAbs == Math.abs(arr[left])) left++;
                while (right >= 0 && rightAbs == Math.abs(arr[right])) right--;
            }
        }
        return count;
    }

}
