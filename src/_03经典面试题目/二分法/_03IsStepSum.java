package _03经典面试题目.二分法;

import java.util.HashMap;

/**
 * 定义何为step num？
 * 比如680,680 + 68 + 6 = 754,680的step sum叫754
 * 给定一个正数num，判断它是不是某个数的step num
 *
 * Created by huangjunyi on 2022/12/26.
 */
public class _03IsStepSum {

    public static boolean isStepSum(int stepSum) {
        int l = 0;
        int r = stepSum;
        /*
         二分
         中点数的stepSum是不是与入参相等，是则返回true
         如果大于入参，左边二分
         小于入参，右边二分
          */
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            int curStepSum = getStepSum(m);
            if (stepSum == curStepSum) return true;
            else if (curStepSum < stepSum) l = m + 1;
            else r = m - 1;
        }
        return false;
    }

    /**
     * 计算num的stepSum
     * @param num
     * @return
     */
    private static int getStepSum(int num) {
        int res = 0;
        while (num > 0) {
            res += num;
            num /= 10;
        }
        return res;
    }

}
