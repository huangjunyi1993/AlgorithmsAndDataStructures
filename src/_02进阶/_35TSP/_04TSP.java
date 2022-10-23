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
public class _04TSP {

    /**
     * TSP暴力解
     * @param matrix
     * @param k
     * @return
     */
    public static int tsp(int[][] matrix, int k) {
        int N = matrix.length;
        /*
        在02中的基础上，使用动态规划进行优化
         */
        int home = k;

        /*
        一个dp表
        有 1 << N 行，代表这么多中状态
        有 N 列，表示N个出发点
         */
        int[][] dp = new int[1 << N][N];

        for (int citys = 0; citys < (1 << N); citys++) {
            for (int start = 0; start < N; start++) {
                // citys的start状态位为1，才有必要处理，否则这个格子是个无效的格子
                if ((citys & (1 << start)) != 0) {
                    /*
                    一个数与上自身的取反加1，就得出二进制中最右侧1代表的值
                    这里如果条件成立，代表citys中只剩一座城市，就是start本身
                    那么就是直接从start回老家
                    */
                    if (citys == (citys & (~citys + 1))) {
                        dp[citys][start] = matrix[start][home];
                    } else {
                        int min = Integer.MAX_VALUE;

                        // 把citys中start对应的状态位修改为0，表示start已经去过了，即将去往下一城市
                        int curCitys = citys & (~(1 << start));

                        for (int i = 0; i < matrix.length; i++) {
                            if ((curCitys & (1 << i)) != 0 && i != start) {
                                min = Math.min(min, matrix[start][i] + dp[curCitys][i]);
                            }
                        }

                        dp[citys][start] = min;
                    }
                }
            }
        }

        return dp[(1 << N) - 1][0];
    }

}
