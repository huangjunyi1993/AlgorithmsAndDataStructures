package _03经典面试题目.未分类;

import java.util.HashMap;

/**
 * 给定无序数组arr，返回其中最长的连续序列的长度
 *
 * 【举例】
 * arr=[100, 4, 200, 1, 3, 2]，最长的连续序列为[1, 2, 3, 4]，所以返回4
 *
 * Created by huangjunyi on 2022/10/16.
 */
public class _17LongestConsecutive {

    public static int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        /*
        用一个map，记录每一个数，对应连续区间的长度

        每遍历到一个数num
        先检查map中是否存在相同的数，存在则跳过

        然后记录 num => 1 到map

        然后检查是否存在前一个数的记录 num - 1 对应的range
        存在则合并

        然后检查是否存在后一个数的记录 num + 1 对应的range
        存在则合并

        遍历过程中记录最大range的长度max
         */

        HashMap<Integer, Integer> rangeMap = new HashMap<>();
        int max = 1;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (rangeMap.containsKey(num)) continue;
            rangeMap.put(num, 1);
            if (rangeMap.containsKey(num - 1)) {
                max = Math.max(max, merge(rangeMap, num - 1, num));
            }
            if (rangeMap.containsKey(num + 1)) {
                max = Math.max(max, merge(rangeMap, num, num + 1));
            }
        }
        return max;
    }

    private static int merge(HashMap<Integer, Integer> rangeMap,
                             int preNum,
                             int postNum) {
        /*
        合并过程
        只需更新开头好结尾的range，其他的不需要管
        因为其他的都是脏数据
        map会自动过滤
        后续也用不到
         */

        Integer preRange = rangeMap.get(preNum);
        Integer postRange = rangeMap.get(postNum);
        int start = preNum - preRange + 1;
        int end = postNum + postRange - 1;
        int range = end - start + 1;
        rangeMap.put(preNum, range);
        rangeMap.put(postNum, range);
        return range;
    }

}
