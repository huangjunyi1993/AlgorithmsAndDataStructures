package _04LeetCode精选TOP面试题.贪心;

/**
 * https://leetcode.cn/problems/jump-game/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/28.
 */
public class _038跳跃游戏 {
    public boolean canJump(int[] nums) {
        int max = nums[0];
        /*
        遍历数组，看每一个位置能拱多远
        更新max
        发现i位置大于max，表示i位置是无法到达的，返回false
         */
        for (int i = 0; i < nums.length; i++) {
            if (max >= nums.length - 1) return true;
            if (i > max) return false;
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }
}
