package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _004寻找两个正序数组的中位数 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
        * 思路：
        *
        * 实现一个f函数，求两个等长数组的上中位数
        *
        * 实现一个g函数，求两个不等长数组的第k小的数
        *
        * 主函数调g函数。如果两数组长度和为奇数，则调一次g函数，取中位数；如果两数组长度和为偶数，则调两次g函数，分别去上下中位数，再返回其平均值
        *
        * */
        if ((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0)) return 0;
        int N1 = nums1 == null ? 0 : nums1.length;
        int N2 = nums2 == null ? 0 : nums2.length;
        int N = N1 + N2;
        boolean even = (N & 1) == 1;
        if (N1 == 0) {
            return even ? nums2[N / 2] : (nums2[N / 2 - 1] + nums2[N / 2]) / 2D;
        } else if (N2 == 0) {
            return even ? nums1[N / 2] : (nums1[N / 2 - 1] + nums1[N / 2]) / 2D;
        } else {
            return even ? g(nums1, nums2, N / 2) : (g(nums1, nums2, N / 2) + g(nums1, nums2, N / 2 + 1)) / 2D;
        }
    }

    /**
     * 求两个排序数组整体第k小的数，k >= 1
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private double g(int[] nums1, int[] nums2, int k) {
        int[] longs = nums1.length > nums2.length ? nums1 : nums2;
        int[] shorts = nums1.length <= nums2.length ? nums1 : nums2;
        int L = longs.length;
        int S = shorts.length;
        if (k <= S) {
            return f(shorts, 0, k - 1, longs, 0, k - 1);
        } else if (k > S && k <= L) {
            // shorts: [1,2,3,4,5,6,7,8,9,10]
            // longs:  [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17]
            // k == 15
            // longs:  [1,2,3,4,||5|,6,7,8,9,10,11,12,13,14,15,||16,17]
            if (longs[k - S - 1] >= shorts[S - 1]) return longs[k - S - 1];
            return f(shorts, 0, S - 1, longs, k - S, k - 1);
        } else {
            // shorts: [1,2,3,4,5,6,7,8,9,10]
            // longs:  [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17]
            // k == 23
            // shorts: [1,2,3,4,5,||6,|7,8,9,10||]
            // longs:  [1,2,3,4,5,6,7,8,9,10,11,12,||13,|14,15,16,17||]
            if (longs[k - S - 1] >= shorts[S - 1]) return longs[k - S - 1];
            if (shorts[k - L - 1] >= longs[L - 1]) return shorts[k - L - 1];
            return f(shorts, k - L, S - 1, longs, k - S, L - 1);
        }
    }

    private double f(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2) {
        while (l1 < r1) {
            int N = r1 - l1 + 1;
            int mid1 = (l1 + r1) / 2;
            int mid2 = (l2 + r2) / 2;
            boolean even = (N & 1) == 1;
            if (even) {
                if (nums1[mid1] == nums2[mid2]) {
                    return nums1[mid1];
                } else if (nums1[mid1] > nums2[mid2]) {
                    // [6,7,||8,9,10]
                    // [1,2,||3,|4,5]
                    if (nums2[mid2] >= nums1[mid1 - 1]) return nums2[mid2];
                    r1 = mid1 - 1;
                    l2 = mid2 + 1;
                } else {
                    // [1,2,||3,|4,5]
                    // [6,7,||8,9,10]
                    if (nums1[mid1] >= nums2[mid2 - 1]) return nums1[mid1];
                    l1 = mid1 + 1;
                    r2 = mid2 - 1;
                }
            } else {
                if (nums1[mid1] == nums2[mid2]) {
                    return nums1[mid1];
                } else if (nums1[mid1] > nums2[mid2]) {
                    r1 = mid1;
                    l2 = mid2 + 1;
                } else {
                    l1 = mid1 + 1;
                    r2 = mid2;
                }
            }
        }
        // [1,2]
        // [3,4]
        // ==> nums1[l1] = 2 nums2[l2] = 3
        return Math.min(nums1[l1], nums2[l2]);
    }

}
