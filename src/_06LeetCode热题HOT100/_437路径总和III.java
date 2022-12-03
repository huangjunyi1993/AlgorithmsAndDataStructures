package _06LeetCode热题HOT100;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/path-sum-iii/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public interface _437路径总和III {
    class Solution {
        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode() {}
            TreeNode(int val) { this.val = val; }
            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
        public int pathSum(TreeNode root, int targetSum) {
            // 路径前缀和 -> 出现的次数
            HashMap<Long, Integer> preSumCountMap = new HashMap<>();
            preSumCountMap.put(0L, 1);
            return process(root, targetSum, 0L, preSumCountMap);
        }

        private int process(TreeNode node, int targetSum, long all, HashMap<Long, Integer> preSumCountMap) {
            if (node == null) return 0;
            all += node.val;
            int res = 0;
            // 当前路径前缀和 - 目标路径长度 -> 出现的次数 -> 答案
            if (preSumCountMap.containsKey(all - targetSum)) {
                res += preSumCountMap.get(all - targetSum);
            }
            // 更新 路径前缀和 -> 出现的次数
            if (preSumCountMap.containsKey(all)) {
                preSumCountMap.put(all, preSumCountMap.get(all) + 1);
            } else {
                preSumCountMap.put(all, 1);
            }

            res += process(node.left, targetSum, all, preSumCountMap);
            res += process(node.right, targetSum, all, preSumCountMap);

            // 擦除
            if (preSumCountMap.get(all) == 1) {
                preSumCountMap.remove(all);
            } else {
                preSumCountMap.put(all, preSumCountMap.get(all) - 1);
            }
            return res;
        }
    }
}
