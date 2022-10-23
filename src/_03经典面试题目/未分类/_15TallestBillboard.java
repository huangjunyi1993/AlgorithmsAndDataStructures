package _03经典面试题目.未分类;

import java.util.HashMap;

/**
 * 给定一个数组arr，如果其中两个集合的累加和相等，并且两个集合使用的数没有相容的部分（也就是arr中某数不能同时进这两个集合），
 * 那么这两个集合加做等累加和集合对。返回等累加和集合对中，最大的累加和。
 * 举例：
 * arr={1,2,3,6} {1,2}和{3}是等累加集合对，{1,2,3}和{6}，也是等累加集合对，返回6
 * Created by huangjunyi on 2022/10/16.
 */
public class _15TallestBillboard {

    public int tallestBillboard(int[] rods) {

        /*
        利用一个hash表，记录：key为集合对间的差值，value为集合对间累加和较小的集合的累计和
        初始是翻入 0 -> 0，表示差值为0的集合对，较小的集合的累计和为0

        然后进行双层循环遍历
        外层遍历rods中的数
        内存遍历hash表中的差值对
        每次更新出两个新的差值对，一个是把数放入累计和较大的集合形成的差值对，一个是把数放入累加和较小的集合形成的差值对

        最后返回hash表中差值为0的value
         */
        HashMap<Integer, Integer> diffMap = new HashMap<>();
        diffMap.put(0, 0);
        HashMap<Integer, Integer> curDiffMap;
        for (int num : rods) {
            if (num != 0) {
                curDiffMap = new HashMap<>(diffMap);
                for (Integer diff : curDiffMap.keySet()) {
                    Integer smallSum = curDiffMap.get(diff);
                    Integer otherSmallSum = curDiffMap.getOrDefault(diff + num, 0);
                    diffMap.put(diff + num, Math.max(smallSum, otherSmallSum));

                    otherSmallSum = curDiffMap.getOrDefault(Math.abs(num - diff), 0);
                    if (diff >= num) {
                        diffMap.put(diff - num, Math.max(smallSum + num, otherSmallSum));
                    } else {
                        diffMap.put(num - diff, Math.max(diff + smallSum, otherSmallSum));
                    }
                }
            }
        }
        return diffMap.get(0);
    }

}
