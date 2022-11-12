package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _115搜索二维矩阵II {
    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int N = matrix.length;
            int M = matrix[0].length;
            int x = 0;
            int y = M - 1;
            // 相当于一棵二叉搜索树，根节点是右上角的节点
            while (x < N && y >= 0) {
                if (matrix[x][y] > target) {
                    y--;
                } else if (matrix[x][y] < target) {
                    x++;
                } else {
                    return true;
                }
            }
            return false;
        }
    }
}
