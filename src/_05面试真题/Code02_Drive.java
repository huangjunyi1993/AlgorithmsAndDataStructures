package _05面试真题;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 司机调度 时间限制： 3000MS 内存限制： 589824KB 题目描述：
 * 正值下班高峰时期，现有可载客司机数2N人，调度中心将调度相关司机服务A、B两个出行高峰区域。 第 i 个司机前往服务A区域可得收入为
 * income[i][0]，前往服务B区域可得收入为 income[i][1]。
 * 返回将每位司机调度完成服务后，所有司机总可得的最高收入金额，要求每个区域都有 N 位司机服务。 输入描述 10 20 20 40 # 如上：
 * 第一个司机服务 A 区域，收入为 10元 第一个司机服务 B 区域，收入为 20元 第二个司机服务 A 区域，收入为 20元 第二个司机服务 B
 * 区域，收入为 40元 输入参数以 '#' 结束输入 输出描述 最高总收入为 10 + 40= 50，每个区域都有一半司机服务
 * 参数及相关数据异常请输出：error 样例输入 : 10 30 100 200 150 50 60 20 # 样例输出 440
 */
public class Code02_Drive {

    // 给定一个N*2的正数矩阵matix，N一定是偶数，可以保证。
    // 一定要让A区域分到N/2个司机，让B区域也分到N/2个司机
    // 返回最大的总收益
    public static int maxMoney(int[][] matrix) {
        return process(matrix, 0, matrix.length / 2);
    }

    /**
     * 返回从i号司机开始往后的最后受益
     * @param matrix
     * @param i
     * @param aRest 剩余要去a的指标
     * @return
     */
    private static int process(int[][] matrix, int i, int aRest) {
        if (i == matrix.length) {
            return aRest == 0 ? 0 : -1;
        }
        int goToA = -1;
        if (aRest != 0) {
            // 能去a，收一个去a的答案
            goToA = matrix[i][0] + process(matrix, i + 1, aRest - 1);
        }
        int goToB = -1;
        int toANum = (matrix.length / 2) - aRest; // 去了a的人数
        int toBNum = i - toANum; // 去了b的人数
        if (toBNum < matrix.length / 2) {
            // 能去b，收一个去b的答案
            goToB = matrix[i][1] + process(matrix, i + 1, aRest);
        }
        // 两答案中取最大
        return Math.max(goToA, goToB);
    }
}
