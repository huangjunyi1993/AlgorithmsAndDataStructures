package _04LeetCode精选TOP面试题.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/spiral-matrix/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _036螺旋矩阵 {
    public List<Integer> spiralOrder(int[][] matrix) {
        /*
        a 左上方行
        b 左上方列
        c 右下方行
        d 右下方列

        从外层到内存
        转圈打印即可
         */
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        List<Integer> res = new ArrayList<>();
        while (a <= c && b <= d) {
            process(matrix, a++, b++, c--, d--, res);
        }
        return res;
    }

    private void process(int[][] matrix, int a, int b, int c, int d, List<Integer> res) {
        if (a == c) {
            for (int i = b; i <= d; i++) {
                res.add(matrix[a][i]);
            }
            return;
        }
        if (b == d) {
            for (int i = a; i <= c; i++) {
                res.add(matrix[i][b]);
            }
            return;
        }
        for (int i = b; i < d; i++) {
            res.add(matrix[a][i]);
        }
        for (int i = a; i < c; i++) {
            res.add(matrix[i][d]);
        }
        for (int i = d; i > b; i--) {
            res.add(matrix[c][i]);
        }
        for (int i = c; i > a; i--) {
            res.add(matrix[i][b]);
        }
    }
}
