package _05面试真题;

/**
 * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
 * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
 * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
 * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
 * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
 * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
 * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
 * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
 * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
 */
public class Code01_Cola {
    public static int putTimes(int m, int a, int b, int c, int x) {
        /*
        流程：
        1、通过买第一屏可乐，花掉之前没花掉的钱
        2、再看当前货币剩下的张数，能买走几瓶可乐
        3、在计算剩余的没花掉的当前货币
         */
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0; // 投了几个币
        int preQianRest = 0; // 前面筹不够一瓶可乐前剩下的钱数
        int preQianZhang = 0; // 前面筹不够一瓶可乐前剩下的钱张数
        for (int i = 0; i < 3 && m != 0; i++) {
            // 用当前这一种货币，扣除之前剩下的钱，买一瓶可乐要花几张，向上取整 (x + y - 1 / y)
            int firstColaZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (zhang[i] >= firstColaZhang) {
                zhang[i] -= firstColaZhang; // 扣除当前货币的张数
                giveRest(qian, zhang, i + 1, preQianRest + qian[i] * firstColaZhang - x, 1); // 找钱
                puts += firstColaZhang + preQianZhang; // 记录投币个数
                m--; // 买走一瓶
            } else {
                // 当前货币加上之前没花掉的钱，都搞不定一瓶，记录没花掉的钱
                preQianRest += qian[i] * zhang[i];
                preQianZhang += zhang[i];
                continue;
            }
            // 搞完第一瓶，之前剩下花不出去的钱就花掉了

            // 买一瓶可乐，需要多少张当前货币
            int oneColaZhang = (x + qian[i] - 1) / qian[i];
            int canBuyCount = zhang[i] / oneColaZhang;
            canBuyCount = Math.min(canBuyCount, m); // 防止买超
            if (canBuyCount > 0) {
                zhang[i] -= canBuyCount * oneColaZhang; // 扣除当前货币的张数
                giveRest(qian, zhang, i + 1, qian[i] * oneColaZhang - x, canBuyCount); // 找钱
                puts += canBuyCount * oneColaZhang; // 记录投币个数
                m -= canBuyCount; // 买走canBuyCount瓶
            }
            // 记录没花掉的钱
            preQianRest = qian[i] * zhang[i];
            preQianZhang = zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    /**
     * 找钱的方法
     * @param qian 钱数组
     * @param zhang 张数数组
     * @param i 要从记号货币开始分配
     * @param oneTimeRest 买一瓶可乐剩下的钱
     * @param time 买了几次
     */
    private static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int time) {
        for(; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * time;
            oneTimeRest %= qian[i];
        }
    }


    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = { 100, 50, 10 };
        int[] zhang = { c, b, a };
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("如果错误会打印错误数据，否则就是正确");
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                break;
            }
        }
        System.out.println("test end");
    }
}
