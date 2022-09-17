package _01基础._14暴力递归;

/**
 * N皇后问题（位运算版）
 * Created by huangjunyi on 2022/9/3.
 */
public class Recursive08 {

    public static int nQueue(int n) {
        if (n > 32) return -1;
        int limit = n  == 32 ? -1 : 1 << n - 1;
        int leftLimit = 0; // 左对角线限制
        int rightLimit = 0; // 右对角线限制
        int column = 0; // 已放皇后的列
        return process(limit, column, leftLimit, rightLimit);
    }

    private static int process(int limit, int column, int leftLimit, int rightLimit) {
        if (limit == column) return 1;
        int res = 0;
        int pos = limit & (~(column|leftLimit|rightLimit)); //可以放皇后的所有位置，&运算是排除范围外的1的干扰
        while (pos != 0) {
            int currPos = pos & (~pos + 1); //每次提前出当前可放皇后位置的最右侧的1
            res += process(limit, column|currPos, (leftLimit|currPos) << 1, (rightLimit|currPos) >> 1);
            pos ^= currPos; //该位置已经尝试过放皇后，去除该位置
        }
        return res;
    }

}
