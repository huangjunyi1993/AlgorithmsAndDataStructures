package _03经典面试题目.分治法;

import java.util.Arrays;

/**
 * 构造特殊数组
 * 给定一个正整数M，请构造出一个长度为M的数组arr，
 * 要求，对任意的i、j、k三个位置，如果i＜j＜k，都有arr[i] + arr[k] != 2*arr[j]，
 * 返回构造出的arr
 *
 * 解法：
 * 如果有一个长度为3的数组[a,b,c]，a + c != 2*b，
 * 那么可以扩出一个2倍的数组[2*a-1,2*b-1,2*c-1,2*a,2*b,2*c]，依然成立，
 * 继续递归......
 * Created by huangjunyi on 2022/9/18.
 */
public class _01GouZaoTeShuShuZu {

    public static int[] makeNo(int size) {
        if (size == 1) return new int[] {1};
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        int[] res = new int[size];
        for (int i = 0; i < halfSize; i++) {
            res[i] = base[i] * 2 - 1;
        }
        for (int i = halfSize; i < size; i++) {
            res[i] = base[i - halfSize] * 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(makeNo(8)));
    }

}
