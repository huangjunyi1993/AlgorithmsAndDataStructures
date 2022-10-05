package _03经典面试题目.预处理数组;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
 * 例如:
 * arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
 * 三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，
 * 累加和都是5
 *
 * Created by huangjunyi on 2022/10/2.
 */
public class _05CutArrToFourParts {

    public static boolean curArr(int[] arr) {
        if (arr == null || arr.length < 7) return false;

        /*
        生成前缀和map
        key：0 ~ i-1 的数组前缀和
        value：下标i
        map表示当前下标域当前下标前一位的数组前缀和的映射
         */
        Map<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }

        int N = arr.length;
        int a = arr[0];
        // 循环枚举第一刀的位置，同时记录第一刀前一位的前缀和a
        for (int i = 1; i <= N - 6; i++) {
            if (map.containsKey(a) && map.get(a) == i) {
                /*
                 检查是否存在第二刀
                 如果存在第二刀
                 map必然存在 2*a + arr[第一刀的位置] 的前缀和
                  */
                int cut2Sum  = 2 * a + arr[i];
                if (map.containsKey(cut2Sum)) {
                    //同样的方法检查是否存在第三刀
                    int cut2Index2 = map.get(cut2Sum);
                    int cut3Sum = 3 * a + arr[i] + arr[cut2Index2];
                    if (map.containsKey(cut3Sum)) {
                        //最后检查第三刀后面的那一部分累加和是否为a
                        int cut3Index = map.get((cut3Sum));
                        if (4 * a + arr[i] + arr[cut2Index2] + arr[cut3Index] == sum) return true;
                    }
                }
            }
            a += arr[i];
        }

        return false;
    }

}
