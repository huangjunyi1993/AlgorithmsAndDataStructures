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
public class _02TSP {

    /**
     * TSP暴力解
     * @param matrix
     * @param k
     * @return
     */
    public static int tsp(int[][] matrix, int k) {
        int N = matrix.length;
        /*
        在01中，使用一个N长度的数组记录城市状态信息如[0,1,1,0,1]
        这里可以简化为用二进制位表示
        citys有int数组类型简化为int类型

        比如有5座城市，那么citys最开始应是11111
        那么1左移5位得出100000，然后减一，就得11111
         */
        int citys = (1 << N) - 1;
        /*
        递归枚举每个可能路径，租后得出最短路径的长度
        从k出发，最终回到k点
         */
        return process(matrix, citys, k, k);
    }

    /**
     * 递归每个出发点，最终回到老家
     * @param matrix 城市距离表
     * @param citys 还要去的城市
     * @param start 当前出发点
     * @param home 老家
     * @return 走过的路的距离
     */
    private static int process(int[][] matrix, int citys, int start, int home) {
        /*
         一个数与上自身的取反加1，就得出二进制中最右侧1代表的值
         这里如果条件成立，代表citys中只剩一座城市，就是start本身
         那么就是直接从start回老家
          */
        if (citys == (citys & (~citys + 1))) {
            return matrix[start][home];
        }
        int min = Integer.MAX_VALUE;
        // 把citys中start对应的状态位修改为0，表示start已经去过了，即将去往下一城市
        citys &= (~(1 << start));
        for (int i = 0; i < matrix.length; i++) {
            if ((citys & (1 << i)) != 0) {
                min = Math.min(min, matrix[start][i] + process(matrix, citys, i, home));
            }
        }

        // 深度优先遍历恢复现场，把start状态位还原
        citys |= (1 << start);
        return min;
    }

}
