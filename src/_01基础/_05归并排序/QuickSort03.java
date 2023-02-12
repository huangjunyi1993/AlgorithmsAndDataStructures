package _01基础._05归并排序;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 快速排序非递归版本
 * Created by huangjunyi on 2022/11/19.
 */
public class QuickSort03 {

    private static class Op {

        int l; // 排序范围左边界
        int r; // 排序范围右边界

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        /*
        用一个栈，去模拟系统底层的递归处理过程
         */
        LinkedList<Op> stack = new LinkedList<>();
        stack.push(new Op(0, N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l >= op.r) continue;
            swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
            int[] range = partition(arr, op.l, op.r);
            stack.push(new Op(op.l, range[0] - 1));
            stack.push(new Op(range[1] + 1, op.r));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] partition(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int less = l - 1;
        int more = r;
        int cur = l;
        while (cur != more) {
            if (arr[cur] < arr[r]) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > arr[r]) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        swap(arr, more, r);
        return new int[] {less + 1, more};
    }

    public static void main(String[] args) {
        int[] arr1 = {9,4,884,1,19,19,1,84,8,45,1,1,519,181,9};
        int[] arr2 = {9,4,884,1,19,19,1,84,8,45,1,1,519,181,9};
        sort(arr1);
        System.out.println(Arrays.toString(arr1));
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

}
