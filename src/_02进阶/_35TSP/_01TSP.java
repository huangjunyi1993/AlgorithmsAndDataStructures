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
public class _01TSP {

    /**
     * TSP暴力解
     * @param matrix
     * @param k
     * @return
     */
    public static int tsp(int[][] matrix, int k) {
        int N = matrix.length;
        /*
        用一个N长度的数组，里面的值0或1
        记录还有哪些城市没到到
        1代表还没到过，0代表到过
        每到一个城市，从这个城市出发去往下一个城市之前
        先把该城市在citys中的状态值修改为0
         */
        int[] citys = new int[N];
        for (int i = 0; i < citys.length; i++) {
            citys[i] = 1;
        }
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
    private static int process(int[][] matrix, int[] citys, int start, int home) {
        // 计算还有多少城市要去
        int cityNum = 0;
        for (int i = 0; i < citys.length; i++) {
            cityNum += citys[i] == 1 ? 1 : 0;
        }
        // 剩一座了，那就是当前这座城市，
        // 因为在去往下一座城市时，才把当前城市标为已去过，
        // 而现在刚到，还没有吧当前这座城市标记为已去过，
        // 所以剩一座没去，那么就是现在这座，
        // 那么剩下的路就是回老家了
        if (cityNum == 1) {
            return matrix[start][home];
        }
        // 枚举每个剩下没去的城市（除了老家以外），抓出最小答案
        int min = Integer.MAX_VALUE;
        citys[start] = 0;
        for (int i = 0; i < citys.length; i++) {
            if (citys[i] == 1) {
                /*
                matrix[start][i] + process(matrix, citys, i, home)
                matrix[start][i] 从当前城市start，到下一座城市i，走的距离
                process(matrix, citys, i, home) 从下一座城市i出发，走完剩下的距离，走过的距离
                然后跟之前的最小值min PK一下
                 */
                min = Math.min(min, matrix[start][i] + process(matrix, citys, i, home));
            }
        }
        // 深度遍历恢复现场
        citys[start] = 1;
        return min;
    }

}
