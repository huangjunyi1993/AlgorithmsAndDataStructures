package _03经典面试题目.动态规划;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/freedom-trail/
 *
 * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 *
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中
 * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 * 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * 当然, 我们还需要1步进行拼写。
 * 因此最终的输出是 4。
 *
 * Created by huangjunyi on 2022/10/9.
 */
public class _35FreedomTrail {

    public static int findRotateSteps1(String ring, String key) {
        char[] chsR = ring.toCharArray();
        char[] chsK = key.toCharArray();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < chsR.length; i++) {
            if (!map.containsKey(chsR[i])) {
                map.put(chsR[i], new ArrayList<>());
            }
            map.get(chsR[i]).add(i);;
        }
        int[][] dp = new int[chsR.length][chsK.length + 1];
        for (int i = 0; i < chsR.length; i++) {
            for (int j = 0; j <= chsK.length; j++) {
                dp[i][j] = -1;
            }
        }
        return process(map, chsK, 0, 0, ring.length(), dp);
    }

    private static int process(Map<Character, List<Integer>> map,
                               char[] chsK,
                               int preRingIndex,
                               int keyIndex,
                               int ringSize,
                               int[][] dp) {
        if (dp[preRingIndex][keyIndex] != -1) return dp[preRingIndex][keyIndex];
        if (keyIndex == chsK.length) {
            dp[preRingIndex][keyIndex] = 0;
            return dp[preRingIndex][keyIndex];
        }
        int res = Integer.MAX_VALUE;
        for (int rIndex : map.get(chsK[keyIndex])) {
            int step = dial(preRingIndex, rIndex, ringSize) + 1;
            step += process(map, chsK, rIndex, keyIndex + 1, ringSize, dp);
            res = Math.min(step, res);
        }
        dp[preRingIndex][keyIndex] = res;
        return res;
    }

    private static int dial(int a, int b, int ringSize) {
        return Math.max(Math.abs(a - b), Math.min(a, b) + ringSize - Math.max(a, b));
    }
}

/***************************************
 *
 *
 * 左神代码 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
 *
 *
 ***************************************/
class Code02_FreedomTrail {

    public static int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
    }

    public static int findRotateSteps1(String r, String k) {
        char[] ring = r.toCharArray();
        int rsize = ring.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < rsize; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        return minSteps1(0, 0, k.toCharArray(), map, rsize);
    }

    // preStrIndex : 目前被对齐的位置是什么位置
    // keyIndex : 请搞定key[keyIndex...]
    // key      : 目标串（固定参数）
    // map      : 任何一个字符，什么位置上有它（固定参数）
    // rsize    : 电话表盘上一共的位置个数（固定参数）
    // 返回最低代价
    public static int minSteps1(
            int preStrIndex,
            int keyIndex,
            char[] key,
            HashMap<Character, ArrayList<Integer>> map,
            int rsize) {
        if (keyIndex == key.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        // key[keyIndex]
        for (int curStrIndex : map.get(key[keyIndex])) {
            int step = dial(preStrIndex, curStrIndex, rsize) + 1
                    + minSteps1(curStrIndex, keyIndex + 1, key, map, rsize);
            ans = Math.min(ans, step);
        }
        return ans;
    }

    public static int findRotateSteps2(String r, String k) {
        char[] ring = r.toCharArray();
        int rsize = ring.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < rsize; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        int[][] dp = new int[rsize][k.length() + 1];
        for (int i = 0; i < rsize; i++) {
            for (int j = 0; j <= k.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return minSteps2(0, 0, k.toCharArray(), map, rsize, dp);
    }

    public static int minSteps2(int preStrIndex, int keyIndex, char[] key, HashMap<Character, ArrayList<Integer>> map,
                                int rsize, int[][] dp) {
        if (dp[preStrIndex][keyIndex] != -1) {
            return dp[preStrIndex][keyIndex];
        }
        if (keyIndex == key.length) {
            dp[preStrIndex][keyIndex] = 0;
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int curStrIndex : map.get(key[keyIndex])) {
            int step = dial(preStrIndex, curStrIndex, rsize) + 1
                    + minSteps2(curStrIndex, keyIndex + 1, key, map, rsize, dp);
            ans = Math.min(ans, step);
        }
        dp[preStrIndex][keyIndex] = ans;
        return ans;
    }

    // 彻底动态规划的方法请同学们自己改出

}

