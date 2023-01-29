package _04LeetCode精选TOP面试题.贪心;

/**
 * https://leetcode.cn/problems/jump-game-ii/
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _038跳跃游戏II {
    public int jump(int[] nums) {
        int step = 0; // 当前步数
        int cur = 0; // 当前步数走的最远距离
        int next = nums[0]; // 下一步走的最远距离
        for (int i = 1; i < nums.length; i++) {
            if (i > cur) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return step;
    }
}
