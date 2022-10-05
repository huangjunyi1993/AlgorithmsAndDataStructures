package _03经典面试题目.二叉树;

import java.util.HashMap;

/**
 *已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
 * 比如给定:
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 };
 * 返回: {4,5,2,6,7,3,1}
 * Created by huangjunyi on 2022/9/25.
 */
public class _02BuildPostTree {

    public static int[] getPost(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) return null;
        //用一张表记录中序数组中数与下标的映射
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        int n = pre.length;
        int[] post = new int[pre.length];
        //递归处理
        process(pre, 0, n - 1, in, 0, n - 1, post, 0, n - 1, inMap);
        return post;
    }

    /**
     * 根据前序数组和前序数组递归填写后序数组
     * @param pre 前序数组
     * @param l1 前序数组当前范围左边界
     * @param r1 前序数组当前范围右边界
     * @param in 中序数组
     * @param l2 前序数组当前范围左边界
     * @param r2 前序数组当前范围右边界
     * @param post 后序数组
     * @param l3 后序数组当前范围左边界
     * @param r3 后序数组当前范围右边界
     * @param inMap 后序数组数与下标映射表
     */
    private static void process(int[] pre, int l1, int r1,
                                int[] in, int l2, int r2,
                                int[] post, int l3, int r3,
                                HashMap<Integer, Integer> inMap) {
        if (l1 > r1) return;
        //只有一个数，把先序数组中的数填到后序数组上，直接返回
        if (l1 == r1) {
            post[l3] = pre[l1];
            return;
        }
        /*
        前序数组：[a,......]
        后序数组：[......,a]
        中序数组：[...,a,..]
        把先序数组当前范围的第一个数填到后序数组当前范围的最后一个位置
        然后找出这个数在中序数组中的位置
        然后进下标计算，递归处理子范围
         */

        post[r3] = pre[l1];
        int mid = inMap.get(pre[l1]);
        process(pre, l1 + 1, l1 + (mid - l2), in, l2, mid - 1, post, l3, l3 + (mid - l2) - 1, inMap);
        process(pre, l1 + (mid - l2) + 1, r1, in, mid + 1, r2, post, l3 + (mid - l2), r3 - 1, inMap);
    }

}
