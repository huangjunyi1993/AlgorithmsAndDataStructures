package _02进阶._17单调栈;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 统计全 1 子矩形
 * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 * Created by huangjunyi on 2022/12/2.
 */
public class MonotonousStack05 {
    class Solution {
        public int numSubmat(int[][] mat) {
            int res = 0;
            // 压缩为一位数组，遍历每一行，以当前行为底的一个柱状图
            int[] heights = new int[mat[0].length];
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    // 当前行打底的数位0，在高度为0，否则叠加1
                    heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
                }
                System.out.println(Arrays.toString(heights));
                // 统计以当前行为底的矩阵个数，累加到结果
                res += countRectangle(heights);
            }
            return res;
        }

        private int countRectangle(int[] heights) {
            int res = 0;
            // 单调栈，单调递增，弹出时（遇到左右两边最近的比当前高度矮的）结算一个矩阵数
            LinkedList<Integer> stack = new LinkedList<>();
            for (int i = 0; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    int pop = stack.pop();
                    // 高度相同，跳过，等后面的再算
                    // 矩阵数换算公式：(hi - max{hj, hk}) * ((l * (l + 1)) / 2)
                    // 减 max{hj, hk} 是因为防止算重，左右两边清算时，因为底部是连通的
                    // hi当前位置高度，hj左边最近的比当前位置矮的高度，右边最近的比当前位置矮的高度
                    // l，能扩到的长度
                    int len = stack.isEmpty() ? i : i - 1 - stack.peek();
                    int max = Math.max(stack.isEmpty() ? 0 : heights[stack.peek()], heights[i]);
                    res += (heights[pop] - max) * ((len * (len + 1)) >> 1);
                }
                stack.push(i);
            }
            // 清算栈中剩余元素
            while (!stack.isEmpty()) {
                int pop = stack.pop();
                // 如果栈空，长度就是数组长度
                int len = stack.isEmpty() ? heights.length : heights.length - 1 - stack.peek();
                // 因已经右边没有比当前位置矮的，所以只看左边
                int max = stack.isEmpty() ? 0 : heights[stack.peek()];
                res += (heights[pop] - max) * ((len * (len + 1)) >> 1);
            }
            return res;
        }
    }
}
