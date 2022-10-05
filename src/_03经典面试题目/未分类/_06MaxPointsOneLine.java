package _03经典面试题目.未分类;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个数组arrx 和 arry,
 * 长度都为N ,
 * 代表二维平面上有N个点,
 * 第 i 个点的x 做标配与 y 坐标分别为 arrx[i] 和 arry[i] ,
 * 返回一条直线最多能穿过多少个点
 *
 * Created by huangjunyi on 2022/10/3.
 */
public class _06MaxPointsOneLine {

    public static int getMaxPoint(int[] arrx, int[] arry) {
        /*
        遍历每个点
        统计
        1、重复点的个数
        2、共x轴点的个数
        3、共y轴点的个数
        4、共线的点的个数
        从2、3、4三个结果中选取最大，加上重复点数，尝试更新max

        共线点数统计方法：
        用一个二维map，外层key是分子，里层key是分母，value是共线点数
        (x1 - x2)/(y1 - y2)，(x1 - x2)和(y1 - y2)先除以最大公约数
        然后在填到map中
         */
        int res = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int N = arrx.length;
        for (int i = 0; i < N; i++) {
            map.clear();
            int samePoint = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            int curX = arrx[i];
            int curY = arry[i];
            for (int j = i + 1; j < N; j++) {
                int nextX = arrx[j];
                int nextY = arry[i];
                if (nextX == curX && nextY == curY) samePoint++;
                else if (nextX == curX) sameX++;
                else if (nextY == curY) sameY++;
                else {
                    int a = nextX - curX;
                    int b = nextY - curY;
                    int gcd = gcd(a, b);
                    a = a / gcd;
                    b = b / gcd;
                    if (map.containsKey(a)) {
                        if (map.containsKey(b)) {
                            map.get(a).put(b, map.get(a).get(b) + 1);
                        } else {
                            map.get(a).put(b, 1);
                        }
                    } else {
                        Map<Integer, Integer> subMap = new HashMap<>();
                        subMap.put(b, 1);
                        map.put(a, subMap);
                    }
                    line = Math.max(line, map.get(a).get(b));
                }
            }
            res = Math.max(res, Math.max(line, Math.max(sameX, sameY)) + samePoint);
        }
        return res;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
