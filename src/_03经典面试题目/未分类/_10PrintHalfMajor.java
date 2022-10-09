package _03经典面试题目.未分类;

/**
 * 给定一个数组arr，如果有某个数出现次数超过了数组长度的一半，打印这个数，如果没有不打印。
 * 额外空间O(1)
 *
 * Created by huangjunyi on 2022/10/6.
 */
public class _10PrintHalfMajor {

    public void majorityElement(int[] nums) {
        /*
        模拟每次从数组中删除两个不同的数
        如果数组中有数的个数超过数组长度一半
        那么删完以后，剩下的数，就是个数超一半的数
         */
        int cand = 0;
        int HP = 0;
        for (int i = 0; i < nums.length; i++) {
            if (HP == 0) {
                cand = nums[i];
                HP = 1;
            } else if (nums[i] == cand) {
                HP++;
            } else {
                HP--;
            }
        }

        /*
        最后验证该数是否真的是个数超一半的
        因为有的情况最后剩下的数不是超一半的
        例如 [1,2,3,4,5]，最后删剩下5，但是不是超一半
         */
        if (HP == 0) System.out.println("no such element");
        HP = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cand == nums[i]) HP++;
        }
        if (HP > nums.length / 2) System.out.println(cand);
        else System.out.println("no such element");
    }

}
