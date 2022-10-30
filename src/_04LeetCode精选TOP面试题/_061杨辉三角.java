package _04LeetCode精选TOP面试题;

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
}
