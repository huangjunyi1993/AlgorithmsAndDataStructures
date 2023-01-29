package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/shuffle-an-array/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/12.
 */
public class _146打乱数组 {
    class Solution {

        private int[] original;
        private int[] shuffle;
        private int N;

        public Solution(int[] nums) {
            N = nums.length;
            original = new int[N];
            shuffle = new int[N];
            for (int i = 0; i < nums.length; i++) {
                original[i] = nums[i];
                shuffle[i] = nums[i];
            }
        }

        public int[] reset() {
            return original;
        }

        public int[] shuffle() {
            // 从后往前遍历，每次取一个随机下标，把它换到尾部，然后返回缩小，继续这样搞
            for (int i = N - 1; i >= 0; i--) {
                int randomIndex = (int) (Math.random() * (i + 1));
                int temp = shuffle[randomIndex];
                shuffle[randomIndex] = shuffle[i];
                shuffle[i] = temp;
            }
            return shuffle;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
}
