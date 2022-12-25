package _03经典面试题目.预处理数组;

/**
 * https://leetcode.cn/problems/largest-1-bordered-square/
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 * Created by huangjunyi on 2022/12/24.
 */
public class _07Largest1BorderedSquare {
    class Solution {
        public int largest1BorderedSquare(int[][] grid) {
            // 生成预处理数组
            // rightMap[i][j] i行j列右边有几个1（包括自己）
            int[][] rightMap = initRightMap(grid);
            // downMap[i][j] i行j列下面有几个1（包括自己）
            int[][] downMap = initDownMap(grid);
            int maxSize = Math.min(grid.length, grid[0].length);
            // 遍历，边长从大到小的可能性
            for (int size = maxSize; size > 0; size--) {
                if (hasBoard(size, rightMap, downMap)) {
                    return size * size;
                }
            }
            return 0;
        }

        private boolean hasBoard(int size, int[][] rightMap, int[][] downMap) {
            for (int i = 0; i <= rightMap.length - size; i++) {
                for (int j = 0; j <= rightMap[0].length - size; j++) {
                    if (rightMap[i][j] >= size // i行j列往右能推到size长度
                            && downMap[i][j] >= size // i行j列往下能推到size长度
                            && rightMap[i + size - 1][j] >= size // 下面往右也能推到size长度
                            && downMap[i][j + size - 1] >= size) { // 右边往下也能推到size长度
                        return true;
                    }
                }
            }
            return false;
        }

        private int[][] initDownMap(int[][] grid) {
            int[][] downMap = new int[grid.length][grid[0].length];
            for (int j = grid[0].length - 1; j >= 0; j --) {
                for (int i = grid.length - 1; i >= 0; i--) {
                    if (i == grid.length - 1) {
                        downMap[i][j] = grid[i][j] == 1 ? 1 : 0;
                    } else {
                        downMap[i][j] = grid[i][j] == 1 ? downMap[i + 1][j] + 1 : 0;
                    }
                }
            }
            return downMap;
        }

        private int[][] initRightMap(int[][] grid) {
            int[][] rightMap = new int[grid.length][grid[0].length];
            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[0].length - 1; j >= 0; j --) {
                    if (j == grid[0].length - 1) {
                        rightMap[i][j] = grid[i][j] == 1 ? 1 : 0;
                    } else {
                        rightMap[i][j] = grid[i][j] == 1 ? rightMap[i][j + 1] + 1 : 0;
                    }
                }
            }
            return rightMap;
        }
    }
}
