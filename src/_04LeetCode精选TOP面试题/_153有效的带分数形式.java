package _04LeetCode精选TOP面试题;

import java.util.HashMap;
import java.util.Map;

/**
 * 100 = 3 + 69258 / 714
 * 100 = 82 + 3546 / 197
 *
 * 等号右边的部分，可以写成p1 + p2 / p3的形式
 * 要求p1和p2和p3，所使用的数字，必须把1~9使用完全，并且不重复
 * 满足的话，我们就说，形如p1 + p2 / p3，一个有效的“带分数”形式
 * 要求，p2 / p3 必须整除
 *
 * 输入N，返回N有多少种带分数形式
 * 100，有11种带分数形式
 * Created by huangjunyi on 2022/10/29.
 */
public class _153有效的带分数形式 {

    // 记录每个输入的n，对应有多少种带分数形式，只生成一次，后面直接取
    public static Map<Integer, Integer> map = new HashMap<>();

    // 用来分割num
    public static int[] arr = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    public static int ways(int n) {
        // map为空，生成n与带分数形式种数的映射表
        if (map.size() == 0) {
            process(123456789, 8);
        }
        // 直接取
        return map.containsKey(n) ? map.get(n) : 0;
    }

    /**
     * 递归对num的index为进行处理
     * 数：123456789
     * 为：876543210
     * 例如现在第8位，那么枚举第8位所有可能的数字
     * 然后递归处理第7位，直到index为-1，
     * 就可以枚举+和/的位置，生成带分数，然后记录到map中
     *
     * @param num 当前的数
     * @param index 当前要处理的时第几位
     */
    private static void process(int num, int index) {
        // index为-1，表示num已经固定好，可以枚举 + 和 / 的分割点
        if (index == -1) {
            for (int addSplit = 8; addSplit >= 2; addSplit--) {
                int p1 = num / arr[addSplit];
                int rest = num % arr[addSplit];
                for (int devSplit = (addSplit >> 1); devSplit >= 1; devSplit--) {
                    int p2 = rest / arr[devSplit];
                    int p3 = rest % arr[devSplit];
                    if (p2 % p3 == 0) {
                        int ans = p1 + p2 / p3;
                        if (!map.containsKey(ans)) {
                            map.put(ans, 1);
                        } else {
                            map.put(ans, map.get(ans) + 1);
                        }
                    }
                }
            }
        } else {
            // 遍历当前index每种可能的数字
            for (int i = index; i >=0; i--) {
                int next = swap(num, index, i);
                process(next, index - 1);
            }
        }
    }

    private static int swap(int num, int l, int r) {
        int bitL = (num / arr[l]) % 10;
        int bitR = (num / arr[r]) % 10;
        return num - (bitL - bitR) * arr[l] + (bitL - bitR) * arr[r];
    }

    public static void main(String[] args) {
        System.out.println(ways(100));
    }

}
