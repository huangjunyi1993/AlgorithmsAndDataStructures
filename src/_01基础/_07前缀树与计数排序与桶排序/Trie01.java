package _01基础._07前缀树与计数排序与桶排序;

/**
 * 前缀树
 * Created by huangjunyi on 2022/11/20.
 */
public class Trie01 {
    private static class Node {
        int pass; // 有多少个字符串经过该节点
        int end; // 有多少个字符串以该节点结尾
        Node[] paths; // 字符路径

        public Node() {
            pass = 0;
            end = 0;
            paths = new Node[26];
        }
    }

    private Node root;

    public Trie01() {
        root = new Node();
    }

    /**
     * 往前缀树中插入字符串
     * @param str
     */
    public void insert(String str) {
        if (str == null) return;
        char[] chs = str.toCharArray();
        Node node = this.root;
        node.pass++;
        for (int i = 0; i < chs.length; i++) {
            // 如果没有路，先建出路来
            if (node.paths[chs[i] - 'a'] == null) node.paths[chs[i] - 'a'] = new Node();
            // 沿途pass++
            node.paths[chs[i] - 'a'].pass++;
            node = node.paths[chs[i] - 'a'];
        }
        // 结尾字符路径连接的节点，end++
        node.end++;
    }

    public void delete(String str) {
        if (search(str) == 0) return;
        char[] chs = str.toCharArray();
        Node node = this.root;
        node.pass--;
        for (int i = 0; i < chs.length; i++) {
            // 如果沿途发现有节点pass减到0，直接置为null，返回
            if (--node.paths[chs[i] - 'a'].pass == 0) node.paths[chs[i] - 'a'] = null;
            node = node.paths[chs[i] - 'a'];
        }
        // 结尾字符路径连接的节点，end--
        node.end--;
    }

    /**
     * 一个字符串在前缀树中出现了几次
     * @param str
     * @return
     */
    public int search(String str) {
        if (str == null) return 0;
        char[] chs = str.toCharArray();
        Node node = this.root;
        for (int i = 0; i < chs.length; i++) {
            // 根据字符路径，顺着往下走，没路了就返回
            if (node.paths[chs[i] - 'a'] == null) return 0;
            else node = node.paths[chs[i] - 'a'];
        }
        // 到达终点，返回end
        return node.end;
    }

    /**
     * 一个前缀串在前缀树中出现了几次
     * @param prefix
     * @return
     */
    public int prefixNumber(String prefix) {
        if (prefix == null) return 0;
        char[] chs = prefix.toCharArray();
        Node node = this.root;
        for (int i = 0; i < chs.length; i++) {
            // 根据字符路径，顺着往下走，没路了就返回
            if (node.paths[chs[i] - 'a'] == null) return 0;
            else node = node.paths[chs[i] - 'a'];
        }
        return node.pass;
    }

}
