package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/majority-element/
 * Created by huangjunyi on 2022/11/5.
 */
public class _089多数元素 {
    class Solution {
        public int majorityElement(int[] nums) {
            int cand = nums[0];
            int HP = 1;
            for (int i = 1; i < nums.length; i++) {
                if (HP == 0) {
                    cand = nums[i];
                    HP++;
                }
                // 当前数和cand不同，HP--，等于一次删掉两个不同的数
                else if (cand != nums[i]) {
                    HP--;
                } else {
                    HP++;
                }
            }
            return cand;
        }
    }
}
