package _03经典面试题目.深度优先遍历宽度优先遍历;

import java.util.*;

/**
 * 字符串转换路径问题
 * 给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含
 * to，list中没有重复的字符串。所有字符串都是小写的。规定start每次只可以改
 * 变一个字符，最终的目标是彻底变成to，但每次变成的字符串都必须在list中存在。
 * 请返回最短的变换路径。
 *
 *         start = "abc"
 *         to = "cab"
 *         list = {"cab","acc","cbc","ccc","cac","cbb","aab","abb"}
 *         转换路径的方法有很多种，但是最短的转换路径如下：
 *         abc --> abb --> aab --> cab
 *         abc --> abb --> cbb --> cab
 *         abc --> cbc --> cac --> cab
 *         abc --> cbc --> cbb --> cab
 *
 * Created by huangjunyi on 2022/10/1.
 */
public class _01StartToShortPath {

    public static List<List<String>> getShortestPathList(String start, String to, List<String> list) {
        /*
        1、求邻居表
         */
        list.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(list);

        /*
        2、求到start的最短路径表
         */
        HashMap<String, Integer> distanceMap = getDistanceMap(start, nexts);

        /*
        根据两个表，找出start到to的所有最短路径
         */
        LinkedList<String> path = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        fillShortestPathList(start, to, nexts, distanceMap, path, res);
        return res;
    }

    private static void fillShortestPathList(
            String curr,
            String to,
            HashMap<String, ArrayList<String>> nexts,
            HashMap<String, Integer> distanceMap,
            LinkedList<String> path,
            List<List<String>> res) {

        /*
        每次递归，把当前字符串放入path中

        如果当前字符串等于to，代表已得出一条最短路径
        放入res中

        如果不是to，则遍历当前字符串的邻居，
        过滤出到start的最短路径为当前字符串到start最短路径+1的字符串
        表示只取从start到next的最短路径上必须经过curr的字符串
        然后进入下一轮递归
         */
        path.add(curr);
        if (curr.equals(to)) res.add(new LinkedList<>(path));
        else {
            for (String next : nexts.get(curr)) {
                if (distanceMap.get(next) == distanceMap.get(curr) + 1) {
                    fillShortestPathList(next, to, nexts, distanceMap, path, res);
                }
            }
        }
        path.pollLast();
    }

    private static HashMap<String, Integer> getDistanceMap(String start, HashMap<String, ArrayList<String>> nexts) {
        /*
        宽度优先遍历的方式求离start的最短路径表
         */
        HashMap<String, Integer> distanceMap = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        set.add(start);
        distanceMap.put(start, 0);
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (String next : nexts.get(curr)) {
                if (!set.contains(next)) {
                    distanceMap.put(next, distanceMap.get(curr) + 1);
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
        return distanceMap;
    }

    private static HashMap<String, ArrayList<String>> getNexts(List<String> list) {
        /*
        生成list中所有字符串的邻居表
        先把list中的左右字符串放入set中
        遍历每一个字符串
        然后遍历字符串的每一个字符，尝试替换为不同字符
        看set中是否存在该字符串替换字符后的字符串
         */
        HashSet<String> set = new HashSet<>(list);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (String str : list) {
            ArrayList<String> next =  new ArrayList<>();
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != chars[i]) {
                        char tmp = chars[i];
                        chars[i] = c;
                        String s = String.valueOf(chars);
                        if (set.contains(s)) next.add(s);
                        chars[i] = tmp;
                    }
                }
            }
            nexts.put(str, next);
        }
        return nexts;
    }

}
