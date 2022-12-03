package _01基础._15动态规划;

/**
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 * 只有一台洗咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 返回让所有咖啡杯变干净的最早完成时间
 * 三个参数: int[] arr、int a、 int b
 * Created by huangjunyi on 2022/9/4.
 */
public class DynamicProgramming08 {

    public static int processByRecursive(int[] arr, int a, int b) {
        return processByRecursive(arr, a, b, 0, 0);
    }

    /**
     * 暴力递归
     * @param arr 每个人喝完咖啡准备刷杯子的时间
     * @param a 洗杯子花费的时间
     * @param b 杯子挥发干净的时间
     * @param index 当前的杯子
     * @param washLine 咖啡机可以使用的时间
     * @return
     */
    private static int processByRecursive(int[] arr, int a, int b, int index, int washLine) {

        //已经到了最后一个杯子，手洗和挥发，选结束时间早的
        //但是如果选手洗，要看喝完咖啡和洗咖啡机能用的时间，哪个比较晚
        if (index == arr.length - 1) return Math.min(Math.max(arr[index], washLine) + a, arr[index] + b);

        //当前杯子选择使用洗咖啡机，洗完的时间
        int time1 = Math.max(arr[index], washLine) + a;
        //其他杯子也都干净的时间，因为选择了使用洗咖啡机，所以洗咖啡机能用的时间要更新
        int time2 = processByRecursive(arr, a, b, index + 1, time1);
        //两个时间PK
        int p1 = Math.max(time1, time2);

        //当前杯子选择挥发，挥发干净的时间
        int time3 = arr[index] + b;
        //其他杯子也都干净的时间
        int time4 = processByRecursive(arr, a, b, index + 1, washLine);
        //两个时间PK
        int p2 = Math.max(time3, time4);

        //两个时间取最小
        return Math.min(p1, p2);
    }

    /**
     * 动态规划
     * @param arr 每个人喝完咖啡准备刷杯子的时间
     * @param a 洗杯子花费的时间
     * @param b 杯子挥发干净的时间
     * @return
     */
    private static int processByDp(int[] arr, int a, int b) {
        /*
        应为暴力递归中是两个可变参数，所以改成动态规划是一张二维表
        但是第二个参数washLine有点难估计，属于业务限制模型
        所以就看所有杯子都选择用洗咖啡机洗，最大能冲到什么时间
        */
        int washLineLimit = 0;
        for (int i = 0; i < arr.length; i++) {
            washLineLimit = Math.max(washLineLimit, arr[i]) + a;
        }
        // dp[i][j] 从i号杯子开始，洗咖啡机j时间点可以用，所有杯子干净需要的时间
        int[][] dp = new int[arr.length][washLineLimit + 1];

        // 根据base case初始化dp表
        // 已经到了最后一个杯子，手洗和挥发，选结束时间早的
        // 但是如果选手洗，要看喝完咖啡和洗咖啡机能用的时间，哪个比较晚
        // if (index == arr.length - 1) return Math.min(Math.max(arr[index], washLine) + a, arr[index] + b);
        for (int i = 0; i <= washLineLimit; i++) {
            dp[arr.length - 1][i] = Math.min(Math.max(arr[arr.length - 1], i) + a, arr[arr.length - 1] + b);
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = 0; j <= washLineLimit; j++) {

                // 当前杯子选择用洗咖啡机洗，洗完的时间
                int p1 = Integer.MAX_VALUE;
                int time1 = Math.max(arr[i], j) + a;
                // 判断越界，如果当前杯子选择用洗咖啡机洗，洗完时间超过washLineLimit，是无效情况，跳过
                if (time1 > washLineLimit) continue;
                //其他杯子也都干净的时间，因为选择了使用洗咖啡机，所以洗咖啡机能用的时间要更新
                int time2 = dp[i + 1][time1];
                //两个时间PK
                p1 = Math.max(time1, time2);

                //当前杯子选择挥发，挥发干净的时间
                int time3 = arr[i] + b;
                //其他杯子也都干净的时间
                int time4 = dp[i + 1][j];
                //两个时间PK
                int p2 = Math.max(time3, time4);

                //两个时间取最小
                dp[i][j] = Math.min(p1, p2);
            }
        }
        // return processByRecursive(arr, a, b, 0, 0);
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1,1,5,5,7,10,12,12,12,12,12,12,15};
        int a=3;
        int b=10;
        System.out.println(processByRecursive(arr, a, b));
        System.out.println(processByDp(arr, a, b));
    }

}
