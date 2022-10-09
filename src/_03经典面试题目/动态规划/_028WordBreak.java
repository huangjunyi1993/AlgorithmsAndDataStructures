package _03经典面试题目.动态规划;

/**
 * 假设所有字符都是小写字母，长字符串时str
 * arr是去重的单词表，每个单词都是不空字符串且可以使用任意次
 * 使用arr中的单词有多少种拼接str的方式，返回方法数
 *
 * Created by huangjunyi on 2022/10/5.
 */
public class _028WordBreak {

    private static class Node {
        private boolean end;
        private Node[] nexts;

        public Node() {
            nexts = new Node[26];
        }
    }

    public static int ways(String str, String[] arr) {
        if (str == null || str.length() == 0) return 1;
        if (arr == null || arr.length == 0) return 0;
        // 把单词表转成前缀树
        Node root = buildTree(arr);
        int N = str.length();
        /*
         一维dp表
         dp[i]表示str从i位置开始，被arr拆分的方式有多少种

         从尾部开始往头位置填表
         每次递归表示尝试str字符串从i位置开始到end的子字符串，是否是arr中的一个单词
         是的话，则计算该单词参与拆分，有多少种拆分方法（dp[end + 1]）
         利用前缀树加速，如果在前缀树上找不到路，则提前结束该轮循环
         */
        int[] dp = new int[N + 1];
        dp[N] = 1;
        char[] chs = str.toCharArray();
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                Node next = cur.nexts[chs[end] - 'a'];
                if (next == null) break;
                cur = next;
                if (cur.end) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    private static Node buildTree(String[] arr) {
        Node root = new Node();
        for (String word : arr) {
            Node cur = root;
            char[] chs = word.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                cur.nexts[chs[i] - 'a'] = cur.nexts[chs[i] - 'a'] == null ? new Node() : cur.nexts[chs[i] - 'a'];
                cur = cur.nexts[chs[i] - 'a'];
            }
            cur.end = true;
        }
        return root;
    }

}
