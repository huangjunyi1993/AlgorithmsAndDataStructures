package _02进阶._17单调栈;

import java.util.LinkedList;

/**
 * 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * Created by huangjunyi on 2022/12/1.
 */
public class MonotonousStack03 {

    class Solution {
        public int largestRectangleArea(int[] heights) {
            int max = 0;
            // 单调栈，单调递增，枚举每一个数作为高，去就算一个长方形
            LinkedList<Integer> stack = new LinkedList<>();
            for (int i = 0; i < heights.length; i++) {
                // 入栈下标对应的高比栈顶对应的高矮，弹出栈顶结算
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    int pop = stack.pop();
                    // 弹出的栈顶元素底下压着的，是左边离它最近比它矮的，当前要入栈的元素是右边离它最近比它矮的，中间的长度乘以高，就可以结算处一个矩形
                    max = Math.max(max, heights[pop] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
                }
                stack.push(i);
            }
            // 结算栈中剩余的
            while (!stack.isEmpty()) {
                int pop = stack.pop();
                max = Math.max(max, heights[pop] * (stack.isEmpty() ? heights.length : heights.length - 1 - stack.peek()));
            }
            return max;
        }
    }

}
