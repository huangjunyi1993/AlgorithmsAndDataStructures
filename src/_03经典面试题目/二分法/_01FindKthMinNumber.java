package _03经典面试题目.二分法;

/**
 * 在两个排序数组中秋第K小的数
 * Created by huangjunyi on 2022/10/7.
 */
public class _01FindKthMinNumber {

    public static int findKthMinNumber(int[] A, int[] B, int K) {
        if (A == null || B == null) return -1;
        if (K < 1 || K > A.length + B.length) return -1;

        /*
        数组重新赋值
        区分出长短数组

        然后分三种情况：
        1、K小于等于短数组长度
        2、K大于短数组长度，但是小于等于长数组长度
        3、K大于长数组长度
        对于情况1，直接求两个数组的0~K-1范围的上中位数
        对于情况2，短数组中每个数都有可能，长数组要排除掉前面的一小部分，然后求两个数组对于范围内的上中位数
        对于情况3，两个数组都要排除掉前面不可能为结果的一小部分，然后再求两个子数组的上中位数

        求两个子数组的上中位数，必须保证两个子数组的长度相同
        情况2中长数组排除掉前面不可能的部分后，会比短数组多出1个数，左移要对长数组排除左边部分后的最左侧数单独判断
        如果它大于短数组中最后一个数，那么它就是答案

        情况3中
        因为K比两个数组的长度都大
        所以两个数组都要排除掉各种左边不可能为答案的部分
        两个数组排除后会发现剩下的部分是长度相等的
        但是求出的中位数是第K-1小的
        所以还有分别单独判断两个数组排除左侧部分后的最左侧数

        原理：
        比如(下面的数是编号，不是数组下标或值)
        A:[1,2,3,4,5,6,7,8,9,10]
        B:[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]
        求第15小
        A中10个数都有可能，比如A1比B14大，那么A1就是15小，所以A数组不用排除任何数
        B中1~4都不可能，比如B4比A10大，它也只是第14小
        所以排除B1~4，然后B16也不可能，因为在B中就有15个数比它小，所以排除B15~B的最后
        B5~B15参与求中位数，B中该范围比A多出1个数，所以单独对B5做判断
        如果B5不大于A10，把B5也排除
        那么就排除了5个数，A1~A10和B6~B15求上中位数
        求出的上中位数是A1~A10和B6~B15组成的数组中第10小的，加上原来排除的5个数
        就是第15小
         */
        int[] longs = A.length >= B.length ? A : B;
        int[] shorts = A.length < B.length ? A : B;
        int L = longs.length;
        int S = shorts.length;
        if (K <= S) {
            return getUpMedian(shorts, 0, K - 1, longs, 0, K - 1);
        }
        if (K > L) {
            if (shorts[K - L - 1] >= longs[L - 1]) return shorts[K - L - 1];
            if (longs[K - S - 1] >= shorts[S - 1]) return longs[K - S - 1];
            return getUpMedian(shorts, K - L, S - 1, longs, K - S, L - 1);
        }
        if (longs[K - S - 1] >= shorts[S - 1]) return longs[K - S - 1];
        return getUpMedian(shorts, 0, S - 1, longs, K - S, K - 1);
    }

    /**
     * 求两个长度相同的子数组的中位数
     * @param arr1 数组1
     * @param s1 数组1左边界
     * @param e1 数组1右边界
     * @param arr2 数组2
     * @param s2 数组2左边界
     * @param e2 数组2右边界
     * @return
     */
    public static int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (arr1[mid1] == arr2[mid2]) return arr1[mid1];
            if (((e1 - s1 + 1) & 1) == 1) {
                if (arr1[mid1] > arr2[mid2]) {
                    if (arr2[mid2] >= arr1[mid1 - 1]) return arr2[mid2];
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else {
                    if (arr1[mid1] >= arr2[mid2 - 1]) return arr1[mid1];
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
            } else {
                if (arr1[mid1] > arr2[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }

}
