package _01基础._15动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文。
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}。a + ba + c 3 abcd + abcd 2 abcd+ba 2。所以返回2。
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming06 {

    public static int getMin(String str, String[] arr) {
        int[][] map = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            for (char aChar : chars) {
                map[i][aChar - 'a'] += 1;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(str, map, dp);
    }

    private static int process(String remain, int[][] map, Map<String, Integer> dp) {

        if (dp.containsKey(remain)) return dp.get(remain);

        char[] chars = remain.toCharArray();
        int[] temp = new int[26];
        for (char aChar : chars) {
            temp[aChar - 'a'] += 1;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            StringBuilder sb = new StringBuilder();
            if (map[i][chars[0] - 'a'] == 0) continue;
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < Math.max(0, temp[j] - map[i][j]); k++) {
                    sb.append((char) (j + 'a'));
                }
            }
            int next = process(sb.toString(), map, dp);
            if (next != -1) {
                res = Math.min(res, next + 1);
            }
        }

        dp.put(remain, res == Integer.MAX_VALUE ? -1 : res);
        return dp.get(remain);
    }

}
