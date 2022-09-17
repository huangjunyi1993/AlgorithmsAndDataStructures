package _02进阶._27数组累加和问题;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组，返回累加和等于k的所有子数组中的最大长度子数组的长度
 * Created by huangjunyi on 2022/9/11.
 */
public class ArrSum02 {

    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(0, -1);
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int diff = sum - k;
            if (sumIndexMap.containsKey(diff)) {
                len = Math.max(len, i - sumIndexMap.get(diff));
            }
            if (sumIndexMap.containsKey(sum)) continue;
            sumIndexMap.put(sum, i);
        }
        return len;
    }

}
