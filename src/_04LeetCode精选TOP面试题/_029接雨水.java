package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/trapping-rain-water/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/27.
 */
public class _029接雨水 {
    public int trap(int[] height) {
        int N = height.length;
        if (N < 3) return 0;
        /*
        转换思维：
        当前这一个格子什么时候可以结算水量？

        正常做法：
        两个数组leftMax[]和rightMax[]
        leftMax[i]表示从0~i的最大高度
        rightMax[i]表示从i~N-1的最大高度

        那么结算i位置的水量就是：
        Math.max(0, Math.max(leftMax[i-1], rightMax[i+1]) - height[i])

        但是可以优化，无需生成数组，优化成两个变量leftMax和rightMax，表示当前已知的左右两侧的最大高度
        两个指针l,r分表从左往右和从右往左遍历，
        leftMax和rightMax那一边小，结算那一边，因为小的那边的最大高度会成为当前位置水量的瓶颈

         */
        int l = 1;
        int r = N - 2;
        int leftMax = height[0];
        int rightMax = height[N - 1];
        int res = 0;
        while (l <= r) {
            if (l != r && leftMax == rightMax) {
                res += Math.max(0, leftMax - height[l]);
                res += Math.max(0, rightMax - height[r]);
                leftMax = Math.max(leftMax, height[l]);
                rightMax = Math.max(rightMax, height[r]);
                l++;
                r--;
            } else if (leftMax < rightMax) {
                res += Math.max(0, leftMax - height[l]);
                leftMax = Math.max(leftMax, height[l]);
                l++;
            } else {
                res += Math.max(0, rightMax - height[r]);
                rightMax = Math.max(rightMax, height[r]);
                r--;
            }
        }
        return res;
    }
}
