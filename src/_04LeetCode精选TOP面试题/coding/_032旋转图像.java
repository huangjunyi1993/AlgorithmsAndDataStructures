package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/rotate-image/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _032旋转图像 {
    public void rotate(int[][] matrix) {
        int tr = 0;
        int tc = 0;
        int dr = matrix.length - 1;
        int dc = matrix[0].length - 1;
        /*
        □△○□
        ○□□△
        △□□○
        □○△□
        从外围到内围
        每一层
        相同组
        追尾交换
         */
        while (tr < dr) {
            rotateEdge(matrix, tr++, tc++, dr--, dc--);
        }
    }

    private void rotateEdge(int[][] matrix, int tr, int tc, int dr, int dc) {
        int times = dc - tc;
        for (int i = 0; i < times; i++) {
            int temp = matrix[tr][tc + i];
            matrix[tr][tc + i] = matrix[dr - i][tc];
            matrix[dr - i][tc] = matrix[dr][dc - i];
            matrix[dr][dc - i] = matrix[tr + i][dc];
            matrix[tr + i][dc] = temp;
        }
    }
}
