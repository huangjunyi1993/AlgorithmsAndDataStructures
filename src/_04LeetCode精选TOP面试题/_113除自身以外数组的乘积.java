package _04LeetCode精选TOP面试题;

/**
 * https://leetcode.cn/problems/product-of-array-except-self/description/
 *
 * Created by huangjunyi on 2022/11/6.
 */
public class _113除自身以外数组的乘积 {
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] res = new int[nums.length];
            int p = 1, q = 1;
            // 第一趟遍历，res[i]记录0~i-1的累成积
            for (int i = 0; i < nums.length; i++) {
                res[i] = p;
                p *= nums[i];
            }
            // 第二趟遍历，q记录i~len-1的累成积，然后把该积累乘到res[i]中，res[i]此时技术除自身外的乘积
            for (int i = nums.length - 1; i >= 1; i--) {
                q *= nums[i];
                res[i - 1] *= q;
            }
            return res;
        }
    }
}
