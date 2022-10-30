package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _047子集 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        // 递归，每个位置要和不要，都来一遍，index越界，则表示所有位置都以决定好，收集一个答案
        process(nums, res, path, 0);
        return res;
    }

    private void process(int[] nums, List<List<Integer>> res, LinkedList<Integer> path, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        process(nums, res, path, index + 1);
        path.addLast(nums[index]);
        process(nums, res, path, index + 1);
        path.removeLast();
    }
}
