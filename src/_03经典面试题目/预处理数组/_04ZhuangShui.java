package _03经典面试题目.预处理数组;

/**
 * 装水问题
 *
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
 * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水
 * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 * Created by huangjunyi on 2022/9/18.
 */
public class _04ZhuangShui {

    public static int water(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        /*
        用两个预处理数组求解
        lMax表示记录从左往右最大值的数组
        rMax表示记录从右往左最大值的数组
        然后遍历原数组arr，没到一个位置都可以根据辅助数组得到两侧的最大值，
        然后最大值较小的那一侧就是瓶颈，根据该侧的最大值进行结算

        但是可以通过左右指针简化，省掉左右辅助数组
        用lMax记录l指针当前位置开始左侧的最大值
        用rMax记录r指针当前位置开始右侧的最大值
        那一侧的最大值较小，就结算那一侧的指针
        比如lMax较小，结算l位置的数量，
        因为此时右侧的最大值再小都不会表rMax小，所以lMax成为了l指针位置的水量的瓶颈，
        所以可以根据lMax结算l位置的水量

        然后每次更新l指针或者r指针位置时，都要看是否要更新lMax或者rMax
         */
        int len = arr.length;
        int lMax = arr[0];
        int rMax = arr[len - 1];
        int l = 1;
        int r = len - 2;
        int water = 0;
        while (l <= r) {
            if (lMax <= rMax) {
                water += Math.max(lMax - arr[l], 0);
                lMax = Math.max(lMax, arr[l]);
                l++;
            } else {
                water += Math.max(rMax - arr[r], 0);
                rMax = Math.max(rMax, arr[r]);
                r--;
            }
        }
        return water;
    }

}
