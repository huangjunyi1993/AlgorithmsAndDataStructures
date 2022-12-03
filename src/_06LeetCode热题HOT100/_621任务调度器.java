package _06LeetCode热题HOT100;

/**
 * https://leetcode.cn/problems/task-scheduler/?favorite=2cktkvj
 *
 * Created by huangjunyi on 2022/11/13.
 */
public class _621任务调度器 {
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            /*
            贪心：
            先选出出现次数最大的任务
            然后依据n间距，先锚点
            然后其他任务依次往里填，必能保证每种任务间距大于等于n，而且耗时最短
            返回的耗时，看是否耗尽了空格，耗尽了则返回任务数，没耗尽返回任务数加空格数
             */
            int maxCount = 0;
            // 统计每种任务出现的次数，并且记录同种任务出现的最大次数
            int[] count = new int[256];
            for (int i = 0; i < tasks.length; i++) {
                count[tasks[i]]++;
                maxCount = Math.max(maxCount, count[tasks[i]]);
            }
            // 计算出现最大次数的任务，有多少种
            int maxKinds = 0;
            for (int i = 0; i < count.length; i++) {
                if (count[i] == maxCount) maxKinds++;
            }
            // 最后一组定了，砍掉，剩下的时要填的任务
            int tasksExpectFinalTeam = tasks.length - maxKinds;
            // 预设的空格数
            int spaces = (n + 1) * (maxCount - 1);
            // 模拟往里填
            int rest = Math.max(0, spaces - tasksExpectFinalTeam);
            // 空格有剩余，返回任务数+剩余空格数，没剩余，返回任务数
            return rest > 0 ? tasks.length + rest : tasks.length;
        }
    }
}
