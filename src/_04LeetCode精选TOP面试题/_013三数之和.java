package _04LeetCode精选TOP面试题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _013三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] < -nums[i]) {
                    l++;
                } else if (nums[l] + nums[r] > -nums[i]) {
                    r--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    do {
                        l++;
                    } while (nums[l] == nums[l - 1]);
                    do {
                        r--;
                    } while (nums[r] == nums[r + 1]);
                }
            }
        }
        return res;
    }
}
