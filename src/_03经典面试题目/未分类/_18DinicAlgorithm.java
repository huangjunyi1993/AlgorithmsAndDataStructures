package _03经典面试题目.未分类;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Dinic算法解决最大网络流问题
 * Created by huangjunyi on 2022/12/11.
 */
public class _18DinicAlgorithm {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int cases = (int) in.nval;
            for (int i = 1; i <= cases; i++) {
                in.nextToken();
                int n = (int) in.nval;
                in.nextToken();
                int s = (int) in.nval;
                in.nextToken();
                int t = (int) in.nval;
                in.nextToken();
                int m = (int) in.nval;
                Dinic dinic = new Dinic(n);
                for (int j = 0; j < m; j++) {
                    in.nextToken();
                    int from = (int) in.nval;
                    in.nextToken();
                    int to = (int) in.nval;
                    in.nextToken();
                    int weight = (int) in.nval;
                    dinic.addEdge(from, to, weight);
                    dinic.addEdge(to, from, weight);
                }
                int ans = dinic.maxFlow(s, t);
                out.println("Case " + i + ": " + ans);
                out.flush();
            }
        }
    }

    /**
     * 边
     */
    public static class Edge {
        public int from; // 从哪
        public int to; // 到哪
        public int available; // 权重（承载水量）

        public Edge(int a, int b, int c) {
            from = a;
            to = b;
            available = c;
        }
    }

    public static class Dinic {
        private int N; // 边数
        private ArrayList<ArrayList<Integer>> nexts; // 城市编号 => [能走的边的边号]
        private ArrayList<Edge> edges; // 边号 => 边
        private int[] depth; // 高度数组
        private int[] cur; // cur数组，边号 => 从哪条边开始尝试，比如cur[s]=2，表示s这个点还能走nexts.get(s)中大于等于2的边，小于的是别的支路走过的，不能重复往里灌水

        public Dinic(int nums) {
            N = nums + 1;
            nexts = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                nexts.add(new ArrayList<>());
            }
            edges = new ArrayList<>();
            depth = new int[N];
            cur = new int[N];
        }

        public void addEdge(int u, int v, int r) {
            int m = edges.size();
            // 加正向边
            edges.add(new Edge(u, v, r));
            nexts.get(u).add(m);
            // 加反向边
            edges.add(new Edge(v, u, 0));
            nexts.get(v).add(m + 1);
        }

        /**
         * 算法主流程
         * @param s 起始点
         * @param t 目标点
         * @return 最大灌水量
         */
        public int maxFlow(int s, int t) {
            int flow = 0;
            while (bfs(s, t)) { // s -> t，还有路能走？
                Arrays.fill(cur, 0); // 还原cur数组
                flow += dfs(s, t, Integer.MAX_VALUE); // 走 =>
                Arrays.fill(depth, 0); // 还原高度数组
            }
            return flow;
        }

        /**
         * 宽度优先遍历，建立高度数组
         */
        private boolean bfs(int s, int t) {
            LinkedList<Integer> queue = new LinkedList<>();
            queue.addFirst(s);
            boolean[] visited = new boolean[N];
            visited[s] = true;
            while (!queue.isEmpty()) {
                int u = queue.pollLast();
                for (int i = 0; i < nexts.get(u).size(); i++) {
                    Edge e = edges.get(nexts.get(u).get(i));
                    int v = e.to;
                    if (!visited[v] && e.available > 0) {
                        visited[v] = true;
                        // 子节点高度 = 父节点高度 + 1
                        depth[v] = depth[u] + 1;
                        if (v == t) {
                            break;
                        }
                        queue.addFirst(v);
                    }
                }
            }
            return visited[t];
        }

        /**
         * 当前来到了s点，s为可变参数，最终目标是t，t固定参数，r收到的任务
         * 收集到的流，作为结果返回，ans <= r
         * @param s 当前来到了s点
         * @param t 最终目标是t
         * @param r 收到的任务
         * @return 收集到的流
         */
        private int dfs(int s, int t, int r) {
            if (s == t || r == 0) {
                return r;
            }
            int f = 0;
            int flow = 0;
            // s点从哪条边开始试 -> cur[s]
            for (; cur[s] < nexts.get(s).size(); cur[s]++) { // cur[s]++，别的支路也走到当前节点，就接着往下尝试
                int ei = nexts.get(s).get(cur[s]);
                Edge e = edges.get(ei); // 正向边
                Edge o = edges.get(ei ^ 1); // 方向边
                if (depth[e.to] == depth[s] + 1 // 高度更大的才走
                        && (f = dfs(e.to, t, Math.min(e.available, r))) != 0) {
                    e.available -= f; // 正向边扣减
                    o.available += f; // 反向边增加
                    flow += f; // 收集流
                    r -= f; // 要完成的任务扣减
                    if (r <= 0) {
                        // 任务都完成了，返回
                        break;
                    }
                }
            }
            return flow;
        }
    }
}
