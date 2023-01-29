package _04LeetCode精选TOP面试题.coding;

/**
 * https://leetcode.cn/problems/roman-to-integer/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/23.
 */
public class _011罗马数字转整数 {
    public int romanToInt(String s) {
        int nums[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) sum -= nums[i]; // 前面比后面的数小，出负数
            else sum += nums[i]; // 前面比后面的数大，出正数
        }
        sum += nums[nums.length - 1];
        return sum;
    }
}
