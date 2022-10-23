package _03经典面试题目.单调栈;

import java.util.LinkedList;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1
 * 其中
 * 内部全是1的所有子矩阵中，含有最多1的子矩阵中，含有几个1？
 *
 * Created by huangjunyi on 2022/10/16.
 */
public class _02MaximalRectangle {

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) return 0;
        int N = map.length;
        int M = map[0].length;

        /*
         数组压缩技巧 + 单调栈

         数组压缩：把二维数组压缩成一维数组，高的数组 height

         单调栈：得到最新的height，在用单调栈求解以当前行为底，最大的矩阵是大小
         单调栈记录的时height数组对于元素的下标
         单调栈从底部到顶部，保持单调递增，当遇到栈顶下标对应的高比要压入下标对应的高要高的话，结算栈顶元素
         */

        int[] height = new int[M];
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) height[j] = 0;
                else height[j]++;
            }
            max = Math.max(max, getMaxArea(height));
        }
        return max;
    }

    private static int getMaxArea(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= stack.peek()) {
                int curI = stack.pop();
                int r = i;
                int l = stack.isEmpty() ? -1 : stack.peek();
                int len = r - l - 1;
                maxArea = Math.max(maxArea, len * height[curI]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curI = stack.pop();
            int r = height.length;
            int l = stack.isEmpty() ? -1 : stack.peek();
            int len = r - l - 1;
            maxArea = Math.max(maxArea, len * height[curI]);
        }
        return maxArea;
    }

}
