package _04LeetCode精选TOP面试题;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _049柱状图中最大的矩形 {
    public int largestRectangleArea(int[] heights) {
        /*
        利用单调栈求解
        保证栈中元素从小到大
        遇到小压大，弹出栈顶大的结算
        结算的时以弹出的作为高，看左右能伸到哪，结算面积
         */
        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                Integer cur = stack.pop();
                int curHeight = heights[cur];
                int right = i;
                int left = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, (right - left - 1) * curHeight);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            int curHeight = heights[cur];
            int right = heights.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            res = Math.max(res, (right - left - 1) * curHeight);
        }
        return res;
    }
}
