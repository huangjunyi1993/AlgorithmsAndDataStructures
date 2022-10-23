package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/container-with-most-water/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _009盛最多水的容器 {
    public int maxArea(int[] height) {
        /*
        左右指针，谁小结算谁
         */
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
