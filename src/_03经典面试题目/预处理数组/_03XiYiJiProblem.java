package _03经典面试题目.预处理数组;

/**
 * 洗衣机问题
 *
 * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批物品到每个打包机上，
 * 放到机每个器上的这些物品数量有多有少，由于物品数量不相同，
 * 需要工人将每个机器上的物品进行移动从而到达物品数量相等才能打包。
 * 每个物品重量太大，每次只能搬一个物品进行移动；为了省力，只能在相邻的机器上移动。
 * 请计算在搬动最小次数的前提下，使每个机器上的物品数量相等。
 * 如果不能使每个机器上的物品相同，返回-1。
 *
 * 例子1：
 * 输入：[0,3,0]
 * 输出：2
 * 具体过程：[0,3,0]->[1,2,0]->[1,1,1]
 *
 * 例子2：
 * 输入：[1,0,5]
 * 输出：4
 * 解释：[1,0,5]表示3个打包机器，且每个机器的物品数量分别为1,0,5;
 * 4表示搬动的最小次数
 * 具体过程：[1,0,5]->[1,1,4]->[2,0,4]->[2,1,3]->[2,2,2]，所以共搬动4次。
 * Created by huangjunyi on 2022/9/18.
 */
public class _03XiYiJiProblem {

    public static int averageBoxes(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int size = arr.length;
        int sum = 0;
        //求总衣服数
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        //如果不能整除，没法分配，返回-1
        if (sum % size != 0) return -1;
        //求出平均值，每台洗衣机应该有的数量
        int avg = sum / size;
        //lSum，代替左数组累加和
        int lSum = 0;
        int res = 0;
        for (int i = 0; i < size; i++) {
            //左侧应该挪动的衣服数（挪进或挪出）
            int lRest = lSum - avg * i;
            //右侧应该挪动的衣服数（通过sum减左侧的方式，省掉右侧rSum数组）
            int rRest = sum - lSum - arr[i] - (avg * (size - i - 1));
            /*
            如果两侧应该挪动的数量都是负数，则两侧的绝对值相加
            否则取绝对值最大的一侧
            然后依次遍历，保留最大的挪动数，既是答案
             */
            if (lRest < 0 && rRest < 0) res = Math.max(Math.abs(lRest) + Math.abs(rRest), res);
            else res = Math.max(Math.max(Math.abs(lRest), Math.abs(rRest)), res);
        }
        return res;
    }

}
