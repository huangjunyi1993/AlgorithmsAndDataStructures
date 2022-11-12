package _04LeetCode精选TOP面试题;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/contains-duplicate/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _106存在重复元素 {
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if (set.contains(num)) return true;
                set.add(num);
            }
            return false;
        }
    }
}
