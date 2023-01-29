package _05面试真题;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 来自网易
 * map[i][j] == 0，代表(i,j)是海洋，渡过的话代价是2
 * map[i][j] == 1，代表(i,j)是陆地，渡过的话代价是1
 * map[i][j] == 2，代表(i,j)是障碍，无法渡过
 * 每一步上、下、左、右都能走，返回从左上角走到右下角最小代价是多少，如果无法到达返回-1
 * Created by huangjunyi on 2023/1/18.
 */
public class Code09_WalkToEnd {

    private static class Cell {
        int x; // 格子所在行
        int y; // 格子所在列
        int cost; // 从起点走到当前格子需要的最小代价

        public Cell(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static int minCost(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) return 0;
        if (map[0][0] == 2 || map[map.length - 1][map[0].length - 1] == 2) return -1;
        /*
        用一个小跟堆，记录下一步可以走的格子，用一个去重表记录已经走过的格子
        小跟堆根据代价从小到大排序
        每次从小跟堆弹出一个格子，是当前最小代价的格子，然后上下左右搜索能走的格子
        弹出的格子时右下角，代表走到终点，返回格子的代价
         */
        PriorityQueue<Cell> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        heap.add(new Cell(0, 0, map[0][0] == 0 ? 2 : 1));
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[0][0] = true;
        while (!heap.isEmpty()) {
            Cell curCell = heap.poll();
            int curX = curCell.x;
            int curY = curCell.y;
            int curCost = curCell.cost;
            if (curX == map.length - 1 && curY == map[0].length - 1) return curCost;
            if (curX > 0 && !visited[curX - 1][curY]) heap.add(new Cell(curX - 1, curY, curCost + map[curX - 1][curY] == 0 ? 2 : 1));
            if (curY > 0 && !visited[curX][curY - 1]) heap.add(new Cell(curX, curY - 1, curCost + map[curX][curY- 1] == 0 ? 2 : 1));
            if (curX < map.length && !visited[curX + 1][curY]) heap.add(new Cell(curX + 1, curY, curCost + map[curX + 1][curY] == 0 ? 2 : 1));
            if (curY < map[0].length && !visited[curX][curY + 1]) heap.add(new Cell(curX, curY + 1, curCost + map[curX][curY + 1] == 0 ? 2 : 1));
        }
        return -1;
    }

}
