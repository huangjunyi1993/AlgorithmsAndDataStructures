package _03经典面试题目.动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/tallest-billboard/
 *
 * 你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。
 * 你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6 的支架。
 * 返回 广告牌的最大可能安装高度 。如果没法安装广告牌，请返回 0 。
 *
 * Created by huangjunyi on 2023/1/8.
 */
public class _049TallestBillboard {
    class Solution {
        public int tallestBillboard(int[] rods) {
            // 新老表：key差值（两根杆高度差），value基线（较矮一边的高度）
            Map<Integer, Integer> oldDp;
            Map<Integer, Integer> curDp = new HashMap<>();
            // 差值为0的，高度为0
            curDp.put(0, 0);
            for (int rod : rods) {
                if (rod <= 0) continue;
                // 新表复制给老表
                oldDp = curDp;
                // 新表重新建，根据老表推新表
                curDp = new HashMap<>();
                // 遍历老表的记录
                for (Map.Entry<Integer, Integer> entry : oldDp.entrySet()) {
                    Integer oldDiff = entry.getKey(); // 老差值
                    Integer base = entry.getValue(); // 老基线
                    Integer other = base + oldDiff; // 老的较高一边的高度

                    // 当前新杆子焊接到老的较矮一边，更新一个记录到新表
                    int diff1 = Math.abs(base + rod - other);
                    int base1 = Math.min(base + rod, other);
                    if (curDp.containsKey(diff1)) {
                        // 如果新表已经有相同差值的话，要PK一下
                        curDp.put(diff1, Math.max(base1, curDp.get(diff1)));
                    } else {
                        curDp.put(diff1, base1);
                    }

                    // 当前新杆子焊接到老的较高一边，更新一个记录到新表
                    int diff2 = Math.abs(other + rod - base);
                    int base2 = Math.min(other + rod, base);
                    if (curDp.containsKey(diff2)) {
                        // 如果新表已经有相同差值的话，要PK一下
                        curDp.put(diff2, Math.max(base2, curDp.get(diff2)));
                    } else {
                        curDp.put(diff2, base2);
                    }

                }
                // 再次遍历老表记录，把老表的记录原封不动，也复制到新表，如果存在相同差值，要PK一下
                for (Map.Entry<Integer, Integer> entry : oldDp.entrySet()) {
                    Integer diff = entry.getKey();
                    Integer base = entry.getValue();
                    if (curDp.containsKey(diff)) {
                        curDp.put(diff, Math.max(base, curDp.get(diff)));
                    } else {
                        curDp.put(diff, base);
                    }
                }
            }

            // 最后取出新表差值为0的，就是答案
            return curDp.get(0);
        }
    }
}
