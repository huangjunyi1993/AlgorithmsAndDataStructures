package _03经典面试题目.未分类;

/**
 * 腾讯原题
 *
 * 给定整数power，给定一个数组arr，给定一个数组reverse。含义如下：
 * arr的长度一定是2的power次方，reverse中的每个值一定都在0~power范围。
 * 例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}
 * 任何一个在前的数字可以和任何一个在后的数组，构成一对数。可能是升序关系、相等关系或者降序关系。
 * 比如arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。
 * 接下来根据reverse对arr进行调整：
 * reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [3,1,4,2]，此时有3个逆序对。
 * reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [1,3,2,4]，此时有1个逆序对
 * reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [1,3,2,4]，此时有1个逆序对。
 * reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，那么arr变成
 * [4,2,3,1]，此时有4个逆序对。
 * 所以返回[3,1,1,4]，表示每次调整之后的逆序对数量。
 *
 * 输入数据状况：
 * power的范围[0,20]
 * arr长度范围[1,10的7次方]
 * reverse长度范围[1,10的6次方]
 *
 * Created by huangjunyi on 2022/10/16.
 * */
public class _16MergeRecord {

    public static int[] reversePair(int[] arr, int[] reverse, int power) {
        int[] reverseArr = copyAndReverse(arr);
        /*
        先生成降序对个数数组downRecode和升序对个数数组upRecode

        假如power为3

        那么：
        downRecode和upRecode长度为4

        downRecode[0]表示 2^0 = 1 arr数组中 1个数为1组 组内中间切一刀 左右各挑一个数 降序对个数
        downRecode[1]表示 2^1 = 2 arr数组中 2个数为1组 组内中间切一刀 左右各挑一个数 降序对个数
        downRecode[2]表示 2^2 = 4 arr数组中 4个数为1组 组内中间切一刀 左右各挑一个数 降序对个数
        downRecode[3]表示 2^3 = 8 arr数组中 8个数为1组 组内中间切一刀 左右各挑一个数 降序对个数

        upRecode[0]表示 2^0 = 1 arr数组中 1个数为1组 组内中间切一刀 左右各挑一个数 升序对个数
        upRecode[1]表示 2^1 = 2 arr数组中 2个数为1组 组内中间切一刀 左右各挑一个数 升序对个数
        upRecode[2]表示 2^2 = 4 arr数组中 4个数为1组 组内中间切一刀 左右各挑一个数 升序对个数
        upRecode[3]表示 2^3 = 8 arr数组中 8个数为1组 组内中间切一刀 左右各挑一个数 升序对个数

        然后遍历reverse数组
        假如reverse[i] = 2;
        那么downRecode和upRecode中，下标1~2范围的元素，交换

        然后累加downRecode中的所有值，记录到res[i]中

        最后返回res
         */
        int[] downRecode = new int[power + 1];
        int[] upRecode = new int[power + 1];
        process(arr, 0, arr.length - 1, power, downRecode);
        process(reverseArr, 0 ,reverseArr.length - 1, power, upRecode);
        int[] res = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {
            for (int j = 1; j < reverse[i]; j++) {
                int tmp = downRecode[j];
                downRecode[j] = upRecode[j];
                upRecode[j] = tmp;
            }
            for (int j = 0; j < downRecode.length; j++) {
                res[i] += downRecode[j];
            }
        }
        return res;
    }

    private static void process(int[] arr, int l, int r, int power, int[] recode) {
        if (l >= r) return;
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid, power - 1, recode);
        process(arr, mid + 1, r, power - 1, recode);
        recode[power] += merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int res = 0;
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            res += arr[p1] > arr[p2] ? mid - p1 + 1 : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= r) help[i++] = arr[p2++];
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return res;
    }

    private static int[] copyAndReverse(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        int l = 0;
        int r = copyArr.length - 1;
        while (l < r) {
            int tmp = copyArr[l];
            copyArr[l] = copyArr[r];
            copyArr[r] = tmp;
            l++;
            r--;
        }
        return copyArr;
    }

}
