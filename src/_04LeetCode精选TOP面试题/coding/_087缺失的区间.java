package _04LeetCode精选TOP面试题.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个排序数据nums，一个区间lower，upper
 * 返回区间缺失的数
 * 例如nums=[0,1,3,50,75]，lower=0，upper=99
 * 返回["2","4->49","51->74","76->99"]
 * Created by huangjunyi on 2022/11/5.
 */
public class _087缺失的区间 {
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int num : nums) {
            // 当前num大于下界，缺了lower~num-1这一段，加入res
            if (num > lower) {
                res.add(miss(lower, num - 1));
            }
            // num大于等于上界，返回res
            if (num >= upper) {
                return res;
            }
            lower = num + 1;
        }
        // 所有num遍历完，还没到上界，加入最后一段
        if (lower <= upper) res.add(miss(lower, upper));
        return res;
    }

    private static String miss(int lower, int upper) {
        String left = String.valueOf(lower);
        String right = "";
        if (lower < upper) {
            right = "->" + String.valueOf(upper);
        }
        return left + right;
    }
}
