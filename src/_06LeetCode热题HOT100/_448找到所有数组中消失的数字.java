package _06LeetCode热题HOT100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _448找到所有数组中消失的数字 {
    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> res = new ArrayList<>();
            // 遍历 下标循环怼
            for (int i = 0; i < nums.length; i++) {
                process(nums, i);
            }
            // 收答案
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) res.add(i + 1);
            }
            return res;
        }
        private void process(int[] nums, int index) {
            // 以index作为发货点，不断发货，直到index对了，或者发不出去了（对面已经有一样的），就退出了
            while (nums[index] != index + 1) {
                int nextIndex = nums[index] - 1;
                if (nums[nextIndex] == nextIndex + 1) break;
                swap(nums, index, nextIndex);
            }
        }

        private void swap(int[] nums, int index, int nextIndex) {
            int temp = nums[index];
            nums[index] = nums[nextIndex];
            nums[nextIndex] = temp;
        }
    }
}
