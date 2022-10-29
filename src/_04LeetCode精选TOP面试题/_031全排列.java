package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _031全排列 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        process(nums, res, 0);
        return res;
    }

    private void process(int[] nums, List<List<Integer>> res, int index) {
        if (index == nums.length) {
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                path.add(nums[i]);;
            }
            res.add(path);
            return;
        }
        /*
        当前位置与包括自己在内的往后所有位置进行交换，然后往下继续尝试
         */
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            process(nums, res, index + 1);;
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
