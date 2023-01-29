package _04LeetCode精选TOP面试题.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/pascals-triangle/?favorite=2ckc81c
 *
 * Created by huangjunyi on 2022/10/30.
 */
public class _061杨辉三角 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> pre = null;
        /*
        1
        11
        121
        1331
        14641
        定好前两层
        每一层下标左对齐
        然后每一层根据前一层来
        每一层的每个位置都是上面和左上的两值相加，但是超出上一层长度时，填1
         */
        for (int i = 0; i < numRows; i++) {
            List<Integer> subRes = new ArrayList<>();
            subRes.add(1);
            if (i == 0) {
                res.add(subRes);
            } else if (i == 1) {
                subRes.add(1);
                res.add(subRes);
            } else {
                for (int j = 1; j < i; j++) {
                    subRes.add(pre.get(j - 1) + pre.get(j));
                }
                subRes.add(1);
                res.add(subRes);
            }
            pre = subRes;
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/pascals-triangle-ii/
     * 杨辉三角 II
     */
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            /*
            空间压缩技巧，实现list的自我更新
            每一层从右往左更新
             */
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i <= rowIndex; i++) {
                for (int j = res.size() - 1; j >= 1; j--){
                    res.set(j, res.get(j) + res.get(j - 1));
                }
                res.add(1);
            }
            return res;
        }
    }
}
