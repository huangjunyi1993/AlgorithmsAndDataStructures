package _02进阶._17单调栈;

import java.util.LinkedList;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，
 * 返回全部由1组成的最大子矩阵，内部有多少个1
 *
 * Created by huangjunyi on 2022/12/2.
 */
public class MonotonousStack04 {
    public static int max(char[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) return 0;
        int[] heights = new int[map.length];
        int max = 0;
        for (int i = 0; i < map.length; i++) {
            // 压缩数组，把二维矩阵，以当前行为底，压缩成一个一位数组，代表一个柱状图
            for (int j = 0; j < map[i].length; j++) {
                // 如果当前行的该位置为0，则该位置的柱子高度清0，否则+1
                heights[j] = map[i][j] == '0' ? 0 : heights[j] + 1;
            }
            // 转化为柱状图最大矩阵面积的求解，每次抓一个最大面积PK
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }

    /**
     * 柱状图最大矩阵面积
     */
    private static int largestRectangleArea(int[] heights) {
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int pop = stack.pop();
                max = Math.max(max, heights[pop] * (stack.isEmpty() ? i - 1 : i - 1 - stack.peek()));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            max = Math.max(max, heights[pop] * (stack.isEmpty() ? heights.length - 1 : heights.length - 1 - stack.peek()));
        }
        return max;
    }
}
