package _01基础._02位运算;

/**
 * 一个数组中，所有数都出现m此，只有一种数出现k次，找到并返回这种数
 * m > 1，k < m
 * 要求空间复杂度O(1)，时间复杂度O(n)
 * Created by huangjunyi on 2022/11/19.
 */
public class BitOperation04 {
    public static int findKTimes(int[] nums, int k, int m) {
        // 一个32位的整形数组，存储每一个数的的每一个bit位相加
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bits[i] += ((num >> i) & 1);
            }
        }
        int res = 0;
        for (int i = 0; i < bits.length; i++) {
            // 如果一个bit位上，% m 后，不为0，代表这一位上，出现k次的数是1
            if (bits[i] % m != 0) res |= (1 << i);
        }
        return res;
    }
}
