package _06LeetCode热题HOT100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum/description/
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _039组合总和 {
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            return process(candidates, target, 0);
        }

        /**
         * 从index号货币开始开始，凑出target元钱，有几种方法
         * @param candidates
         * @param target
         * @param index
         * @return
         */
        private List<List<Integer>> process(int[] candidates, int target, int index) {
            List<List<Integer>> res = new ArrayList<>();
            if (target == 0) {
                // 如果target为0，返回一个空容器，上层递归就可以往里头塞值
                res.add(new ArrayList<>());
                return res;
            }
            // 如果target不为0，而index越界了，没有答案
            if (index == candidates.length) return res;
            // index号货币取0张...1张...2张....
            for (int zhang = 0; zhang * candidates[index] <= target; zhang++) {
                // 递归，取出index+1开始往后试的答案
                List<List<Integer>> subRes = process(candidates, target - zhang * candidates[index], index + 1);
                // 遍历里面每一个答案，往里面塞zhang个货币
                for (List<Integer> subRe : subRes) {
                    for (int i = 0; i < zhang; i++) {
                        subRe.add(candidates[index]);
                    }
                    // copy当递归的答案里面
                    res.add(subRe);
                }
            }
            return res;
        }
    }
}
