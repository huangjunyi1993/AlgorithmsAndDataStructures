package _01基础._15动态规划;

/**
 * 给定一个正整数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小结合的累加和
 * Created by huangjunyi on 2022/11/30.
 */
public class DynamicProgramming17 {
    /**
     * 暴力递归
     */
    public static int smallSum01(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    /**
     * 从index位置开始，在数组arr中只有挑选数字，必须挑满picks个，
     * 返回最接近rest但是不超过rest的集合累加和
     */
    private static int process(int[] arr, int index, int picks, int rest) {
        // base case：没数了，看调完没，没挑完要返回-1，表示无效
        if (index == arr.length) return picks == 0 ? 0 : -1;
        // index的数不要
        int p1 = process(arr, index + 1, picks, rest);
        // index的数要
        int p2 = -1;
        int next = -1;
        if (arr[index] <= rest) next = process(arr, index + 1, picks - 1, rest - arr[index]);
        if (next != -1) p2 = arr[index] + next;
        // 两个累加和PK一下
        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     */
    public static int smallSum02(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        /*
        可变参数：index picks rest
        index：0 ~ arr.length
        picks: 偶数 0 ~ arr.length / 2 奇数 0 ~ arr.length / 2 + 1
        rest: 0 ~ sum / 2
        dp[index][picks][rest]：从index位置开始，在数组arr中只有挑选数字，必须挑满picks个，最接近rest但是不超过rest的集合累加和
         */
        int[][][] dp = new int[arr.length + 1][(arr.length + 1) / 2 + 1][sum / 2 + 1];

        /*
        根据base case 初始化dp表
        // base case：没数了，看调完没，没挑完要返回-1，表示无效
        if (index == arr.length) return picks == 0 ? 0 : -1;
         */
        for (int index = 0; index <= arr.length; index++) {
            for (int picks = 0; picks <= (arr.length + 1) / 2; picks++) {
                for (int rest = 0; rest <= sum / 2; rest++) {
                    dp[index][picks][rest] = -1;
                }
            }
        }
        for (int rest = 0; rest <= sum / 2; rest++) {
            dp[arr.length][0][rest] = 0;
        }

        /*
        暴力递归外层与内层的依赖关系：
        // index的数不要
        int p1 = process(arr, index + 1, picks, rest);
        // index的数要
        int p2 = -1;
        int next = -1;
        if (arr[index] <= rest) next = process(arr, index + 1, picks - 1, rest - arr[index]);
        if (next != -1) p2 = arr[index] + next;
        // 两个累加和PK一下
        return Math.max(p1, p2);

        index层依赖于index+1层，所以从下往上填
         */
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int picks = 0; picks <= (arr.length + 1) / 2; picks++) {
                for (int rest = 0; rest <= sum / 2; rest++) {
                    // index的数不要
                    int p1 = dp[index + 1][picks][rest];
                    // index的数要
                    int p2 = -1;
                    int next = -1;
                    if (arr[index] <= rest && picks > 0) next = dp[index + 1][picks - 1][rest - arr[index]];
                    if (next != -1) p2 = arr[index] + next;
                    // 两个累加和PK一下
                    dp[index][picks][rest] = Math.max(p1, p2);
                }
            }
        }
        // 根据暴力递归的最外层，确定返回值
        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum / 2];
        } else {
            return Math.max(dp[0][arr.length / 2][sum / 2], dp[0][arr.length / 2 + 1][sum / 2]);
        }
    }

}
