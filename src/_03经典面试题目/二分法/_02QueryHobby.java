package _03经典面试题目.二分法;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)。意思是在数组里下标0~3这个范围上，有几个2？返回2。
 * 假设给你一个数组arr，对这个数组的查询非常频繁，请返回所有查询的结果
 *
 * Created by huangjunyi on 2022/12/24.
 */
public class _02QueryHobby {

    public static class QueryBox {

        // 例如 5 => [1,3,5] 5这个数，1、3、5下标都有
        private HashMap<Integer, ArrayList<Integer>> map;

        public QueryBox(int[] arr) {
            map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).add(i);
            }
        }

        public int query(int L, int R, int value) {
            if (!map.containsKey(value)) return 0;
            ArrayList<Integer> list = map.get(value);
            // 二分计算小于L的几个
            int a = countLess(list, L);
            // 二分计算小于R+1的几个
            int b = countLess(list, R + 1);
            return b - a;
        }

        private int countLess(ArrayList<Integer> list, int limit) {
            // 二分查找小于limit的最右侧下标
            int N = list.size();
            int l = 0;
            int r = N - 1;
            int pre = -1;
            while (l <= r) {
                int m = l + ((r - l ) >> 1);
                if (list.get(m) < limit) {
                    pre = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            // 下标加1，就是个数
            return pre + 1;
        }


    }

}
