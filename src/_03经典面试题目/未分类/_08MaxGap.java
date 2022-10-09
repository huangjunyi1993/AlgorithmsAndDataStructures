package _03经典面试题目.未分类;

/**
 * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
 * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
 *
 * 示例:
 * 输入: nums = [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 *
 * Created by huangjunyi on 2022/10/5.
 */
public class _08MaxGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int N = nums.length;
        // 获取nums数组中的最大值和最小值
        int min = nums[0];
        int max = nums[0];
        for (int i = 0; i < N; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) return 0;

        /*
        分桶，如果nums的长度为N，则分N+1个桶
        遍历nums中的每一个数，
        计算该数落在那个桶中
        并尝试更新该桶的最大值和最小值
         */
        int[] mins = new int[N + 1];
        int[] maxs = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            int bid = bucketId(nums[i], N, min, max);
            mins[bid] = visited[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = visited[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            visited[bid] = true;
        }

        /*
        遍历所有的桶
        后一个桶的最小值 与 前一个桶的最大值 相减
        答案必在其中
         */
        int res = 0;
        int pre = maxs[0];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) continue;
            res = Math.max(res, mins[i] - pre);
            pre = maxs[i];
        }
        return res;
    }

    private int bucketId(long num, long len, long min, long max) {
        return (int) (((num - min) * len) / (max - min));
    }
}
