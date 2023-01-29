package _04LeetCode精选TOP面试题.位运算;

/**
 * https://leetcode.cn/problems/single-number/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/1.
 */
public class _074只出现一次的数字 {
    public int singleNumber(int[] nums) {
        int eor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            eor ^= nums[i];
        }
        return eor;
    }
}
