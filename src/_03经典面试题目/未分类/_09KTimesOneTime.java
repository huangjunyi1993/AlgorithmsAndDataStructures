package _03经典面试题目.未分类;

/**
 * 给定一个数组arr，已知除了一种数只出现1次之外，剩下所有的数都出现了k次，如何使用O(1)的额外空间，找到这个数。
 *
 * Created by huangjunyi on 2022/10/6.
 */
public class _09KTimesOneTime {

    public static int getOneTimeNum(int[] arr, int k) {
        // 用一个数组代替k进制数，数组中每个数代表一个数位
        int[] kHexNumSum = new int[32];
        /*
        把arr中所有数转成k进制数，累加到kHexNumSum中
        然后kHexNumSum中的代表每个数位的数都模k
        然后把kHexNumSum转回十进制，得到的数
        就是出现一次的数

        因为其他数都出现k次，所以kHexNumSum中代表每个数位的数，模k后都会被消掉
         */
        for (int i = 0; i < arr.length; i++) {
            setEor(arr[i], kHexNumSum, k);
        }
        return getDecimalNumber(kHexNumSum, k);
    }

    /**
     * k进制数转十进制
     * @param kHexNumSum
     * @param k
     * @return
     */
    private static int getDecimalNumber(int[] kHexNumSum, int k) {
        int res = 0;
        for (int i = kHexNumSum.length - 1; i >= 0; i--) {
            res = res * k + kHexNumSum[i];
        }
        return res;
    }

    /**
     * 把当前value转成k进制数，累积到kHexNumSum中
     * @param value
     * @param kHexNumSum
     * @param k
     */
    private static void setEor(int value, int[] kHexNumSum, int k) {
        int[] kHexNum = getKHexadecimalNumber(value, k);
        for (int i = 0; i < kHexNum.length; i++) {
            kHexNumSum[i] = (kHexNumSum[i] + kHexNum[i]) % k;
        }
    }

    /**
     * 十进制数转k进制数
     * @param value
     * @param k
     * @return
     */
    private static int[] getKHexadecimalNumber(int value, int k) {
        int[] kHexNum = new int[32];
        int index = 0;
        while (value != 0) {
            kHexNum[index++] = value % k;
            value /= k;
        }
        return kHexNum;
    }

}
