package _03经典面试题目.未分类;

/**
 * 给定一个数组arr，长度为N，arr中的值不是0就是1
 * arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+2栈灯的状态
 *
 * 问题一：
 * 如果N栈灯排成一条直线,请问最少按下多少次开关,能让灯都亮起来
 * 排成一条直线说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 *
 * 问题二：
 * 如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * 排成一个圈说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 *
 * Created by huangjunyi on 2022/12/26.
 */
public class _19LightProblem {

    // 问题1
    public static int noLoop(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0] ^ 1;
        if (arr.length == 2) return arr[0] == arr[1] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        // 0位置开关，不按，走一趟
        int p1 = processNoLoop(arr, 2, arr[0], arr[1]);
        // 0位置开关，按，走一趟
        int p2 = processNoLoop(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) p2++;
        return Math.min(p1, p2);
    }

    /**
     * 下一个开关的位置是nextIndex，当前来到第nextIndex-1个开关，返回让nextIndex-1后续所有灯亮按的最少开关数
     * @param arr
     * @param nextIndex 下一个开关的位置
     * @param preStatus 前一个灯的状态
     * @param curStatus 当前等状态（nextIndex - 1）
     * @return
     */
    private static int processNoLoop(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) {
            return preStatus == curStatus ? curStatus ^ 1 : Integer.MAX_VALUE;
        }
        if (preStatus == 0) { // 前一个开关是关的，当前位置开关必须按下，否则再也没机会救活
            int res = processNoLoop(arr, nextIndex + 1, curStatus ^ 1, arr[nextIndex] ^ 1);
            return res == Integer.MAX_VALUE ? Integer.MAX_VALUE : res + 1;
        } else { // 前一个开关是开的，当前位置不能按，否则弄灭了，后面也没得救活了
            return processNoLoop(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }

    public static int loop(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0] ^ 1;
        if (arr.length == 2) return arr[0] == arr[1] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        if (arr.length == 3) return arr[0] == arr[1] && arr[1] == arr[2] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        // 因为0号位开关，不是必须被点亮（可以被最后一个开关救活），
        // 所以不满足递归函数的语义（前一个灯如果是灭的，必须救活），
        // 所以递归的当前位置从2（nextIndex==3）开始
        // 0不按，1不按
        int p1 = processLoop(arr, 3, arr[1], arr[2], arr[0], arr[arr.length - 1]);
        // 0按，1不按
        int p2 = processLoop(arr, 3, arr[1] ^ 1, arr[2], arr[0] ^ 1, arr[arr.length - 1] ^ 1);
        // 0不按，1按
        int p3 = processLoop(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[0] ^ 1, arr[arr.length - 1]);
        // 0按，1按
        int p4 = processLoop(arr, 3, arr[1], arr[2] ^ 1, arr[0], arr[arr.length - 1] ^ 1);
        if (p2 != Integer.MAX_VALUE) p2++;
        if (p3 != Integer.MAX_VALUE) p3++;
        if (p4 != Integer.MAX_VALUE) p4 += 2;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    /**
     *
     * @param arr
     * @param nextIndex
     * @param preStatus
     * @param curStatus
     * @param firstStatus 第一个灯的状态
     * @param endStatus 最后一个灯的状态
     * @return
     */
    private static int processLoop(int[] arr, int nextIndex,
                                   int preStatus, int curStatus,
                                   int firstStatus, int endStatus) {
        if (nextIndex == arr.length) return preStatus == curStatus && curStatus == firstStatus ? curStatus ^ 1 : Integer.MAX_VALUE;
        if (preStatus == 0) {
            int res = processLoop(arr,
                    nextIndex + 1,
                    curStatus ^ 1,
                    // 如果下一个位置是最后一个开关，curStatus就不可以从arr中取，因为0位置开关按下会改变，要取endStatus
                    nextIndex == arr.length - 1 ? endStatus ^ 1 : arr[nextIndex] ^ 1,
                    firstStatus,
                    // 如果下一个位置是最后一个开关，当前开关按下，会改变endStatus
                    nextIndex == arr.length - 1 ? endStatus ^ 1 : endStatus);
            if (res != Integer.MAX_VALUE) res++;
            return res;
        } else {
            return processLoop(arr,
                    nextIndex + 1,
                    curStatus,
                    // 如果下一个位置是最后一个开关，curStatus就不可以从arr中取，因为0位置开关按下会改变，要取endStatus
                    nextIndex == arr.length - 1 ? endStatus: arr[nextIndex],
                    firstStatus,
                    endStatus);
        }


    }

}
