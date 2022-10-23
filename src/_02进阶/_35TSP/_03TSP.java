package _02进阶._35TSP;

/**
 * TPS问题
 * 有N个城市，任何两个城市之间的都有距离，任何一座城市到自己的
 * 距离都是0。所有点到点的距离都存在一个N*N的二维数组matrix里，也就是
 * 整张图有邻接矩阵表示。现要求一旅行商从k城市出发必须经过每一个城市且只在一个城市逗留一次，
 * 最后回到出发的k城，返回总距离最短的路的距离。
 * 参数给定一个matrix，给定k。
 *
 * Created by huangjunyi on 2022/10/16.
 */
public class _03TSP {

    /**
     * TSP暴力解
     * @param matrix
     * @param k
     * @return
     */
    public static int tsp(int[][] matrix, int k) {
        int N = matrix.length;
        /*
        在02中的基础上，添加记忆化搜索的优化
         */
        int citys = (1 << N) - 1;

        /*
        一个记忆表
        有 1 << N 行，代表这么多中状态
        有 N 列，表示N个出发点
        先初始化记忆表中所有的值为-1
         */
        int[][] dp = new int[1 << N][N];
        for (int i = 0; i < (i << N); i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        /*
        递归枚举每个可能路径，租后得出最短路径的长度
        从k出发，最终回到k点
         */
        return process(matrix, citys, k, k, dp);
    }

    /**
     * 递归每个出发点，最终回到老家
     * @param matrix 城市距离表
     * @param citys 还要去的城市
     * @param start 当前出发点
     * @param home 老家
     * @param dp
     * @return 走过的路的距离
     */
    private static int process(int[][] matrix, int citys, int start, int home, int[][] dp) {

        // 记忆表中存在有效记录，直接返回
        if (dp[citys][start] != -1) return dp[citys][start];

        /*
         一个数与上自身的取反加1，就得出二进制中最右侧1代表的值
         这里如果条件成立，代表citys中只剩一座城市，就是start本身
         那么就是直接从start回老家
          */
        if (citys == (citys & (~citys + 1))) {
            dp[citys][start] = matrix[start][home];
            return dp[citys][start];
        }
        int min = Integer.MAX_VALUE;
        // 把citys中start对应的状态位修改为0，表示start已经去过了，即将去往下一城市
        citys &= (~(1 << start));
        for (int i = 0; i < matrix.length; i++) {
            if ((citys & (1 << i)) != 0 && i != start) {
                min = Math.min(min, matrix[start][i] + process(matrix, citys, i, home, dp));
            }
        }

        // 深度优先遍历恢复现场，把start状态位还原
        citys |= (1 << start);

        dp[citys][start] = min;
        return dp[citys][start];
    }

}
