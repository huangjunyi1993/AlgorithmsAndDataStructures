package _04LeetCode精选TOP面试题.宽度优先遍历;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 给定一个数组arr，开始位置start，结束位置end
 * arr上每个数a含义是要么往左跳a步，要么往右跳a步
 * 问从start跳到end最少要跳几步，跳不到则返回-1
 *
 * 1 <= start, start
 * 并且start=1，对应arr中的0下标
 *
 * Created by huangjunyi on 2022/10/29.
 */
public class _038跳跃游戏III {
    public int jump(int N, int[] arr, int start, int end) {
        if (start < 1 || start > N || end < 1 || end > N) return -1;
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> levelMap = new HashMap<>(); // 跳到的位置 => 步数
        queue.add(start);
        levelMap.put(start, 0);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            Integer level = levelMap.get(cur);
            if (cur == end) {
                return level;
            }
            int left = cur - arr[cur - 1];
            int right = cur + arr[cur + 1];
            if (left > 0 && !levelMap.containsKey(left)) {
                queue.add(left);
                levelMap.put(left, level + 1);
            }
            if (right <= N && !levelMap.containsKey(right)) {
                queue.add(right);
                levelMap.put(right, level + 1);
            }
        }
        return -1;
    }
}
