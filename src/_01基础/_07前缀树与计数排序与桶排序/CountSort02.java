package _01基础._07前缀树与计数排序与桶排序;

/**
 * 桶排序：计数排序的一种使用
 */
public class CountSort02 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        //取得当前数组最大值的位数
        int maxBit = getMaxBit(arr);

        //创建一个辅助数组
        int[] help = new int[arr.length];

        for (int i = 1; i <= maxBit; i++) {

            int[] count = new int[10];

            for (int j = 0; j < arr.length; j++) {
                //获取数组中每个值指定位上的数
                int bitNum = getBitNum(arr[j], i);
                //count数组此时记录指定数位上不同数字出现的次数
                count[bitNum]++;
            }

            for (int j = 1; j < count.length; j++) {
                //count数组变成指定数位上数字小于等于j的数的个数有几个
                count[j] += count[j - 1];
            }

            //根据count数组的记录，从原数组的最后一位往前遍历，放入help数组中的指定位置
            for (int j = arr.length - 1; j >= 0; j--) {
                int bitNum = getBitNum(arr[j], i);
                //count[bitNum]记录了当前数位i上数字小于等于bitNum的数，出现的次数
                //所以arr[j]放入到help数组中count[bitNum]-1的位置
                help[count[bitNum] - 1] = arr[j];
                //处理过了，减一，下次遇到指定数位上数字相同的数，就会放到前一个位置
                count[bitNum]--;
            }

            //help数组拷贝回原数组
            for (int j = 0; j < help.length; j++) {
                arr[j] = help[j];
            }

        }

    }

    private static int getBitNum(int num, int bit) {
        for (int i = 0; i < bit - 1; i++) {
            num /= 10;
        }
        int help = (num / 10) * 10;
        return num - help;
    }

    /**
     * 计算数组中最大的数的最大位数
     * @param arr
     * @return
     */
    private static int getMaxBit(int[] arr) {
        //找出数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }

        // 计算最大位数
        int res = 0;
        while (max != 0) {
            max /= 10;
            res++;
        }

        return res;
    }

}
