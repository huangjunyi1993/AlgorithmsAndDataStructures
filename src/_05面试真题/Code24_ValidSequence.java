package _05面试真题;

/**
 * 来自腾讯
 * 给定一个长度为n的数组arr，求有多少个子数组满足 :
 * 子数组两端的值，是这个子数组的最小值和次小值，最小值和次小值谁在最左和最右无所谓
 * n<=100000（10^5） n*logn  O(N)
 * Created by huangjunyi on 2023/1/19.
 */
public class Code24_ValidSequence {

    public static int nums(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int n = arr.length;
        int[] stack = new int[n];
        int[] times = new int[n];
        int size = 0;
        int res = 0;

        // 从左往右搞一遍，搞出所有左边次小，右边最小的情况
        // 单调栈从小到到大，
        // 遇到小压大，弹出，结算
        for (int i = 0; i < n; i++) {
            while (size != 0 && stack[size - 1] > arr[i]) {
                int val = stack[size];
                int time = times[--size];
                // 当前要压入栈中的数，和从栈中弹出的数，可以构成达标子数组time个
                // 然后从栈中弹出的数，两两配对，可以扣除CN2个达标子数组
                res += time + ((time * (time - 1)) >> 1);
            }
            if (size != 0 && stack[size - 1] == arr[i]) {
                times[size - 1]++;
            } else {
                stack[size] = arr[i];
                times[size] = 1;
                size++;
            }
        }

        // 栈中剩下一些元素，单独结算每条记录自己两两配对的情况
        while (size != 0) {
            int time = times[--size];
            res += ((time * (time - 1)) >> 1);
        }

        // 从右往左右搞一遍，搞出所有左边最小，右边次小的情况
        for (int i = n - 1; i >= 0; i--) {
            while (size != 0 && stack[size - 1] > arr[i]) {
                int val = stack[size];
                int time = times[--size];
                // 当前要压入栈中的数，和从栈中弹出的数，可以构成达标子数组time个
                // 每条记录内部两两配对的情况已经在上面结算过，现在不在结算了
                res += time;
            }
            if (size != 0 && stack[size - 1] == arr[i]) {
                times[size - 1]++;
            } else {
                stack[size] = arr[i];
                times[size] = 1;
                size++;
            }
        }

        return res;
    }

    public static int cn2(int n) {
        return (n * (n - 1)) >> 1;
    }

    // 为了测试
    // 暴力方法
    public static int test(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int s = 0; s < arr.length; s++) {
            for (int e = s + 1; e < arr.length; e++) {
                int max = Math.max(arr[s], arr[e]);
                boolean valid = true;
                for (int i = s + 1; i < e; i++) {
                    if (arr[i] < max) {
                        valid = false;
                        break;
                    }
                }
                ans += valid ? 1 : 0;
            }
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int n = 30;
        int v = 30;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * n);
            int[] arr = randomArray(m, v);
            int ans1 = nums(arr);
            int ans2 = test(arr);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");
    }

}
