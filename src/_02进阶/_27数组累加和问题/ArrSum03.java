package _02进阶._27数组累加和问题;

/**
 * 给定一个数组，返回累加和小于等于k的所有子数组中的最大长度子数组的长度
 * Created by huangjunyi on 2022/9/11.
 */
public class ArrSum03 {

    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        // minSum[i] 以i开头，往右扩出的累加和最小的子数组 的累加和
        int[] minSum = new int[arr.length];
        // minSumEnd[i] 以i开头，往右扩出的累加和最小的子数组 的右边界
        int[] minSumEnd = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        // 从右往左遍历arr，初始化minSum和minSumEmd
        for (int i = arr.length - 2; i >= 0; i--) {
            // i + 1位置的累加和小于0，可以往右扩
            if (minSum[i + 1] <= 0) {
                minSum[i] =  arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            }
            // 否则不往右扩，自己单独算作minSum[i]
            else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int end = 0; // 扩不到的那个位置
        int sum = 0; // 当前窗口累加和
        int len = 0; // 抓到的最大长度
        for (int start = 0; start < arr.length; start++) {
            // 根据minSum和minSumEnd的信息把窗口右边界往右推
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            // 推到不能再推，抓一个答案PK
            len = Math.max(len, end - start);
            // 如果窗口还有长度，则还可以缩，减去将要出窗口的数
            if (start < end) sum -= arr[start];
            // 创建每长度了，在左边界++之前，右边界先++
            else end = start + 1;
        }
        return len;
    }

}
