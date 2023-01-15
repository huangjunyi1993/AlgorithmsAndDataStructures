package _03经典面试题目.Manacher;

import java.util.*;

/**
 *给定字符串数组words，其中所有字符串都不相同，如果words[i]+words[j]是回文
 *串就记录(i,j)，找到所有记录并返回
 * 例子一：
 * 输入：["abcd", "dcba", "lls", "s", "sssll"]
 * 输出：[[0,1], [1,0], [3,2], [2,4]]
 * 解析：输出的每一组数组，每个下标代表字符串拼接在一起，都是回文串
 * abcddcba bcdaabcd slls llssssll
 * Created by huangjunyi on 2022/10/16.
 */
public class _01PalindromePairs {

    public static List<List<Integer>> palindromePairs(String[] words) {

        /*
        先把每个字符串与对应的下标rest，加入到一个map中 wordIndexMap
        然后遍历每个字符串

        比如遍历到 字符串str aabaa 对应下标为index

        从左往右看，直到中点，检查str的某些前缀是否是回文，并且剩下的后缀逆序后，是否在wordIndexMap存在，是则有一种结果（剩余后缀逆序后拼前面）
        a是不是回文串 -> 是 -> 看 abaa 的逆序串 aaba 是否存在 wordIndexMap 中 -> 存在 -> aaba 拼接在前面，可以形成回文串 -> 记录结果(rest，index)
        接着aa同样方式判断

        从右往左看，直到中点，检查str的某些后缀是否是回文，并且剩下的前缀逆序后，是否在wordIndexMap存在，是则有一种结果（剩余前缀逆序后拼后面）
        a是不是回文串 -> 是 -> 看 aaba 的逆序串 abaa 是否存在 wordIndexMap 中 -> 存在 -> abaa 拼接在后面，可以形成回文串 -> 记录结果(index, rest)
        接着aa同样方式处理

        然后整个字符串，也要检查，检查wordIndexMap中是否有空串，空串拼接在前、后，都可以形成回文串，记录结果(index, rest)、(rest，index)

        检查一个子串是否是回文串，可以用Manacher算法加速
         */

        // 先把每个字符串与对应的下标rest，加入到一个map中 wordIndexMap
        HashMap<String, Integer> wordIndexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordIndexMap.put(words[i], i);
        }

        // 然后遍历每个字符串
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            findAll(words[i], i, wordIndexMap, res);
        }
        return res;
    }

    private static void findAll(String word,
                                int index,
                                HashMap<String, Integer> wordIndexMap,
                                List<List<Integer>> res) {

        // 单独检查，自己是回文串，wordIndexMap里又有又有空串的情况，前面拼一个空串，后面拼一个空串，共算2
        String reverse = getReverse(word);
        Integer rest = wordIndexMap.get("");
        if (rest != null && rest != index && reverse.equals(word)) {
            addRecode(res, index, rest);
            addRecode(res, rest, index);
        }

        // 检查一个子串是否是回文串，可以用Manacher算法加速
        String postStr = processStr(word);
        int[] rArr = manacher(postStr);
        int mid = rArr.length >> 1;

        /*
        从左往右看，直到中点
        a是不是回文串 -> 是 -> 看 abaa 的逆序串 aaba 是否存在 wordIndexMap 中 -> 存在 -> aaba 拼接在前面，可以形成回文串 -> 记录结果(rest，index)
        接着aa同样方式判断
         */
        for (int i = 1; i < mid; i++) {
            if (i - rArr[i] == -1) {
                rest = wordIndexMap.get(reverse.substring(0, mid - i));
                if (rest != null && rest != index) {
                    addRecode(res, rest, index);
                }
            }
        }

        /*
        从右往左看，直到中点
        a是不是回文串 -> 是 -> 看 aaba 的逆序串 abaa 是否存在 wordIndexMap 中 -> 存在 -> abaa 拼接在后面，可以形成回文串 -> 记录结果(index, rest)
        接着aa同样方式处理
         */
        for (int i = mid + 1; i < rArr.length; i++) {
            if (i + rArr[i] == rArr.length) {
                rest = wordIndexMap.get(reverse.substring((mid << 1) - i));
                if (rest != null && rest != index) {
                    addRecode(res, index, rest);
                }
            }
        }
    }

    private static int[] manacher(String str) {
        int[] rArr = new int[str.length()];
        char[] chs = str.toCharArray();
        int R = -1;
        int C = -1;
        for (int i = 0; i < chs.length; i++) {
            rArr[i] = R > i ? Math.min(rArr[2 * C - i], R - i) : 1;
            while (i + rArr[i] < chs.length && i - rArr[i] >= 0) {
                if (chs[i + rArr[i]] == chs[i - rArr[i]]) {
                    rArr[i]++;
                } else {
                    break;
                }
            }
            if (i + rArr[i] > R) {
                R = i + rArr[i];
                C = i;
            }
        }
        return rArr;
    }

    private static String processStr(String word) {
        char[] chs = word.toCharArray();
        char[] postChs = new char[chs.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < postChs.length; i++) {
            postChs[i] = (i & 1) == 0 ? '#' : chs[index++];
        }
        return String.valueOf(postChs);
    }

    private static void addRecode(List<List<Integer>> res, Integer index1, int index2) {
        res.add(Arrays.asList(index1, index2));
    }

    private static String getReverse(String word) {
        char[] chs = word.toCharArray();
        int L = 0;
        int R = word.length() - 1;
        while (L < R) {
            char tmp = chs[L];
            chs[L] = chs[R];
            chs[R] = tmp;
            L++;
            R--;
        }
        return String.valueOf(chs);
    }

}
