package _07LeetCode其他题;

/**
 * https://leetcode.cn/problems/subarrays-with-k-different-integers/
 * Created by huangjunyi on 2023/1/20.
 */
public class _992K个不同整数的子数组 {
    class Solution {
        public int subarraysWithKDistinct(int[] nums, int k) {
            int n = nums.length;
            // k-1窗口的词频统计表
            int[] lessCountMap = new int[n + 1];
            // k窗口的词频统计表
            int[] equalsCountMap = new int[n + 1];
            // 小窗口词种数
            int lessKinds = 0;
            // 大窗口词种数
            int equalsKinds = 0;
            // 小窗口左边界
            int lessLeft = 0;
            // 大窗口左边界
            int equalsLeft = 0;
            // 答案
            int res = 0;
            // right是两个窗口的左边界，每次走一步
            // 然后维持大小窗口
            // 每一步两窗口的差值
            // 就是结算答案
            for (int right = 0; right < n; right++) {
                if (lessCountMap[nums[right]] == 0) lessKinds++;
                if (equalsCountMap[nums[right]] == 0) equalsKinds++;
                lessCountMap[nums[right]]++;
                equalsCountMap[nums[right]]++;
                while (lessKinds == k) {
                    if (lessCountMap[nums[lessLeft]] == 1) lessKinds--;
                    lessCountMap[nums[lessLeft]]--;
                    lessLeft++;
                }
                while (equalsKinds > k) {
                    if (equalsCountMap[nums[equalsLeft]] == 1) equalsKinds--;
                    equalsCountMap[nums[equalsLeft]]--;
                    equalsLeft++;
                }
                if (lessLeft > equalsLeft) res += (lessLeft - equalsLeft);
            }
            return res;
        }
    }
}
