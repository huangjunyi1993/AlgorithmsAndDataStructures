package _04LeetCode精选TOP面试题.哈希表;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/rabbits-in-forest/
 * Created by huangjunyi on 2023/1/15.
 */
public class _157森林中的兔子 {
    class Solution {
        public int numRabbits(int[] answers) {
            /*
            1、排序
            2、排序后可以作为同一组的就靠在一起了
            3、用一个map统计：
                key（还有几只兔子颜色和我相同），
                一组兔子数：key + 1
                value（被询问的兔子中，一组内个数为key+1的兔子有多少只）
            4、一组兔子数：key + 1，组数：value / 组数 向上取整
            5、当前key对应的所有兔子数：(value / 组数 向上取整) * (key + 1)
             */
            Arrays.sort(answers);
            Map<Integer, Integer> map = new HashMap<>();
            for (int answer : answers) {
                if (map.containsKey(answer)) {
                    map.put(answer, map.get(answer) + 1);
                } else {
                    map.put(answer, 1);
                }
            }
            int all = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                all += ((entry.getValue() + entry.getKey()) / (entry.getKey() + 1)) * (entry.getKey() + 1);
            }
            return all;
        }
    }
}
