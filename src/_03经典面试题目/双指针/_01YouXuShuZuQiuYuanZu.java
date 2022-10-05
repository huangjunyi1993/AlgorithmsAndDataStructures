package _03经典面试题目.双指针;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个有序数组arr，给定一个正数aim
 * 1）返回累加和为aim的，所有不同二元组
 * 2）返回累加和为aim的，所有不同三元组
 * Created by huangjunyi on 2022/9/18.
 */
public class _01YouXuShuZuQiuYuanZu {

    /**
     * 问题1
     * @param arr
     * @param aim
     * @return
     */
    public static int[][] uniquePair1(int[] arr, int aim) {
        if (arr == null || arr.length < 2) return null;
        int l = 0;
        int r = arr.length - 1;
        List<int[]> list = new ArrayList<>();
        /*
        利用左右指针求解
        如果arr[l] + arr[r] < aim，则l指针往右移一位
        如果arr[l] + arr[r] > aim，则r指针往左移一位
        如果arr[l] + arr[r] == aim，看l的前一位的数是否跟l对应的数相等，不相等则记录结果，相等则l++
         */
        while (l < r) {
            if (arr[l] + arr[r] < aim) l++;
            else if (arr[l] + arr[r] > aim) r--;
            else {
                if (l > 0 && arr[l] == arr[l - 1]) {
                    l++;
                    continue;
                }
                list.add(new int[]{arr[l], arr[r]});
                l++;
            }
        }
        int[][] res = list.toArray(new int[list.size()][]);
        return res;
    }

    /**
     * 问题2
     * @param arr
     * @param aim
     * @return
     */
    public static int[][] uniquePair2(int[] arr, int aim) {
        if (arr == null || arr.length < 3) return null;
        Set<Integer> visited = new HashSet<>();
        List<int[]> list = new ArrayList<>();
        /*
        遍历数组，然后aim - arr[i]得出target
        简化为问题一一样的双指针求法
        当arr[l] + arr[r] == target是记录结果
        记录的结果是(arr[i], arr[l], arr[r])

        但是要记录遍历过的数组的值，当遇到相同的值时就跳过，防止出现重复的元组
         */
        for (int i = 0; i < arr.length - 2; i++) {
            if (visited.contains(arr[i])) continue;
            int target = aim - arr[i];
            int l = i + 1;
            int r = arr.length - 1;
            while (l < r) {
                if (arr[l] + arr[r] < target) l++;
                else if (arr[l] + arr[r] > target) r--;
                else {
                    if (l > i + 1 && arr[l] == arr[l - 1]) {
                        l++;
                        continue;
                    }
                    list.add(new int[]{arr[i], arr[l], arr[r]});
                    l++;
                }
            }
            visited.add(arr[i]);
        }
        int[][] res = list.toArray(new int[list.size()][]);
        return res;
    }

}
