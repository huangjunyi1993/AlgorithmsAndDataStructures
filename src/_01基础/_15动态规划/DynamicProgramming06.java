package _01基础._15动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文。
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}。ba + ba + c 3张， abcd + abcd 2张， abcd + ba 2张。所以返回2。
 * Created by huangjunyi on 2022/9/3.
 */
public class DynamicProgramming06 {

    /**
     * 暴力递归
     * @param str 目标字符串
     * @param arr 贴纸数组
     * @return
     */
    public static int getMin01(String str, String[] arr) {
        int res = process01(str, arr);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 暴力递归
     * @param str 剩余字符串
     * @param arr 贴纸数组
     * @return
     */
    private static int process01(String str, String[] arr) {
        // 字符串已经拼好，还需0张贴纸
        if (str.length() == 0) return 0;
        int min = Integer.MAX_VALUE;
        // 尝试每一张贴纸
        for (int i = 0; i < arr.length; i++) {
            // 减去选择的贴纸的字符，返回新的剩余要拼的字符串
            String newStr = getNewStr(str, arr[i]);
            // 选择的贴纸没有削去任何一个字符，无效，跳过
            if (newStr.length() == str.length()) continue;
            // 剩余字符串newStr，往下继续跑递归
            min = Math.min(min, process01(newStr, arr));
        }
        // 算上自己使用的一张贴纸
        if (min != Integer.MAX_VALUE) min += 1;
        return min;
    }

    /**
     * 原字符串，减去贴纸中的字符，返回新的字符串
     * @param s1 原字符串
     * @param s2 贴纸
     * @return
     */
    private static String getNewStr(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int[] count = new int[26];
        // 统计原字符串的各种字符数
        for (int i = 0; i < chs1.length; i++) {
            count[chs1[i] - 'a']++;
        }
        // 减去贴纸含有的各种字符的字符数
        for (int i = 0; i < chs2.length; i++) {
            count[chs2[i] - 'a']--;
        }
        // 拼接处新的剩余字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    sb.append((char)(i + 'a'));
                }
            }
        }
        return sb.toString();
    }

    public static int getMin(String str, String[] arr) {
        /*
        map记录每种贴纸的字符出现的个数
        例如map[i][0] 表示第i张贴纸，a字符出现的次数（a - a == 0）
        初始化号map表
         */
        int[][] map = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            for (char aChar : chars) {
                map[i][aChar - 'a'] += 1;
            }
        }

        // 缓存表，key剩余字符串，value需要的贴纸数，一个一维表，因为暴力递归中只有一个可变参数（剩余字符串）
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(str, map, dp);
    }

    private static int process(String remain, int[][] map, Map<String, Integer> dp) {
        // 如果缓存表有值，直接返回
        if (dp.containsKey(remain)) return dp.get(remain);

        // 统计原字符串的各种字符数
        char[] chars = remain.toCharArray();
        int[] temp = new int[26];
        for (char aChar : chars) {
            temp[aChar - 'a'] += 1;
        }
        int res = Integer.MAX_VALUE;

        // 尝试每一张贴纸
        for (int i = 0; i < map.length; i++) {
            StringBuilder sb = new StringBuilder();
            // 优化，只选择能消除掉当前字符串第一个字符的贴纸（因为第一个字符串总要消掉的，先消后消不会影响结果）
            if (map[i][chars[0] - 'a'] == 0) continue;
            // 减去贴纸含有的各种字符的字符数
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < Math.max(0, temp[j] - map[i][j]); k++) {
                    sb.append((char) (j + 'a'));
                }
            }
            // 剩余字符串newStr，往下继续跑递归
            int next = process(sb.toString(), map, dp);
            // next + 1 算上自己使用的一张贴纸
            if (next != -1) res = Math.min(res, next + 1);
        }

        // 结果返回前，先放入缓存表
        dp.put(remain, res == Integer.MAX_VALUE ? -1 : res);
        return dp.get(remain);
    }

    public static void main(String[] args) {
        String str = "babac";
        String[] arr = {"ba","c","abcd"};
        System.out.println(getMin01(str, arr));
        System.out.println(getMin(str, arr));
    }

}
