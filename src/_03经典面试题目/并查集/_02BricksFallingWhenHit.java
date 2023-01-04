package _03经典面试题目.并查集;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * 给定一个只含0和1二维数组matrix，第0行表示天花板。每个位置认为与上、下、左、右四个方向有粘性，比如：matrix =
 * 1 0 0 1 0
 * 1 0 0 1 1
 * 1 1 0 1 1
 * 1 0 0 0 0
 * 0 0 1 1 0
 * 注意到0行0列是1，然后能延伸出5个1的一片。同理0行3列也是1，也能延伸出5个1的一片。
 * 注意到4行2列是1，然后能延伸出2个1的一片。其中有两片1是粘在天花板上的，而4行2列延伸出来的这片，认为粘不住就掉下来了。
 * 在给定一个二维数组bomb，表示炸弹的位置，比如：
 * bomb =
 * 2 0
 * 1 3
 * 1 4
 * 0 3
 * 第一枚炮弹在2行0列，该处的1直接被打碎，然后会有2个1掉下来。
 * 第二枚炮弹在1行3列，该处的1直接被打碎，然后会有3个1掉下来。
 * 第三枚炮弹在1行4列，不会有1掉下来。
 * 第四枚炮弹在0行3列，该处的1直接被打碎，不会有1掉下来，因为这一片1只剩这一个了。
 * 根据matrix和bomb，返回结果[2,3,0,0]。
 *
 * Created by huangjunyi on 2022/10/15.
 */
public class _02BricksFallingWhenHit {

    private static class Dot{}

    private static class UnionSet {
        int[][] grid; // 已经把炮弹能打中的1都变成2的二维数组
        Dot[][] dots; // 每个值grid中的1，对应在dots中有一个对象表示
        int N; // 行数
        int M; // 列数
        int cellingAll; // 与天花板相连的点数
        HashSet<Dot> cellingSet; // 与天花板相连的集合的代表点
        HashMap<Dot, Dot> fatherMap; // 一个点在集合中的父节点
        HashMap<Dot, Integer> sizeMap; // 集合代表点对应结合的大小

        public UnionSet(int[][] grid) {
            init(grid); // 初始化并查集
            connect(grid); // 连接各个1为
        }

        private void connect(int[][] grid) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 上下左右相连
                    union(i, j, i - 1, j);
                    union(i, j, i, j + 1);
                    union(i, j, i + 1, j);
                    union(i, j, i, j - 1);
                }
            }
        }

        private void union(int i, int j, int i1, int j1) {
            if (valid(i, j) && valid(i1, j1)) {
                Dot father1 = find(i, j);
                Dot father2 = find(i1, j1);
                if (father1 == father2) return;
                Integer size1 = sizeMap.get(father1);
                Integer size2 = sizeMap.get(father2);
                boolean isCelling1 = cellingSet.contains(father1);
                boolean isCelling2 = cellingSet.contains(father2);
                if (size1 > size2) {
                    sizeMap.put(father1, size1 + size2);
                    fatherMap.put(father2, father1);
                    if (isCelling1 ^ isCelling2) {
                        cellingAll += isCelling1 ? size2 : size1;
                        cellingSet.add(father1);
                    }
                } else {
                    sizeMap.put(father2, size1 + size2);
                    fatherMap.put(father1, father2);
                    if (isCelling1 ^ isCelling2) {
                        cellingAll += isCelling1 ? size2 : size1;
                        cellingSet.add(father2);
                    }
                }
            }
        }

        private Dot find(int i, int j) {
            Dot dot = dots[i][j];
            LinkedList<Dot> stack = new LinkedList<>();
            while (fatherMap.get(dot) != dot) {
                stack.add(dot);
                dot = fatherMap.get(dot);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), dot);
            }
            return dot;
        }

        private boolean valid(int i, int j) {
            return i >= 0 && i < this.N && j >= 0 && j < M && grid[i][j] == 1;
        }

        private void init(int[][] grid) {
            this.grid = grid;
            this.N = grid.length;
            this.M = grid[0].length;
            dots = new Dot[N][M];
            cellingAll = 0;
            cellingSet = new HashSet<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1) {
                        Dot dot = new Dot();
                        dots[i][j] = dot;
                        fatherMap.put(dot, dot);
                        sizeMap.put(dot, 1);
                        if (i == 0) {
                            cellingSet.add(dot);
                            cellingAll++;
                        }
                    }
                }
            }
        }

        public int getCellingAll() {
            return this.cellingAll;
        }

        /**
         * 把炮弹指定的位置的2变为1，然后尝试与上下左右相连，
         * 返回相连后对比相连前增加的与天花板相连的1的数目
         *
         * @param row
         * @param col
         * @return
         */
        public int finger(int row, int col) {
            grid[row][col] = 1;
            Dot dot = new Dot();
            dots[row][col] = dot;
            fatherMap.put(dot, dot);
            sizeMap.put(dot, 1);
            if (row == 0) {
                cellingSet.add(dot);
                cellingAll += 1;
            }
            int preCellingAll = this.cellingAll;
            union(row, col, row - 1, col);
            union(row, col, row, col + 1);
            union(row, col, row + 1, col);
            union(row, col, row, col - 1);
            int curCellingAll = this.cellingAll;
            if (row == 0) {
                return curCellingAll - preCellingAll;
            } else {
                return curCellingAll == preCellingAll ? 0 : curCellingAll - preCellingAll - 1;
            }
        }

    }

    public static int[] hitBricks(int[][] grid, int[][] hits) {
        /*
        通过并查集求解
        先把grid数组中会被炮弹打中的1变成2，然后利用grid生成并查集
        并查集中把相连的1都连接好
        并且记录哪些集合与天花板相连
        以及与天花板相连或接近相连的点数

        然后逆序将炮弹会打中的2变成1
        再尝试与上下左右相连
        计算前后与天花板相连点数的差值
        就是该枚炮弹会击落的1的数目
         */
        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 1) grid[hits[i][0]][hits[i][1]] = 2;
        }
        UnionSet unionSet = new UnionSet(grid);
        int[] res = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) res[i] = unionSet.finger(hits[i][0], hits[i][1]);
        }
        return res;
    }

}
