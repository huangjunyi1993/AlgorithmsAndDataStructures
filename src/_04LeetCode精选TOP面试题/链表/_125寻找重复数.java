package _04LeetCode精选TOP面试题.链表;

/**
 * https://leetcode.cn/problems/find-the-duplicate-number/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/5.
 */
public class _125寻找重复数 {
    class Solution {
        public int findDuplicate(int[] nums) {
            // 在有环链表中返回入环结点，入环结点就是重复数
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while (fast != slow) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }
    }
}
