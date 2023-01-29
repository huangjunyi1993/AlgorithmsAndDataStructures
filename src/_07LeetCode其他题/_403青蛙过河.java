package _07LeetCode其他题;

import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.cn/problems/frog-jump/
 * Created by huangjunyi on 2023/1/21.
 */
public class _403青蛙过河 {
    class Solution {
        public boolean canCross(int[] stones) {
            HashSet<Integer> can = new HashSet<>();
            for (int stone : stones) can.add(stone);
            HashMap<Integer, HashMap<Integer, Boolean>> dp = new HashMap<>();
            return process(1, 1, stones[stones.length - 1], dp, can);
        }

        /**
         * 当前到达的位置是cur，前面跳跃距离是pre，终点在end，
         * 后续可以选择往后跳pre-1、pre、pre+1长度
         * 返回是否能到达终点
         * @param cur 当前位置
         * @param pre 前一步的跳跃长度
         * @param end 终点
         * @param dp 记忆缓存
         * @param can 能到的位置（石头的位置）
         * @return
         */
        private boolean process(int cur, int pre, int end,
                                HashMap<Integer, HashMap<Integer, Boolean>> dp,
                                HashSet<Integer> can) {
            if (cur == end) return true;
            if (!can.contains(cur)) return false;
            if (dp.containsKey(cur) && dp.get(cur).containsKey(pre)) return dp.get(cur).get(pre);
            boolean res = (pre > 1 && process(cur + pre - 1, pre - 1, end, dp, can))
                    || process(cur + pre, pre, end, dp, can)
                    || process(cur + pre + 1, pre + 1, end, dp, can);
            if (!dp.containsKey(cur)) dp.put(cur, new HashMap<>());
            dp.get(cur).put(pre, res);
            return res;
        }
    }
}
